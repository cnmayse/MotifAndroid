package com.example.charl.motif;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
