package com.example.charl.motif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class ArtViewingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Adding our layout to parent class frame layout (adds navigation drawer).
         */
        getLayoutInflater().inflate(R.layout.activity_art_viewing, frameLayout);

        //setContentView(R.layout.activity_art_viewing);

        //Set toolbar title to name of artist
        Intent incomingIntent = this.getIntent();
        String artistName = incomingIntent.getStringExtra(getString(R.string.artist_name));
        setToolbarTitle(artistName);

        final ImageAdapter imageAdapter = new ImageAdapter(this);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(imageAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //Get image ID
                int imageID = imageAdapter.getImageId(position);

                //Create intent for PieceViewingActivity
                //Send image ID and the title of selected artwork
                Intent pieceIntent = new Intent(getApplicationContext(), ArtPieceActivity.class);
                pieceIntent.putExtra(getString(R.string.piece_image_id), imageID);
                pieceIntent.putExtra(getString(R.string.art_piece_name), "\"Art Title\"");

                startActivity(pieceIntent);
            }
        });
    }
}
