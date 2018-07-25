package com.srktechnology.directory;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.srktechnology.directory.external_lib.ConnectivityReceiver;
import com.srktechnology.directory.external_lib.MyApplication;

import java.util.regex.Pattern;

public class login_signup_AC extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener, android.text.TextWatcher {

    Button btnSignin, btnSignup, btnLogin, btnCheck, btnForgetPassword, btnStep1, btnStep2, btnStep3;
    View vwSignin, vwSignup, vwStep1, vwStep2, vwStep3;
    EditText edt_Username, edt_Password, edt_Mobile, edt_FirstName, edt_MiddelName, edt_LastName, edt_EnterAddress, edt_EnterCity, edt_Pincode, edt_EnterOccupation, edt_EnterMobile;
    Typeface Poppins_ExtraLight;
    boolean error = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_login_signup__ac);

        Poppins_ExtraLight = Typeface.createFromAsset(getAssets(), Constant.Poppins_ExtraLight);

        btnSignin = (Button) findViewById(R.id.btnSignin);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        btnCheck = (Button) findViewById(R.id.btnCheck);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnStep1 = (Button) findViewById(R.id.btnStep1);
        btnStep2 = (Button) findViewById(R.id.btnStep2);
        btnStep3 = (Button) findViewById(R.id.btnStep3);
        btnForgetPassword = (Button) findViewById(R.id.btnForgetPassword);

        vwSignin = (View) findViewById(R.id.vw_signin);
        vwSignup = (View) findViewById(R.id.vw_signup);
        vwStep1 = (View) findViewById(R.id.vw_step1);
        vwStep2 = (View) findViewById(R.id.vw_step2);
        vwStep3 = (View) findViewById(R.id.vw_step3);

        edt_Username = (MaterialEditText) findViewById(R.id.edit_UserName);
        edt_Password = (MaterialEditText) findViewById(R.id.edit_Password);
        edt_Mobile = (MaterialEditText) findViewById(R.id.edit_Mobile);
        edt_FirstName = (MaterialEditText) findViewById(R.id.edit_FirstName);
        edt_MiddelName = (MaterialEditText) findViewById(R.id.edit_MiddelName);
        edt_LastName = (MaterialEditText) findViewById(R.id.edit_LastName);
        edt_EnterAddress = (MaterialEditText) findViewById(R.id.edit_EnterAddress);
        edt_EnterCity = (MaterialEditText) findViewById(R.id.edit_EnterCity);
        edt_Pincode = (MaterialEditText) findViewById(R.id.edit_Pincode);
        edt_EnterOccupation = (MaterialEditText) findViewById(R.id.edit_EnterOccupation);
        edt_EnterMobile = (MaterialEditText) findViewById(R.id.edit_EnterMobile);

        edt_Username.setTypeface(Poppins_ExtraLight);
        edt_Password.setTypeface(Poppins_ExtraLight);
        edt_Mobile.setTypeface(Poppins_ExtraLight);
        edt_FirstName.setTypeface(Poppins_ExtraLight);
        edt_MiddelName.setTypeface(Poppins_ExtraLight);
        edt_LastName.setTypeface(Poppins_ExtraLight);
        edt_EnterAddress.setTypeface(Poppins_ExtraLight);
        edt_EnterCity.setTypeface(Poppins_ExtraLight);
        edt_Pincode.setTypeface(Poppins_ExtraLight);
        edt_EnterOccupation.setTypeface(Poppins_ExtraLight);
        edt_EnterMobile.setTypeface(Poppins_ExtraLight);

        edt_Username.addTextChangedListener(this);
        edt_Password.addTextChangedListener(this);
        edt_Mobile.addTextChangedListener(this);
        edt_FirstName.addTextChangedListener(this);
        edt_MiddelName.addTextChangedListener(this);
        edt_LastName.addTextChangedListener(this);
        edt_EnterAddress.addTextChangedListener(this);
        edt_EnterCity.addTextChangedListener(this);
        edt_Pincode.addTextChangedListener(this);
        edt_EnterOccupation.addTextChangedListener(this);
        edt_EnterMobile.addTextChangedListener(this);


        btnSignup.setTypeface(Poppins_ExtraLight);
        btnSignin.setTypeface(Poppins_ExtraLight);
        btnLogin.setTypeface(Poppins_ExtraLight);
        btnCheck.setTypeface(Poppins_ExtraLight);
        btnForgetPassword.setTypeface(Poppins_ExtraLight);
        btnStep1.setTypeface(Poppins_ExtraLight);
        btnStep2.setTypeface(Poppins_ExtraLight);
        btnStep3.setTypeface(Poppins_ExtraLight);

        showSnack(checkConnection());

        edt_Username.setError("Wrong..!");
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

                vwStep1.setVisibility(View.GONE);
                vwStep2.setVisibility(View.GONE);
                vwStep3.setVisibility(View.GONE);

                return;

            case R.id.btnSignup:

                vwSignin.setVisibility(View.GONE);
                vwSignup.setVisibility(View.VISIBLE);

                btnSignup.setBackground(getResources().getDrawable(R.color.colorPrimaryDark));
                btnSignup.setTextColor(Color.parseColor("#FF8900"));

                btnSignin.setBackground(getResources().getDrawable(R.color.color_light_gray));
                btnSignin.setTextColor(Color.parseColor("#6E6E6E"));

                vwStep1.setVisibility(View.GONE);
                vwStep2.setVisibility(View.GONE);
                vwStep3.setVisibility(View.GONE);
                return;

            case R.id.btnCheck:
                vwSignup.setVisibility(View.GONE);
                vwStep1.setVisibility(View.VISIBLE);
                return;

            case R.id.btnStep1:
                vwStep1.setVisibility(View.GONE);
                vwStep2.setVisibility(View.VISIBLE);
                return;

            case R.id.btnStep2:
                vwStep2.setVisibility(View.GONE);
                vwStep3.setVisibility(View.VISIBLE);
                return;

            case R.id.btnLogin:
                if (error) {
                    Toast.makeText(getApplicationContext(), "Please Solve Error", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error is Solved", Toast.LENGTH_LONG).show();
                }

                return;
            case R.id.btnStep3:
                return;

            case R.id.btnForgetPassword:
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

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        String txtUserName = edt_Username.getText().toString();
        String txtPassword = edt_Password.getText().toString();



        if (txtUserName.isEmpty()) {
            edt_Username.setError("User Name is Required..!");
            error = true;
        } else if (!Patterns.PHONE.matcher(txtUserName).matches()) {
            edt_Username.setError("Enter Valid Mobile Number..!");
            error = true;
        }else if (txtUserName.length() != 10) {
            edt_Username.setError("Mobile Number Must Be 10 Digits..!");
            error = true;
        }
        else if (txtPassword.isEmpty()) {
            edt_Password.setError("Password is Required..!");
            error = true;
        } else {
            error = false;
        }

    }
}
