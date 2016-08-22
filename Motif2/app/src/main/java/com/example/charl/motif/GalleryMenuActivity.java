package com.example.charl.motif;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GalleryMenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Adding our layout to parent class frame layout (adds navigation drawer).
         */
        getLayoutInflater().inflate(R.layout.activity_gallery_menu, frameLayout);

        //setContentView(R.layout.activity_gallery_menu);

        //Retrieve gallery name from intent
        Intent incomingIntent = this.getIntent();
        String galleryName = incomingIntent.getStringExtra(getString(R.string.gallery_name));

        //set toolbar title to curent gallery
        setToolbarTitle(galleryName);
    }

    /**
     * Go to selected artist's gallery view.
     */
    public void artistOnClick(View v){
        String artistName = "";

        switch (v.getId()){
            case R.id.nelson_button:
                artistName = "Carolyn Nelson";
                break;
            case R.id.martiny_button:
                artistName = "David Martiny";
                break;
            case R.id.stewart_button:
                artistName = "Margie Stewart";
                break;
        }

        Intent artIntent = new Intent(getApplicationContext(), ArtViewingActivity.class);

        //Send selected artist name to art viewing intent
        artIntent.putExtra(getString(R.string.artist_name), artistName);

        startActivity(artIntent);
    }
}
