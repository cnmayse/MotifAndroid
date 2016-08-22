package com.example.charl.motif;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by ken12_000 on 7/14/2016.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public int getImageId(int position){
        return mThumbIds[position];
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(500, 500));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.sample_art, R.drawable.sample_art,
            R.drawable.sample_art, R.drawable.sample_art,
            R.drawable.sample_art, R.drawable.sample_art,
            R.drawable.sample_art, R.drawable.sample_art,
            R.drawable.sample_art, R.drawable.sample_art,
            R.drawable.sample_art, R.drawable.sample_art,
            R.drawable.sample_art, R.drawable.sample_art,
            R.drawable.sample_art, R.drawable.sample_art,
            R.drawable.sample_art, R.drawable.sample_art,
            R.drawable.sample_art, R.drawable.sample_art,
            R.drawable.sample_art, R.drawable.sample_art
    };
}
