package com.example.charl.motif;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.util.Arrays;

public class LoginScreenActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private SignInButton signInButton;
    private GoogleSignInOptions gso;
    private GoogleApiClient mGoogleApiClient;
    private int RC_SIGN_IN = 100;
    private CallbackManager callbackManager;
    private TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login_screen);

        //Set up Facebook login button
        final LoginButton  loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));

        textViewName = (TextView) findViewById(R.id.textViewName);

        //See if the activity is called via logging out.
        //If so, inform the user that logout was successful
        Intent intent = getIntent();
        if(intent != null) {
            if (intent.hasExtra(getString(R.string.logout_key))) {
                //Present snackbar to user, with logout message
                Snackbar snackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayout),
                        R.string.logout_message, Snackbar.LENGTH_LONG);

                //Make snackbar text in center of bar.
                View snackView = snackbar.getView();
                View textView = (TextView)snackView.findViewById(android.support.design.R.id.snackbar_text);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                }
                snackbar.show();
            }
        }

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {


                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Intent goto_find_gallery = new Intent(getApplicationContext(),FindGalleryActivity.class);
                        startActivity(goto_find_gallery);

                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });



        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        final SignInButton signInButton  = (SignInButton) findViewById(R.id.sign_in_button);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }

        });
    }

        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            //If signin
            callbackManager.onActivityResult(requestCode, resultCode, data);
            if (requestCode == RC_SIGN_IN) {
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                //Calling a new function to handle signin
                handleSignInResult(result);
            }

        }



        //After the signing we are calling this function
        private void handleSignInResult(GoogleSignInResult result) {
            //If the login succeed
            if (result.isSuccess()) {
                //Getting google account
                GoogleSignInAccount acct = result.getSignInAccount();

                //Displaying name

               textViewName.setText(acct.getDisplayName());

                //Save username and email in Shared Preferences
                saveUsername(acct.getDisplayName());
                saveEmail(acct.getEmail());


                //Send the user name and email with the intent to the FindGalleryActivity
                //From there, the FindGalleryActivity will apply these to the nav header
                Intent goto_find_gallery = new Intent(getApplicationContext(),FindGalleryActivity.class);

                startActivity(goto_find_gallery);

            } else {
                //If login fails
                Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
            }

            //Set navigation header strings

        }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    /**
     * Save the username in SavedPreferences
     */
    private void saveUsername(String username){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.nav_header_pref_file_key)
                                                                    ,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(getString(R.string.username_key), username);
        editor.commit();
    }

    /**
     * Save the username in SavedPreferences
     */
    private void saveEmail(String email){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.nav_header_pref_file_key)
                                                                    ,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(getString(R.string.email_key), email);
        editor.commit();
    }

}

