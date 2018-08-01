package com.srktechnology.directory.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.srktechnology.directory.Adapter.UserList_Adapter;
import com.srktechnology.directory.Model.Advertisement.Advertisement;
import com.srktechnology.directory.Model.Advertisement.Data;
import com.srktechnology.directory.Model.UserList.UserList;
import com.srktechnology.directory.R;
import com.srktechnology.directory.external_lib.APIInterFace;
import com.srktechnology.directory.external_lib.ApiUtils;
import com.srktechnology.directory.external_lib.Constant;
import com.srktechnology.directory.external_lib.SessionManager;
import com.srktechnology.directory.external_lib.SharedPref;

import java.util.HashMap;
import java.util.List;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView txtUserName, txtMobile;
    ImageView imgProfile;
    SessionManager sessionManager;
    private APIInterFace mAPIService;
    AlertDialog alertDialog;
    SliderLayout slider_add;
    private static final String TAG = "Home";
    RecyclerView recyclerView;


    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerView = (RecyclerView) findViewById(R.id.rcy_userList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        //  Initial Retrofit Service

        mAPIService = ApiUtils.getAPIService();


        slider_add = (SliderLayout) findViewById(R.id.slider_add);

        // Session manager

        sessionManager = new SessionManager(getApplicationContext());

        //  Initial Spots Dialog

        alertDialog = new SpotsDialog(Home.this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View viewHeader = navigationView.getHeaderView(0);

        txtUserName = (TextView) viewHeader.findViewById(R.id.nav_header_txtUser);
        txtMobile = (TextView) viewHeader.findViewById(R.id.nav_header_txtMobile);

        SharedPref.init(getApplicationContext(), "User_Profile");

        String strUser = SharedPref.read("First_Name", "");
        String strMobile = SharedPref.read("Mobile_Number", "");

        txtUserName.setText(strUser);
        txtMobile.setText(strMobile);

        getBanner();
        getUser();
    }

    private void getUser() {
        compositeDisposable.add(mAPIService.getUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserList>() {
                    @Override
                    public void accept(UserList userList) throws Exception {

                        if(userList.getError().equals("false"))
                        {
                            displayUser(userList);
                        }
                        else
                        {
                            Snackbar snackbar = Snackbar
                                    .make(findViewById(R.id.drawer_layout), "No Data Found..!", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }

                    }
                }));

    }

    private void displayUser(UserList userList) {
        List<com.srktechnology.directory.Model.UserList.Data> data = userList.getData();
        UserList_Adapter list_adapter = new UserList_Adapter(this,data);
        recyclerView.setAdapter(list_adapter);
    }

    private void getBanner() {
        compositeDisposable.add(mAPIService.getAdvertisement()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Advertisement>() {
                    @Override
                    public void accept(Advertisement advertisement) throws Exception {

                        if(advertisement.getError().equals("false"))
                        {
                            displayImage(advertisement);
                        }
                        else
                        {
                            Snackbar snackbar = Snackbar
                                    .make(findViewById(R.id.drawer_layout), "No Advertisement Found..!", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }

                    }
                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    private void displayImage(Advertisement advertisement) {
        HashMap<String, String> bannerMap = new HashMap<>();

        List<Data> data = advertisement.getData();
        for (Data banner : data) {
            bannerMap.put(banner.getBannerAdd(), Constant.Advertisement_PATH + banner.getFullAdd());
        }

        for (String name : bannerMap.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView.description(name)
                    .image(bannerMap.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            slider_add.addSlider(textSliderView);
        }

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
            SharedPref.init(getApplicationContext(), "User_Profile");
            SharedPref.write("id", "");
            SharedPref.write("Register_Number", "");
            try {
                SharedPref.write("Profile", "");
            } catch (Exception e) {

            }
            SharedPref.write("First_Name", "");
            SharedPref.write("Middel_Name", "");
            SharedPref.write("Last_Name", "");
            SharedPref.write("Mobile_Number", "");
            SharedPref.write("Occupation", "");
            SharedPref.write("Area", "");
            SharedPref.write("City", "");
            SharedPref.write("Pincode", "");
            SharedPref.write("Password", "");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        finish();
        startActivity(new Intent(getApplicationContext(), login_signup_AC.class));
        return true;
    }
}
