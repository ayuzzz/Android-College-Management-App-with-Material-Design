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
import android.widget.TextView;

public class AboutUs extends AppCompatActivity{
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus);

        TextView tv1 = (TextView)findViewById(R.id.tv1);
        tv1.setText("INFORMATION");

        TextView tv2 = (TextView)findViewById(R.id.tv2);
        tv2.setText("Smt. Kashibai Navale College of Engineering (SKNCOE), Pune was established in 2001 under the aegis of Sinhgad Technical Education Society. SKNCOE has consistently produced excellent Results and provide a hospitable environment with up-to-date infrastructure for a rewarding, purposeful and enjoyable student life. Being a premier education institute, the endeavor of the college is to harness the innate potential in the youths and fulfill their needs for higher technical education.");


        TextView tv3 = (TextView)findViewById(R.id.tv3);
        tv3.setText("ADDRESS");

        TextView tv4 = (TextView)findViewById(R.id.tv4);
        tv4.setText("Sinhgad Institutes's\n" +
                "SMT. KASHIBAI NAVALE COLLEGE OF ENGINEERING\n" +
                "SURVEY NO. 44/1, OFF SINHGAD ROAD,\n" +
                "VADGAON BK. PUNE, MAHARASHTRA, 411041");

        TextView tv5 = (TextView)findViewById(R.id.tv5);
        tv5.setText("TELEPHONE");

        TextView tv6 = (TextView)findViewById(R.id.tv6);
        tv6.setText("020 - 24354938 \n" +
                "020 - 24100295\n\n");



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
                    Intent intent = new Intent(AboutUs.this, GALLERY.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.notices) {
                    Intent intent = new Intent(AboutUs.this, Notices.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.timetables) {
                    Intent intent = new Intent(AboutUs.this, TimeTables.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.attendance) {
                    Intent intent = new Intent(AboutUs.this, Attendance.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.results) {
                    Intent intent = new Intent(AboutUs.this, Results.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.downloads) {
                    Intent intent = new Intent(AboutUs.this, Downloads.class);
                    startActivity(intent);
                    finish();

                }


                if (menuItem.getItemId() == R.id.fee) {
                    Intent intent = new Intent(AboutUs.this, Fee.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.statistics) {
                    Intent intent = new Intent(AboutUs.this, Statistics.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.teaching) {
                    Intent intent = new Intent(AboutUs.this, Teaching.class);
                    startActivity(intent);finish();


                }

                if (menuItem.getItemId() == R.id.aboutus){
                    Intent intent = new Intent(AboutUs.this, AboutUs.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.share) {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hey, download this app!");
                    startActivity(shareIntent);

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