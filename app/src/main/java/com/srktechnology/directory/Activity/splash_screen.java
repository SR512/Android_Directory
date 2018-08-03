package com.srktechnology.directory.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.srktechnology.directory.R;
import com.srktechnology.directory.external_lib.ConnectivityReceiver;
import com.srktechnology.directory.external_lib.MyApplication;
import com.srktechnology.directory.external_lib.SessionManager;

public class splash_screen extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    // Splash screen timer

    private static int SPLASH_TIME_OUT = 3000;
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_splash_screen);

        // Session manager

        sessionManager = new SessionManager(getApplicationContext());


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(sessionManager.isLoggedIn())
                {
                    Intent i = new Intent(splash_screen.this, Home.class);
                    startActivity(i);

                }
                else
                {
                    Intent i = new Intent(splash_screen.this, login_signup_AC.class);
                    startActivity(i);

                }


                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    // Method to manually check connection status
    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    // Showing the status in Snackbar

    private void showSnack(boolean isConnected) {
        String message;
        int color;

        if (isConnected) {
            message = "Good.! Connected to Internet";
            color = Color.WHITE;
        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
        }

        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.splash_screen), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener

        MyApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

    @Override
    public void onProgressUpdate(int percentage) {

    }
}
