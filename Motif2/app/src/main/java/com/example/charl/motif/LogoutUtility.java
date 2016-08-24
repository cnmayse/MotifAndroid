package com.example.charl.motif;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/**
 * Created by ken12_000 on 8/24/2016.
 */
public class LogoutUtility implements GoogleApiClient.OnConnectionFailedListener,
GoogleApiClient.ConnectionCallbacks{

    private GoogleApiClient mGoogleApiClient;
    private final String LOG = "CONNECTION ERROR";

    public LogoutUtility(Context context){
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        googleSignOut();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(LOG, "Connection failed.");
    }

    /**
     * Sign the user of their Google account.
     */
    private void googleSignOut(){
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {

            }
        });
    }


    /**
     * Log the user out
     */
    public void logout(){
        mGoogleApiClient.connect();
    }
}
