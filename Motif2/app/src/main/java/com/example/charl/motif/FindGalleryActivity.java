package com.example.charl.motif;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


public class FindGalleryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Adding our layout to parent class frame layout (adds navigation drawer).
         */
        getLayoutInflater().inflate(R.layout.activity_find_gallery, frameLayout);

        //setContentView(R.layout.activity_find_gallery);

        /**
         * Retrieve username and email from incoming bundle
         */
        Intent callingIntent = this.getIntent();
        Bundle incomingBundle = callingIntent.getExtras();

        String userName = incomingBundle.getString("username");
        String userEmail = incomingBundle.getString("email");

        /**
         * Add username and email to the navigation header
         */
        NavigationView navView = (NavigationView)findViewById(R.id.nav_view);
        View headerView = navView.getHeaderView(0);

        TextView usernameView = (TextView)headerView.findViewById(R.id.navHeaderUserName);
        //System.out.println("THIS IS NAME!!!!!!!!!" + usernameView.getText());
        if(userName != null) {
            usernameView.setText(userName);
        }

        TextView emailView = (TextView)headerView.findViewById(R.id.navHeaderUserEmail);
        if(userEmail != null) {
            emailView.setText(userEmail);
        }

        //Listener for the ListView in the FindGalleryActivity
        AdapterView.OnItemClickListener listItemClickListener = new AdapterView.OnItemClickListener(){

            //create the onItemClick method
            public void onItemClick(AdapterView<?> listView,
                                    View view,
                                    int listPosition,
                                    long listID){
                if(listPosition == 0){
                    //if the user chooses position 0 create the intent
                    Intent intent = new Intent(FindGalleryActivity.this, GalleryMenuActivity.class);
                    //Send intent to GalleryMenuActivity
                    startActivity(intent);
                }
            }
        };

        //add newly created listener to the ListView
        ListView listView = (ListView) findViewById(R.id.artgallerylist);
        listView.setOnItemClickListener(listItemClickListener);
    }
}
