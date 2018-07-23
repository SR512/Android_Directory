package com.srktechnology.directory;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.srktechnology.directory.external_lib.ConnectivityReceiver;
import com.srktechnology.directory.external_lib.MyApplication;

public class login_signup_AC extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    Button btnSignin, btnSignup, btnLogin, btnCheck, btnForgetPassword;
    View vwSignin, vwSignup;
    EditText edt_Username, edt_Password, edt_Mobile;
    Typeface Poppins_ExtraLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_login_signup__ac);

        Poppins_ExtraLight = Typeface.createFromAsset(getAssets(), Constant.Poppins_ExtraLight);

        btnSignin = (Button) findViewById(R.id.btnSignin);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        btnCheck = (Button) findViewById(R.id.btnLogin);
        btnLogin = (Button) findViewById(R.id.btnCheck);
        btnForgetPassword = (Button) findViewById(R.id.btnForgetPassword);

        vwSignin = (View) findViewById(R.id.vw_signin);
        vwSignup = (View) findViewById(R.id.vw_signup);

        edt_Username = (MaterialEditText) findViewById(R.id.edit_UserName);
        edt_Password = (MaterialEditText) findViewById(R.id.edit_Password);
        edt_Mobile = (MaterialEditText) findViewById(R.id.edit_Mobile);

        edt_Username.setTypeface(Poppins_ExtraLight);
        edt_Password.setTypeface(Poppins_ExtraLight);
        edt_Mobile.setTypeface(Poppins_ExtraLight);

        btnSignup.setTypeface(Poppins_ExtraLight);
        btnSignin.setTypeface(Poppins_ExtraLight);
        btnLogin.setTypeface(Poppins_ExtraLight);
        btnCheck.setTypeface(Poppins_ExtraLight);
        btnForgetPassword.setTypeface(Poppins_ExtraLight);

        showSnack(checkConnection());
    }

    //    Button Click

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignin:
                vwSignin.setVisibility(View.VISIBLE);
                vwSignup.setVisibility(View.GONE);

                btnSignin.setBackground(getResources().getDrawable(R.color.colorPrimaryDark));
                btnSignin.setTextColor(Color.parseColor("#FF8900"));

                btnSignup.setBackground(getResources().getDrawable(R.color.color_light_gray));
                btnSignup.setTextColor(Color.parseColor("#6E6E6E"));

                return;

            case R.id.btnSignup:

                vwSignin.setVisibility(View.GONE);
                vwSignup.setVisibility(View.VISIBLE);

                btnSignup.setBackground(getResources().getDrawable(R.color.colorPrimaryDark));
                btnSignup.setTextColor(Color.parseColor("#FF8900"));

                btnSignin.setBackground(getResources().getDrawable(R.color.color_light_gray));
                btnSignin.setTextColor(Color.parseColor("#6E6E6E"));

                return;

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    // Method to manually check connection status

    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        return isConnected;
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
                .make(findViewById(R.id.login_signup_AC), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }
}
