package com.example.charl.motif;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GalleryMenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Adding our layout to parent class frame layout (adds navigation drawer).
         */
        getLayoutInflater().inflate(R.layout.activity_gallery_menu, frameLayout);

        //setContentView(R.layout.activity_gallery_menu);
    }

    /**
     * Show sample art when artist image is selected
     */
    public void nelsonButtonOnClick(View v){
        Intent artIntent = new Intent(getApplicationContext(), ArtViewingActivity.class);
        startActivity(artIntent);
    }
}
