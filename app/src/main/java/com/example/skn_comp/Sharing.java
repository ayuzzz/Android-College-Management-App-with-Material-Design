package com.example.skn_comp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class Sharing extends AppCompatActivity{
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharing);

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hey, download this app!");
        startActivity(shareIntent);

        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                if (menuItem.getItemId() == R.id.gallery) {
                    Intent intent = new Intent(Sharing.this, GALLERY.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.notices) {
                    Intent intent = new Intent(Sharing.this, Notices.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.timetables) {
                    Intent intent = new Intent(Sharing.this, TimeTables.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.attendance) {
                    Intent intent = new Intent(Sharing.this, Attendance.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.results) {
                    Intent intent = new Intent(Sharing.this, Results.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.fee) {
                    Intent intent = new Intent(Sharing.this, Fee.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.statistics) {
                    Intent intent = new Intent(Sharing.this, Statistics.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.teaching) {
                    Intent intent = new Intent(Sharing.this, Teaching.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.nteaching) {
                    Intent intent = new Intent(Sharing.this, Nteaching.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.aboutus){
                    Intent intent = new Intent(Sharing.this, AboutUs.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.share) {
                    Intent intent = new Intent(Sharing.this, Sharing.class);
                    startActivity(intent);finish();

                }









                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

    }
}