package com.srktechnology.directory;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class login_signup_AC extends AppCompatActivity {

    Button btnSignin,btnSignup,btnLogin,btnCheck,btnForgetPassword;
    View vwSignin,vwSignup;
    EditText edt_Username,edt_Password,edt_Mobile;
    Typeface Poppins_ExtraLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_login_signup__ac);

       Poppins_ExtraLight  = Typeface.createFromAsset(getAssets(),  Constant.Poppins_ExtraLight);

        btnSignin = (Button)findViewById(R.id.btnSignin);
        btnSignup = (Button)findViewById(R.id.btnSignup);
        btnCheck = (Button)findViewById(R.id.btnLogin);
        btnLogin = (Button)findViewById(R.id.btnCheck);
        btnForgetPassword = (Button)findViewById(R.id.btnForgetPassword);

        vwSignin  = (View)findViewById(R.id.vw_signin);
        vwSignup  = (View)findViewById(R.id.vw_signup);

        edt_Username = (MaterialEditText)findViewById(R.id.edit_UserName);
        edt_Password = (MaterialEditText)findViewById(R.id.edit_Password);
        edt_Mobile = (MaterialEditText)findViewById(R.id.edit_Mobile);

        edt_Username.setTypeface(Poppins_ExtraLight);
        edt_Password.setTypeface(Poppins_ExtraLight);
        edt_Mobile.setTypeface(Poppins_ExtraLight);

        btnSignup.setTypeface(Poppins_ExtraLight);
        btnSignin.setTypeface(Poppins_ExtraLight);
        btnLogin.setTypeface(Poppins_ExtraLight);
        btnCheck.setTypeface(Poppins_ExtraLight);
        btnForgetPassword.setTypeface(Poppins_ExtraLight);



    }

    //    Button Click

    public void btnClick(View view)
    {
        switch (view.getId())
        {
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

}
