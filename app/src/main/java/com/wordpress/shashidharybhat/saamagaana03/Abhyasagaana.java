package com.wordpress.shashidharybhat.saamagaana03;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class Abhyasagaana extends AppCompatActivity {
    protected DrawerLayout drawer;
    Button button1;
    Button down1txt;
    Button down1aud;
    Download_Files download;
    String filepath, audpath;
    File filepdf, fileaud;
    String pdfpath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abhyasagaana);
        button1 = findViewById(R.id.button_Abhyasa);
        down1txt = findViewById(R.id.down1txt);
        down1aud = findViewById(R.id.down1aud);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        download = new Download_Files(getApplicationContext());
        filepath = Environment.getExternalStorageDirectory() + File.separator + "Saamagaana" + File.separator;
        pdfpath = filepath.concat("sarali-varisai.pdf");
        audpath = filepath.concat("sobhillu.mp3");
        fileaud = new File(audpath);
        filepdf = new File(pdfpath);
        init();
        drawer = findViewById(R.id.drawer_layout2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Abhyasagaana.this, Abhyasa_pdf1.class);
                startActivity(i);
            }
        });
        toggle.syncState();
    }

    private boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public void initButtonFiles(String id) {
        /*if (filepdf.exists()) {
            down1txt.setBackgroundResource(R.drawable.ic_check_black_24dp);
            down1txt.setClickable(false);
        }
        if (fileaud.exists()) {
            down1aud.setBackgroundResource(R.drawable.ic_check_black_24dp);
            down1aud.setClickable(false);
        }*/
        BroadcastReceiver onComplete = new BroadcastReceiver() {
            public void onReceive(Context ctxt, Intent intent) {
                down1txt.setBackgroundResource(R.drawable.ic_check_black_24dp);
                down1txt.setClickable(false);
                down1aud.setBackgroundResource(R.drawable.ic_check_black_24dp);
                down1aud.setClickable(false);
                intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);

            }
        };
        registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    public void init() {
        //Boolean testvid = false;
        ///String filepath = Environment.getExternalStorageDirectory() + File.separator + "Saamagaana" + File.separator;
        /// String pdfpath = filepath.concat("sarali-varisai.pdf");
        if (filepdf.exists()) {
            down1txt.setBackgroundResource(R.drawable.ic_check_black_24dp);
            down1txt.setClickable(false);
            button1.setClickable(true);
        } else {
            button1.setClickable(false);
            down1txt.setBackgroundResource(R.drawable.ic_file_download_black_24dp);
            down1txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isConnectingToInternet()) {
                        download.letsDo(1, 1);
                        down1txt.setClickable(false);
                        initButtonFiles("0");
                    } else {
                        Toast.makeText(Abhyasagaana.this, "Oops!! There is no internet connection. Please enable internet connection and try again.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        if (fileaud.exists()) {
            down1aud.setBackgroundResource(R.drawable.ic_check_black_24dp);
            down1aud.setClickable(false);
        } else {
            button1.setClickable(false);

            down1aud.setBackgroundResource(R.drawable.ic_file_download_black_24dp);
            down1aud.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (isConnectingToInternet()) {
                        download.letsDo(2, 2);
                        down1aud.setClickable(false);
                        initButtonFiles("1");
                    } else {
                        Toast.makeText(Abhyasagaana.this, "Oops!! There is no internet connection. Please enable internet connection and try again.", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Abhyasagaana.this, MainActivity.class);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        } else if (id == R.id.Refresh) {
            Intent intent = new Intent(Abhyasagaana.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
