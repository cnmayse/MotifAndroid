package com.example.charl.motif;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalInfo extends AppCompatActivity {
    final String DEFAULT_VALUE = "Anonymous";
    private TextView username;
    private Button NextActivity;
    private EditText ageview;
    private EditText ziptext;
    private String noinfo;
    private SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        final SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.nav_header_pref_file_key),
                Context.MODE_PRIVATE);
        final String name = sharedPreferences.getString(getString(R.string.username_key), DEFAULT_VALUE);
        ziptext = (EditText) findViewById(R.id.ziptext);
        ageview = (EditText) findViewById(R.id.agetext);
        NextActivity = (Button) findViewById(R.id.NextActivityButton);
        username = (TextView) findViewById(R.id.usernametextview3);
        username.setText(name);
        String age = sharedPreferences.getString(getString(R.string.userage_key), "");
        String zip = sharedPreferences.getString(getString(R.string.userzip_key), "");
            if (!(age.matches("") || zip.matches(""))){
                Intent goto_find_gallery = new Intent(getApplicationContext(), FindGalleryActivity.class);

                startActivity(goto_find_gallery);
            }

            NextActivity.setOnClickListener(new View.OnClickListener() {


                public void onClick(View v) {

                    String userage = ageview.getText().toString();
                    String zipstring = ziptext.getText().toString();


                    if (userage.matches("") && zipstring.matches("")) {
                        username.setText("must enter 1");
                    } else {
                        saveAge(userage);
                        saveZip(zipstring);


                        Intent goto_find_gallery = new Intent(getApplicationContext(), FindGalleryActivity.class);

                        startActivity(goto_find_gallery);

                    }
                }
            });

    }
    private void saveAge(String userage){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.nav_header_pref_file_key)
                ,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(getString(R.string.userage_key), userage);
        editor.commit();
    }
    private void saveZip(String zip){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.nav_header_pref_file_key)
                ,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(getString(R.string.userzip_key), zip);
        editor.commit();
    }
}

