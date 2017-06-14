package com.example.skn_comp;

import android.content.DialogInterface;
import android.content.Intent;
import android.lib.widget.verticalmarqueetextview.VerticalMarqueeTextView;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;


public class MainActivity extends AppCompatActivity{
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;


    TextView tv;
    String highlights = "";
    public static final String JSON_URL2 = "http://aosp.16mb.com/viewflipper.php";
    ImageView iv1,iv2,iv3,iv4,iv5;

    public static final String JSON_URL = "http://skn.16mb.com/PHP/highlights.php";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this,"Note:To Refresh Any Page Tilt Your Phone and Change the Screen Orientation",Toast.LENGTH_LONG).show();
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        iv4 = (ImageView) findViewById(R.id.iv4);
        iv5 = (ImageView) findViewById(R.id.iv5);


        sendRequest();
        sendRequest2();


        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */


        /**
         * Setup click events on the Navigation View Items.
         */


        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                if (menuItem.getItemId() == R.id.gallery) {
                    Intent intent = new Intent(MainActivity.this, GALLERY.class);
                    startActivity(intent);

                }

                if (menuItem.getItemId() == R.id.notices) {
                    Intent intent = new Intent(MainActivity.this, Notices.class);
                    startActivity(intent);

                }

                if (menuItem.getItemId() == R.id.timetables) {
                    Intent intent = new Intent(MainActivity.this, TimeTables.class);
                    startActivity(intent);

                }

                if (menuItem.getItemId() == R.id.attendance) {
                    Intent intent = new Intent(MainActivity.this, Attendance.class);
                    startActivity(intent);

                }

                if (menuItem.getItemId() == R.id.results) {
                    Intent intent = new Intent(MainActivity.this, Results.class);
                    startActivity(intent);

                }

                if (menuItem.getItemId() == R.id.downloads) {
                    Intent intent = new Intent(MainActivity.this, Downloads.class);
                    startActivity(intent);
                    finish();

                }

                if (menuItem.getItemId() == R.id.fee) {
                    Intent intent = new Intent(MainActivity.this, Fee.class);
                    startActivity(intent);

                }

                if (menuItem.getItemId() == R.id.statistics) {
                    Intent intent = new Intent(MainActivity.this, Statistics.class);
                    startActivity(intent);

                }

                if (menuItem.getItemId() == R.id.teaching) {
                    Intent intent = new Intent(MainActivity.this, Teaching.class);
                    startActivity(intent);

                }



                if (menuItem.getItemId() == R.id.aboutus) {
                    Intent intent = new Intent(MainActivity.this, AboutUs.class);
                    startActivity(intent);

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
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();



    }



  private void sendRequest()
    {

        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json) {
        ParseJSON1 pj = new ParseJSON1(json);
        pj.parseJSON();
        int i = 0;
        for (;i<ParseJSON1.firsts.length - 1;i++)
        {
            highlights = highlights + ParseJSON1.firsts[i] + "\n\n\n\n";


        }

        highlights = highlights + ParseJSON1.firsts[i];


        VerticalMarqueeTextView tv = (VerticalMarqueeTextView) findViewById(R.id.tv);
        tv.setText(highlights);

    }

    private void sendRequest2() {

        StringRequest stringRequest = new StringRequest(JSON_URL2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON2(response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON2(String json) {
        ParseJSON1 pj = new ParseJSON1(json);
        pj.parseJSON();
        Glide.with(this)
                .load(ParseJSON1.firsts[0])
              //  .asGif()
                .placeholder(R.drawable.loading)
                .into(iv1);
        Glide.with(this)
                .load(ParseJSON1.firsts[1])
               // .asGif()
                .placeholder(R.drawable.loading)
                .into(iv2);
        Glide.with(this)
                .load(ParseJSON1.firsts[2])
              //  .asGif()
                .placeholder(R.drawable.loading)
                .into(iv3);
        Glide.with(this)
                .load(ParseJSON1.firsts[3])
               // .asGif()
                .placeholder(R.drawable.loading)
                .into(iv4);
        Glide.with(this)
                .load(ParseJSON1.firsts[4])
               // .asGif()
                .placeholder(R.drawable.loading)
                .into(iv5);




    }


    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }


}


