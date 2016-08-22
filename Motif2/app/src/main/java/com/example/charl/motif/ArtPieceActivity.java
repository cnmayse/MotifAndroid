package com.example.charl.motif;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ArtPieceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Adding our layout to parent class frame layout (adds navigation drawer).
         */
        getLayoutInflater().inflate(R.layout.activity_art_piece, frameLayout);
        //setContentView(R.layout.activity_art_piece);

        //Get incoming image Id
        //!!!!!! Add default image if image id is non-existent.  This will swap out "0" value below.
        Intent incomingIntent = this.getIntent();
        int imageId = incomingIntent.getIntExtra(getString(R.string.piece_image_id), 0);
        String artTitle = incomingIntent.getStringExtra(getString(R.string.art_piece_name));

        //Set toolbar title to title of artwork
        setToolbarTitle(artTitle);

        //Set image using image id
        ImageView imageView = (ImageView)findViewById(R.id.artPieceImageView);

        imageView.setImageResource(imageId);
        imageView.setPadding(8, 8, 8, 8);
    }
}
