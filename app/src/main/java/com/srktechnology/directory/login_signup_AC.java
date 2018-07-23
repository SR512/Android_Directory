package com.srktechnology.directory;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class login_signup_AC extends AppCompatActivity {

    Button btnSignin,btnSignup;
    View vwSignin,vwSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_login_signup__ac);

        btnSignin = (Button)findViewById(R.id.btnSignin);
        btnSignup = (Button)findViewById(R.id.btnSignup);
        vwSignin  = (View)findViewById(R.id.vw_signin);
        vwSignup  = (View)findViewById(R.id.vw_signup);


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
