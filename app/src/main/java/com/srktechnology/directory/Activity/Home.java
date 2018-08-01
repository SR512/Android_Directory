package com.srktechnology.directory.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.TextureView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.srktechnology.directory.R;
import com.srktechnology.directory.external_lib.SessionManager;
import com.srktechnology.directory.external_lib.SharedPref;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView txtUserName,txtMobile;
    ImageView imgProfile;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Session manager
        sessionManager = new SessionManager(getApplicationContext());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View viewHeader = navigationView.getHeaderView(0);

        txtUserName = (TextView)viewHeader.findViewById(R.id.nav_header_txtUser);
        txtMobile = (TextView)viewHeader.findViewById(R.id.nav_header_txtMobile);

        SharedPref.init(getApplicationContext(),"User_Profile");

        String strUser = SharedPref.read("First_Name","");
        String strMobile = SharedPref.read("Mobile_Number","");

        txtUserName.setText(strUser);
        txtMobile.setText(strMobile);

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
        getMenuInflater().inflate(R.menu.home, menu);
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

        if (id == R.id.nav_profile) {
            // Handle the camera action
        } else if (id == R.id.nav_events) {

        } else if (id == R.id.nav_article) {

        } else if (id == R.id.nav_add) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_logout) {
            sessionManager.setLogin(false);
            SharedPref.init(getApplicationContext(),"User_Profile");
            SharedPref.write("id","");
            SharedPref.write("Register_Number","");
            try {
                SharedPref.write("Profile","");
            } catch (Exception e) {

            }
            SharedPref.write("First_Name","");
            SharedPref.write("Middel_Name","");
            SharedPref.write("Last_Name","");
            SharedPref.write("Mobile_Number","");
            SharedPref.write("Occupation", "");
            SharedPref.write("Area", "");
            SharedPref.write("City", "");
            SharedPref.write("Pincode","");
            SharedPref.write("Password","");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        finish();
        startActivity(new Intent(getApplicationContext(),login_signup_AC.class));
        return true;
    }
}
