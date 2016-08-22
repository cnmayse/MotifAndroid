package com.example.charl.motif;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Base class that creates a navigation drawer (side-menu).
 * A toolbar is also present, to hold menu button.
 * Contains all android-default styling right now.
 */
public abstract class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected FrameLayout frameLayout;

    //Value to return if username and/or email not in shared preferences file
    final String DEFAULT_VALUE = "Unknown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        frameLayout = (FrameLayout)findViewById(R.id.content_frame);

        /**FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Add the username and email to the nav header
        setUpNavHeader();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //These are for the default menu items that Android added with the navigation menu
       /* if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        if(id == R.id.nav_gallery){
            Intent galleryIntent = new Intent(getApplicationContext(), FindGalleryActivity.class);
            startActivity(galleryIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Add username and email to the nav header if activity called from login activity
     */
    private void setUpNavHeader(){
        //Retrieve username and email from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.nav_header_pref_file_key),
                                                                            Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(getString(R.string.username_key), DEFAULT_VALUE);
        String email = sharedPreferences.getString(getString(R.string.email_key), DEFAULT_VALUE);


        //Add username and email to the navigation header
        NavigationView navView = (NavigationView)findViewById(R.id.nav_view);
        View headerView = navView.getHeaderView(0);

        TextView usernameView = (TextView)headerView.findViewById(R.id.navHeaderUserName);
        //System.out.println("THIS IS NAME!!!!!!!!!" + usernameView.getText());
        if(name != null) {
            usernameView.setText(name);
        }

        TextView emailView = (TextView)headerView.findViewById(R.id.navHeaderUserEmail);
        if(email != null) {
            emailView.setText(email);
        }
    }

    /**
     * Set the tile of the toolbar
     */
    public void setToolbarTitle(String title){
        ((TextView)findViewById(R.id.toolbar_title)).setText(title);
    }
}
