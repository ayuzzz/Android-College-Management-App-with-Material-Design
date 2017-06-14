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
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GALLERY extends AppCompatActivity
{
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    ProgressDialog PD;




    public static final String JSON_URL = "http://aosp.16mb.com/Gallery.php";
    public static final String TAG_IMAGE_URL = "image";
    public static final String TAG_NAME = "name";

    private GridView gridView;

    //ArrayList for Storing image urls and titles
    private ArrayList<String> images;
    private ArrayList<String> names;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);

        gridView = (GridView) findViewById(R.id.gridView);

        images = new ArrayList<>();
        names = new ArrayList<>();

        //Calling the getData method
        getData();

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
                    Intent intent = new Intent(GALLERY.this, GALLERY.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.notices) {
                    Intent intent = new Intent(GALLERY.this, Notices.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.timetables) {
                    Intent intent = new Intent(GALLERY.this, TimeTables.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.attendance) {
                    Intent intent = new Intent(GALLERY.this, Attendance.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.results) {
                    Intent intent = new Intent(GALLERY.this, Results.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.downloads) {
                    Intent intent = new Intent(GALLERY.this, Downloads.class);
                    startActivity(intent);
                    finish();

                }

                if (menuItem.getItemId() == R.id.fee) {
                    Intent intent = new Intent(GALLERY.this, Fee.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.statistics) {
                    Intent intent = new Intent(GALLERY.this, Statistics.class);
                    startActivity(intent);finish();

                }

                if (menuItem.getItemId() == R.id.teaching) {
                    Intent intent = new Intent(GALLERY.this, Teaching.class);
                    startActivity(intent);finish();

                }



                if (menuItem.getItemId() == R.id.aboutus){
                    Intent intent = new Intent(GALLERY.this, AboutUs.class);
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

    private void getData(){
        //Showing a progress dialog while our app fetches the data from url
        final ProgressDialog loading = ProgressDialog.show(this, "Please wait...","Fetching data...",false,false);

        //Creating a json array request to get the json from our api
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(JSON_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Dismissing the progressdialog on response
                        loading.dismiss();

                        //Displaying our grid
                        showGrid(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        for(int i=0;i<500;i++)
                        {loading.show();
                        }
                        Toast.makeText(GALLERY.this,"Check your Connection...",Toast.LENGTH_LONG).show();
                        loading.dismiss();

                    }
                }
        );
        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //Adding our request to the queue
        requestQueue.add(jsonArrayRequest);
    }

    private void showGrid(JSONArray jsonArray){
        //Looping through all the elements of json array
        for(int i = 0; i<jsonArray.length(); i++){
            //Creating a json object of the current index
            JSONObject obj = null;
            try {
                //getting json object from current index
                obj = jsonArray.getJSONObject(i);

                //getting image url and title from json object
                images.add(obj.getString(TAG_IMAGE_URL));
                names.add(obj.getString(TAG_NAME));
            }
            catch (JSONException e)
            {

            }
        }
        //Creating GridViewAdapter Object
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this,images);

        //Adding adapter to gridview
        gridView.setAdapter(gridViewAdapter);
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);

        // ListView setOnItemClickListener function apply here.

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // TODO Auto-generated method stub

                Intent i = new Intent(getApplicationContext(), FullscreenActivity.class);
                //i.putExtra("id",position + 1);
                i.putExtra("Name",names.get(position));
                i.putExtra("link",images.get(position));
                startActivity(i);

            }
        });


    }

}