package com.example.skn_comp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class TimeTables extends AppCompatActivity
{
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    ProgressDialog PD;

    public static final String JSON_URL = "http://skn.16mb.com/PHP/TimeTables.php";

    //private Button buttonGet;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetables);

        listView = (ListView) findViewById(R.id.listView);
        PD = new ProgressDialog(this);
        PD.setMessage("Loading.....");
        PD.setCancelable(false);

        sendRequest();

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
                    Intent intent = new Intent(TimeTables.this, GALLERY.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.notices) {
                    Intent intent = new Intent(TimeTables.this, Notices.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.timetables) {
                    Intent intent = new Intent(TimeTables.this, TimeTables.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.attendance) {
                    Intent intent = new Intent(TimeTables.this, Attendance.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.results) {
                    Intent intent = new Intent(TimeTables.this, Results.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.downloads) {
                    Intent intent = new Intent(TimeTables.this, Downloads.class);
                    startActivity(intent);
                    finish();

                }

                if (menuItem.getItemId() == R.id.fee) {
                    Intent intent = new Intent(TimeTables.this, Fee.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.statistics) {
                    Intent intent = new Intent(TimeTables.this, Statistics.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.teaching) {
                    Intent intent = new Intent(TimeTables.this, Teaching.class);
                    startActivity(intent);finish();

                }



                if (menuItem.getItemId() == R.id.aboutus){
                    Intent intent = new Intent(TimeTables.this, AboutUs.class);
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

    private void sendRequest() {
        PD.show();
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
                        for(int i=0;i<500;i++)
                        {PD.show();}
                        Toast.makeText(TimeTables.this,"Check your Connection...",Toast.LENGTH_LONG).show();
                        PD.dismiss();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json) {
        ParseJSON2 pj = new ParseJSON2(json);
        pj.parseJSON();
        if(ParseJSON2.firsts==null && ParseJSON2.seconds==null)
        {
            Toast.makeText(TimeTables.this,"No Items Found !!",Toast.LENGTH_LONG).show();
            PD.dismiss();
            return;
        }


        CustomList_Timetables cl = new CustomList_Timetables(this, ParseJSON2.firsts);
        listView.setAdapter(cl);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
               /* // TODO Auto-generated method stub
                Intent i = new Intent(Notices.this,NewListActivity.class);
                i.putExtra("Heading",ParseJSON2.firsts);
                startActivity(i);*/

                Intent i = new Intent(getApplicationContext(), FullscreenActivity.class);

                i.putExtra("link",ParseJSON2.seconds[position]);
                i.putExtra("Name",ParseJSON2.firsts[position]);
                startActivity(i);
            }
        });

        PD.dismiss();
    }

    /*  @Override
    public void onClick(View v) {
        sendRequest();
    }
  */


}