package com.wordpress.shashidharybhat.saamagaana03;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public DrawerLayout drawer;
    Button abhyasa;
    Button swarajathi;
    Button varnas;
    Button krithis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        /* Important
            Notice
         */
        abhyasa = findViewById(R.id.button7);
        abhyasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abhyas = new Intent(MainActivity.this
                        , com.wordpress.shashidharybhat.saamagaana03.Abhyasagaana.class);
                startActivity(abhyas);
            }
        });
        swarajathi = findViewById(R.id.button2);
        swarajathi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent swarajath = new Intent(MainActivity.this
                        , com.wordpress.shashidharybhat.saamagaana03.Swarajatis.class);
                startActivity(swarajath);
            }
        });
        varnas = findViewById(R.id.button);
        varnas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent varna = new Intent(MainActivity.this
                        , com.wordpress.shashidharybhat.saamagaana03.Varnas.class);
                startActivity(varna);
            }
        });
        krithis = findViewById(R.id.button6);
        krithis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent krithi = new Intent(MainActivity.this
                        , com.wordpress.shashidharybhat.saamagaana03.Krithis.class);
                startActivity(krithi);
            }
        });
        ActionBar actbar = getSupportActionBar();
        actbar.setTitle("Saamagaana Sangeetha Sabha");
        actbar.setDisplayUseLogoEnabled(true);
        actbar.setDisplayShowHomeEnabled(true);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Landing.class);
        startActivity(intent);
    }

    /*@Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }*/

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
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.abhyasagaana) {
            Intent intent = new Intent(MainActivity.this, Abhyasagaana.class);
            startActivity(intent);
        } else if (id == R.id.swarajathi) {
            Intent intent = new Intent(MainActivity.this, Swarajatis.class);
            startActivity(intent);
        } else if (id == R.id.varnas) {
            Intent intent = new Intent(MainActivity.this, Varnas.class);
            startActivity(intent);
        } else if (id == R.id.krithis) {
            Intent intent = new Intent(MainActivity.this, Krithis.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void download(View view) {
        Intent intent = new Intent(MainActivity.this, Download_Files.class);
        startActivity(intent);
    }
}
