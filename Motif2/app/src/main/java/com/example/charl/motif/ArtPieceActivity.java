package com.example.charl.motif;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ArtPieceActivity extends BaseActivity {

    //Like and dislike buttons
    private ImageButton likeButton;
    private ImageButton dislikeButton;

    //Boolean values for like/dislike button states
    private boolean likeIsPressed = false;
    private boolean dislikeIsPressed = false;

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
        imageView.setPadding(2, 2, 2, 2);

        //Obtain like and dislike buttons
        likeButton = (ImageButton)findViewById(R.id.likeButton);
        dislikeButton = (ImageButton)findViewById(R.id.dislikeButton);
    }

    /**
     * If like button is clicked, change image to green like button.
     * Change dislike button to grey (if not aready).
     */
    public void likeButtonOnClick(View v){
        //If like button is already selected, unselect it by changing to grey like image
        if(likeIsPressed){
            likeButton.setImageResource(R.drawable.thumbs_up_grey);
            likeIsPressed = false;
        }
        else {
            //Change like button to green
            likeButton.setImageResource(R.drawable.thumbs_up_green);

            //Change dislike button to grey
            dislikeButton.setImageResource(R.drawable.thumbs_down_grey);

            //Adjust states
            likeIsPressed = true;
            dislikeIsPressed = false;
        }
    }

    /**
     * If dislike button is clicked, change image to red dislike button.
     * Change like button to grey like button (if not already).
     */
    public void dislikeButtonOnClick(View v){
        //If dislike button is already selected, unselect it by changing to grey image
        if(dislikeIsPressed){
            dislikeButton.setImageResource(R.drawable.thumbs_down_grey);
            dislikeIsPressed = false;
        }
        else {
            //Change dislike button to red
            dislikeButton.setImageResource(R.drawable.thumbs_down_red);

            //Change like button to grey
            likeButton.setImageResource(R.drawable.thumbs_up_grey);

            //Adjust states
            dislikeIsPressed = true;
            likeIsPressed = false;
        }
    }
}
