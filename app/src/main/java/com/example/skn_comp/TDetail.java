package com.example.skn_comp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by user on 11/1/2016.
 */
public class TDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tdetail);

        String name = null,qual = null,desig = null,exp = null,image = null;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            name = bundle.getString("name");
            qual = bundle.getString("qualification");
            desig = bundle.getString("designation");
            exp = bundle.getString("experience");
            image = bundle.getString("image");
        }


        ImageView iv1 = (ImageView)findViewById(R.id.iv1);
        TouchImageView iv = (TouchImageView) findViewById(R.id.iv);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        // Show progress bar
        progressBar.setVisibility(View.VISIBLE);
        Picasso.with(this)
               .load(image)
             //.fit()
               .into(iv1, new com.squareup.picasso.Callback() {
               @Override
               public void onSuccess() {
               if (progressBar != null) {
                progressBar.setVisibility(View.GONE);
                            }
                        }
                   @Override
                        public void onError() {

                        }
                    });



        TextView tv1 = (TextView)findViewById(R.id.tv1);
        tv1.setText("\nName : " + name
                + "\n\n\nQualification : " + qual
                + "\n\n\nDesignation : " + desig
                + "\n\n\nExperience : " + exp + " Years");


    }
}