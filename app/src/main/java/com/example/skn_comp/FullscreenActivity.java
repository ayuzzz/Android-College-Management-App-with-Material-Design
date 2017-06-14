package com.example.skn_comp;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;


public class FullscreenActivity extends AppCompatActivity {

    String link = null;
    String name = null;
    TouchImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreenactivity);


        FloatingActionMenu materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        FloatingActionButton floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        FloatingActionButton floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);


        // For getting the name of the image that is clicked

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            name = bundle.getString("Name");
        }


        // For the link of the image to be shown in fullscreen

        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null) {
            link = bundle1.getString("link");
            iv = (TouchImageView) findViewById(R.id.iv);
            final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
            // Show progress bar
            progressBar.setVisibility(View.VISIBLE);
            Picasso.with(this)
                    .load(link)
                    //.fit()
                    .into(iv, new com.squareup.picasso.Callback() {
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


        }


        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final int id=1;
                Picasso.with(getApplicationContext())
                        .load(link)
                        .into(new Target() {

                                  @Override
                                  public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                      Toast.makeText(FullscreenActivity.this, "Downloading...", Toast.LENGTH_LONG).show();
                                      try {


                                          String root = Environment.getExternalStorageDirectory().toString();
                                          File myDir = new File(root + "/SKN-COMP");


                                          if (!myDir.exists()) {
                                              myDir.mkdirs();
                                          }

                                          final NotificationManager mNotifyManager =
                                                  (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                          final NotificationCompat.Builder  mBuilder  = new NotificationCompat.Builder(FullscreenActivity.this);
                                          mBuilder.setContentTitle("Downloading Image")
                                                  .setContentText("Download in progress")
                                                  .setSmallIcon(R.drawable.download_icon);
                                          // Start a lengthy operation in a background thread
                                          new Thread(
                                                  new Runnable() {
                                                      @Override
                                                      public void run() {
                                                          int incr;
                                                          // Do the "lengthy" operation 20 times
                                                          for (incr = 0; incr <= 50; incr+=5) {
                                                              // Sets the progress indicator to a max value, the
                                                              // current completion percentage, and "determinate"
                                                              // state
                                                              mBuilder.setProgress(100, incr, false);
                                                              // Displays the progress bar for the first time.
                                                              mNotifyManager.notify(id, mBuilder.build());
                                                              // Sleeps the thread, simulating an operation
                                                              // that takes time
                                                              try {
                                                                  // Sleep for 5 seconds
                                                                  Thread.sleep(5*100);
                                                              } catch (InterruptedException e) {

                                                              }
                                                          }
                                                          // When the loop is finished, updates the notification
                                                          mBuilder.setContentText("Download complete")
                                                                  // Removes the progress bar
                                                                  .setProgress(0,0,false);
                                                          mNotifyManager.notify(id, mBuilder.build());
                                                      }
                                                  }
                                                  // Starts the thread by calling the run() method in its Runnable
                                          ).start();

                                          String name = new Date().toString() + ".jpg";
                                          myDir = new File(myDir, name);
                                          FileOutputStream out = new FileOutputStream(myDir);
                                          bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

                                          MediaScannerConnection.scanFile(FullscreenActivity.this, new String[] { myDir.getAbsolutePath()},
                                                  null,
                                                  new MediaScannerConnection.OnScanCompletedListener() {
                                                      @Override
                                                      public void onScanCompleted(String path, Uri uri) {

                                                      }
                                                  });
                                          out.flush();
                                          out.close();
                                      }catch (Exception e)
                                      {


                                      }

                                  }

                                  @Override
                                  public void onBitmapFailed(Drawable errorDrawable) {
                                  }

                                  @Override
                                  public void onPrepareLoad(Drawable placeHolderDrawable) {
                                  }
                              }
                        );

            }


        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //put this logic in button

       /* final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        final File photoFile = new File(getFilesDir(), name);
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(photoFile));
        startActivity(Intent.createChooser(shareIntent, "Share image using"));*/
                // Get access to the URI for the bitmap
                Uri bmpUri = getLocalBitmapUri(iv);
                if (bmpUri != null) {
                    // Construct a ShareIntent with link to image
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                    shareIntent.setType("image/*");
                    // Launch sharing dialog for image
                    startActivity(Intent.createChooser(shareIntent, "Share Image"));
                } else {
                    // ...sharing failed, handle error
                }
            }
            // Returns the URI path to the Bitmap displayed in specified ImageView
            public Uri getLocalBitmapUri(ImageView iv) {
                // Extract Bitmap from ImageView drawable
                Drawable drawable = iv.getDrawable();
                Bitmap bmp = null;
                if (drawable instanceof BitmapDrawable){
                    bmp = ((BitmapDrawable) iv.getDrawable()).getBitmap();
                } else {
                    return null;
                }
                // Store image to default external storage directory
                Uri bmpUri = null;
                try {
                    File file =  new File(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
                    file.getParentFile().mkdirs();
                    FileOutputStream out = new FileOutputStream(file);
                    bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
                    out.close();
                    bmpUri = Uri.fromFile(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return bmpUri;
            }

        });

    }
}

//------------------------------------------------------------------------------------------------------------------------------------


//put this logic in button

       /* final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
        final File photoFile = new File(getFilesDir(), name);
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(photoFile));
        startActivity(Intent.createChooser(shareIntent, "Share image using"));
    } */

  /*  @Override
    public void onClick(View v) {

        Picasso.with(this)
                .load(link)
                .into(new Target() {

                          @Override
                          public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                              Toast.makeText(FullscreenActivity.this,"Downloading...", Toast.LENGTH_LONG).show();
                              try {
                                  String root = Environment.getExternalStorageDirectory().toString();
                                  File myDir = new File(root + "/SKN-COMP/Gallery");

                                  if (!myDir.exists()) {
                                      myDir.mkdirs();
                                  }

                                  String name = new Date().toString() + ".jpg";
                                  myDir = new File(myDir, name);
                                  FileOutputStream out = new FileOutputStream(myDir);
                                  bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

                                  out.flush();
                                  out.close();
                              } catch(Exception e){
                                  // some action
                              }
                          }

                          @Override
                          public void onBitmapFailed(Drawable errorDrawable) {
                          }

                          @Override
                          public void onPrepareLoad(Drawable placeHolderDrawable) {
                          }
                      }
                );

    }

}*/

