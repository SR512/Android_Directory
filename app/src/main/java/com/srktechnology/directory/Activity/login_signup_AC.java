package com.srktechnology.directory.Activity;

import android.app.AlertDialog;
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
import com.srktechnology.directory.Model.Login.Login;
import com.srktechnology.directory.external_lib.Constant;
import com.srktechnology.directory.Model.CheckUser.UserDetail;

import com.srktechnology.directory.Model.Register.Register;
import com.srktechnology.directory.R;
import com.srktechnology.directory.external_lib.APIInterFace;
import com.srktechnology.directory.external_lib.ApiUtils;
import com.srktechnology.directory.external_lib.ConnectivityReceiver;
import com.srktechnology.directory.external_lib.MyApplication;
import com.srktechnology.directory.external_lib.SessionManager;
import com.srktechnology.directory.external_lib.SharedPref;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login_signup_AC extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener, android.text.TextWatcher {

    Button btnSignin, btnSignup, btnLogin, btnCheck, btnForgetPassword, btnStep1, btnStep2, btnStep3;
    View vwSignin, vwSignup, vwStep1, vwStep2, vwStep3;
    EditText edt_Username, edt_Password, edt_Mobile, edt_FirstName, edt_MiddelName, edt_LastName, edt_EnterAddress, edt_EnterCity, edt_Pincode, edt_EnterOccupation, edt_EnterMobile, edt_EnterPassword, edt_EnterConformPassword;
    Typeface Poppins_ExtraLight;
    boolean error = true, isvwSignin, isvwSignup, isvwStep1, isvwStep2, isvwStep3, isUserDetail = false, isFirstTime = false;
    String txtUserName, txtPassword, txtMobile, txtFirstName, txtMideelName, txtLastName, txtAddress, txtCity, txtPincode, txtOccupation, txtMobileNumber, txtEnterPassword, txtConfromPassword, txtid, txtRegisterNo;
    AlertDialog alertDialog;
    private static final String TAG = "Login_Signup";
    private APIInterFace mAPIService;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_login_signup__ac);

        // Session manager
        session = new SessionManager(getApplicationContext());

        //  Initial Retrofit Service

        mAPIService = ApiUtils.getAPIService();

        //  Initial Spots Dialog

        alertDialog = new SpotsDialog(login_signup_AC.this);

        isvwSignin = true;

        Poppins_ExtraLight = Typeface.createFromAsset(getAssets(), Constant.Poppins_ExtraLight);

        //  Casting All Controll

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
        edt_EnterPassword = (MaterialEditText) findViewById(R.id.edit_EnterPassword);
        edt_EnterConformPassword = (MaterialEditText) findViewById(R.id.edit_EnterConformPassword);

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
        edt_EnterPassword.setTypeface(Poppins_ExtraLight);
        edt_EnterConformPassword.setTypeface(Poppins_ExtraLight);

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
        edt_EnterPassword.addTextChangedListener(this);
        edt_EnterConformPassword.addTextChangedListener(this);

        btnSignup.setTypeface(Poppins_ExtraLight);
        btnSignin.setTypeface(Poppins_ExtraLight);
        btnLogin.setTypeface(Poppins_ExtraLight);
        btnCheck.setTypeface(Poppins_ExtraLight);
        btnForgetPassword.setTypeface(Poppins_ExtraLight);
        btnStep1.setTypeface(Poppins_ExtraLight);
        btnStep2.setTypeface(Poppins_ExtraLight);
        btnStep3.setTypeface(Poppins_ExtraLight);

        showSnack(checkConnection());

    }

    //    Button Click

    public void btnClick(View view) {
        switch (view.getId()) {

            case R.id.btnSignin:
                error = true;
                vwSignin.setVisibility(View.VISIBLE);
                vwSignup.setVisibility(View.GONE);

                btnSignin.setBackground(getResources().getDrawable(R.color.colorPrimaryDark));
                btnSignin.setTextColor(Color.parseColor("#FF8900"));

                btnSignup.setBackground(getResources().getDrawable(R.color.color_light_gray));
                btnSignup.setTextColor(Color.parseColor("#6E6E6E"));

                vwStep1.setVisibility(View.GONE);
                vwStep2.setVisibility(View.GONE);
                vwStep3.setVisibility(View.GONE);

                isvwSignin = true;
                isvwSignup = false;
                isvwStep1 = false;
                isvwStep2 = false;
                isvwStep3 = false;

                edt_Username.setText("");
                edt_Password.setText("");
                edt_FirstName.setText("");
                edt_MiddelName.setText("");
                edt_LastName.setText("");
                edt_EnterAddress.setText("");
                edt_EnterCity.setText("");
                edt_Pincode.setText("");
                edt_EnterOccupation.setText("");
                edt_Mobile.setText("");
                edt_EnterMobile.setText("");
                edt_EnterPassword.setText("");
                edt_EnterConformPassword.setText("");

                return;

            case R.id.btnSignup:

                error = true;

                edt_Username.setText("");
                edt_Password.setText("");
                edt_FirstName.setText("");
                edt_MiddelName.setText("");
                edt_LastName.setText("");
                edt_EnterAddress.setText("");
                edt_EnterCity.setText("");
                edt_Pincode.setText("");
                edt_EnterOccupation.setText("");
                edt_Mobile.setText("");
                edt_EnterMobile.setText("");

                vwSignin.setVisibility(View.GONE);
                vwSignup.setVisibility(View.VISIBLE);

                btnSignup.setBackground(getResources().getDrawable(R.color.colorPrimaryDark));
                btnSignup.setTextColor(Color.parseColor("#FF8900"));

                btnSignin.setBackground(getResources().getDrawable(R.color.color_light_gray));
                btnSignin.setTextColor(Color.parseColor("#6E6E6E"));

                vwStep1.setVisibility(View.GONE);
                vwStep2.setVisibility(View.GONE);
                vwStep3.setVisibility(View.GONE);

                isvwSignin = false;
                isvwSignup = true;
                isvwStep1 = false;
                isvwStep2 = false;
                isvwStep3 = false;

                return;

            case R.id.btnCheck:

                if (error) {
                    Toast.makeText(getApplicationContext(), "All Field is Required..!", Toast.LENGTH_LONG).show();
                } else {

                    SharedPref.init(getApplicationContext(), "isUserFirst");
                    isFirstTime = SharedPref.read("isFirst", false);

                    if (!isFirstTime) {
                        if (checkConnection()) {
                            alertDialog.show();
                            CheckUserExists();
                        } else {
                            showSnack(checkConnection());

                        }
                    } else {
                        Snackbar snackbar = Snackbar
                                .make(findViewById(R.id.login_signup_AC), "Your are Already Register Please Login...!", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }

                }

                return;

            case R.id.btnStep1:

                if (error) {
                    Toast.makeText(getApplicationContext(), "All Field is Required..!", Toast.LENGTH_LONG).show();
                } else {

                    if (isUserDetail) {
                        error = false;
                    } else {
                        error = true;
                    }
                    vwStep1.setVisibility(View.GONE);
                    vwStep2.setVisibility(View.VISIBLE);

                    isvwSignin = false;
                    isvwSignup = false;
                    isvwStep1 = false;
                    isvwStep2 = true;
                    isvwStep3 = false;
                }
                return;

            case R.id.btnStep2:

                if (error) {
                    Toast.makeText(getApplicationContext(), "All Field is Required..!", Toast.LENGTH_LONG).show();
                } else {
                    if (isUserDetail) {
                        error = false;
                    } else {
                        error = true;
                    }
                    vwStep2.setVisibility(View.GONE);
                    vwStep3.setVisibility(View.VISIBLE);

                    isvwSignin = false;
                    isvwSignup = false;
                    isvwStep1 = false;
                    isvwStep2 = false;
                    isvwStep3 = true;
                }

                return;

            case R.id.btnLogin:
                if (error) {
                    Toast.makeText(getApplicationContext(), "All Field is Required..!", Toast.LENGTH_LONG).show();
                } else {
                    if (!isUserDetail) {
                        error = false;
                    } else {
                        error = true;
                    }
                    if (checkConnection()) {
                        alertDialog.show();
                        login();
                    } else {
                        showSnack(checkConnection());
                    }
                }
                return;

            case R.id.btnStep3:
                if (error) {
                    Toast.makeText(getApplicationContext(), "All Field is Required..!", Toast.LENGTH_LONG).show();
                } else {
                    if (isUserDetail) {
                        error = false;
                    } else {
                        error = true;
                    }

                    if (txtEnterPassword == null) {
                        edt_EnterPassword.setError("Password is Required..!");
                        error = true;
                    } else {
                        if (checkConnection()) {
                            alertDialog.show();
                            if (!isUserDetail) {
                                newRegister();
                            } else {

                                uploadUserData();
                            }

                        } else {
                            showSnack(checkConnection());
                        }
                    }
                }
                return;

            case R.id.btnForgetPassword:
                error = true;
                return;
        }
    }

    private void newRegister() {
        mAPIService.userRegister(edt_FirstName.getText().toString(),
                edt_MiddelName.getText().toString(),
                edt_LastName.getText().toString(),
                edt_EnterMobile.getText().toString(),
                edt_EnterOccupation.getText().toString(),
                edt_EnterAddress.getText().toString(),
                edt_EnterCity.getText().toString(),
                edt_Pincode.getText().toString(),
                edt_EnterPassword.getText().toString()).enqueue(new Callback<Register>() {

            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                alertDialog.hide();


                if (response.body().getError().equals("false")) {

                    isFirstTime = true;
                    vwStep3.setVisibility(View.GONE);
                    vwSignin.setVisibility(View.VISIBLE);

                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.login_signup_AC), "SuccessFully Submited. Please Login..!", Snackbar.LENGTH_LONG);
                    snackbar.show();

                } else {

                    edt_EnterMobile.setError(response.body().getData().getMobileNumber().get(0));

                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.login_signup_AC), response.body().getData().getMobileNumber().get(0), Snackbar.LENGTH_LONG);
                    snackbar.show();
                }


            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                alertDialog.hide();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void login() {
        mAPIService.login(txtUserName, txtPassword).enqueue(new Callback<Login>() {

            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                alertDialog.hide();


                if (response.body().getError().equals("false")) {


                    if (response.body().getData().getStatus().equals("0")) {

                        edt_Username.setText("");
                        edt_Password.setText("");

                        Snackbar snackbar = Snackbar
                                .make(findViewById(R.id.login_signup_AC), "You Are Block By Admin. Please try After SomeTime..!", Snackbar.LENGTH_LONG);
                        snackbar.show();

                    } else {
                        session.setLogin(true);
                        SharedPref.init(getApplicationContext(), "User_Profile");
                        SharedPref.write("id", response.body().getData().getId());
                        SharedPref.write("Register_Number", response.body().getData().getId());
                        try {
                            SharedPref.write("Profile", response.body().getData().getId());
                        } catch (Exception e) {

                        }
                        SharedPref.write("Mobile_Number", response.body().getData().getId());
                        SharedPref.write("Occupation", response.body().getData().getId());
                        SharedPref.write("Area", response.body().getData().getId());
                        SharedPref.write("City", response.body().getData().getId());
                        SharedPref.write("City", response.body().getData().getId());
                        SharedPref.write("Pincode", response.body().getData().getId());
                        SharedPref.write("Password", response.body().getData().getId());

                        Snackbar snackbar = Snackbar
                                .make(findViewById(R.id.login_signup_AC), "SuccessFully Login..!", Snackbar.LENGTH_LONG);
                        snackbar.show();

                    }


                } else {

                    edt_Username.setText("");
                    edt_Password.setText("");

                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.login_signup_AC), "User Name Password Wrong..!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }


            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                alertDialog.hide();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void CheckUserExists() {

        mAPIService.getUserDetails(txtMobile).enqueue(new Callback<UserDetail>() {
            @Override
            public void onResponse(Call<UserDetail> call, Response<UserDetail> response) {
                alertDialog.hide();


                if (response.errorBody() == null) {

                    error = false;
                    isUserDetail = true;

                    edt_FirstName.setText(response.body().getData().getFirst_Name());
                    edt_MiddelName.setText(response.body().getData().getMiddel_Name());
                    edt_LastName.setText(response.body().getData().getLast_Name());
                    edt_EnterAddress.setText(response.body().getData().getArea());
                    edt_EnterCity.setText(response.body().getData().getCity());
                    edt_Pincode.setText(response.body().getData().getPincode());
                    edt_EnterMobile.setText(response.body().getData().getMobile_Number());
                    edt_EnterOccupation.setText(response.body().getData().getOccupation());

                    txtid = response.body().getData().getId();
                    txtRegisterNo = response.body().getData().getRegister_Number();

                } else {
                    error = true;
                    isUserDetail = false;

                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.login_signup_AC), "Your Data Not Found Please Register as New User..!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                vwSignup.setVisibility(View.GONE);
                vwStep1.setVisibility(View.VISIBLE);

                isvwSignin = false;
                isvwSignup = false;
                isvwStep1 = true;
                isvwStep2 = false;
                isvwStep3 = false;

            }

            @Override
            public void onFailure(Call<UserDetail> call, Throwable t) {
                alertDialog.hide();
                Toast.makeText(getApplicationContext(), t.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void uploadUserData() {

        mAPIService.uploadUserData(edt_FirstName.getText().toString(),
                edt_MiddelName.getText().toString(),
                edt_LastName.getText().toString(),
                edt_EnterMobile.getText().toString(),
                edt_EnterOccupation.getText().toString(),
                edt_EnterAddress.getText().toString(),
                edt_EnterCity.getText().toString(),
                edt_Pincode.getText().toString(),
                edt_EnterPassword.getText().toString(),
                txtid, txtRegisterNo).enqueue(new Callback<UserDetail>() {
            @Override
            public void onResponse(Call<UserDetail> call, Response<UserDetail> response) {
                alertDialog.hide();

                if (response.errorBody() == null) {

                    SharedPref.init(getApplicationContext(), "isUserFirst");
                    SharedPref.write("isFirst", true);

                    vwStep3.setVisibility(View.GONE);
                    vwSignin.setVisibility(View.VISIBLE);

                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.login_signup_AC), "SuccessFully Submited. Please Login..!", Snackbar.LENGTH_LONG);
                    snackbar.show();

                } else {

                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.login_signup_AC), "Please Try Again..!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }


            }

            @Override
            public void onFailure(Call<UserDetail> call, Throwable t) {
                alertDialog.hide();
                Toast.makeText(getApplicationContext(), t.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });
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

        if (isvwSignin) {
            txtUserName = edt_Username.getText().toString();
            txtPassword = edt_Password.getText().toString();

            if (txtUserName.isEmpty()) {
                edt_Username.setError("User Name is Required..!");
                error = true;
            } else if (!Patterns.PHONE.matcher(txtUserName).matches()) {
                edt_Username.setError("Enter Valid Mobile Number..!");
                error = true;
            } else if (txtUserName.length() != 10) {
                edt_Username.setError("Mobile Number Must Be 10 Digits..!");
                error = true;
            } else if (txtPassword.isEmpty()) {
                edt_Password.setError("Password is Required..!");
                error = true;
            } else {
                error = false;
            }
        } else if (isvwSignup) {
            txtMobile = edt_Mobile.getText().toString();

            if (txtMobile.isEmpty()) {
                edt_Mobile.setError("Mobile Number is Required..!");
                error = true;
            } else if (!Patterns.PHONE.matcher(txtMobile).matches()) {
                edt_Mobile.setError("Enter Valid Mobile Number..!");
                error = true;
            } else if (txtMobile.length() != 10) {
                edt_Mobile.setError("Mobile Number Must Be 10 Digits..!");
                error = true;
            } else {
                error = false;
            }
        } else if (isvwStep1) {

            txtFirstName = edt_FirstName.getText().toString();
            txtMideelName = edt_MiddelName.getText().toString();
            txtLastName = edt_LastName.getText().toString();

            if (txtFirstName.isEmpty()) {
                edt_FirstName.setError("First Name is Required..!");
                error = true;
            } else if (txtMideelName.isEmpty()) {
                edt_MiddelName.setError("Middel Name is Required..!");
                error = true;
            } else if (txtLastName.isEmpty()) {
                edt_LastName.setError("Last Name is Required..!");
                error = true;
            } else {
                error = false;
            }
        } else if (isvwStep2) {

            txtAddress = edt_EnterAddress.getText().toString();
            txtCity = edt_EnterCity.getText().toString();
            txtPincode = edt_Pincode.getText().toString();

            if (txtAddress.isEmpty()) {
                edt_EnterAddress.setError("Address is Required..!");
                error = true;
            } else if (txtCity.isEmpty()) {
                edt_EnterCity.setError("City is Required..!");
                error = true;
            } else if (txtPincode.isEmpty()) {
                edt_Pincode.setError("Pincode is Required..!");
                error = true;
            } else if (txtPincode.length() != 6) {
                edt_Pincode.setError("Pincode Must Be 6 Digits.!");
                error = true;
            } else {
                error = false;
            }
        } else if (isvwStep3) {

            txtOccupation = edt_EnterOccupation.getText().toString();
            txtMobileNumber = edt_EnterMobile.getText().toString();
            txtEnterPassword = edt_EnterPassword.getText().toString();
            txtConfromPassword = edt_EnterConformPassword.getText().toString();

            if (txtOccupation.isEmpty()) {
                edt_EnterOccupation.setError("Occupation is Required..!");
                error = true;
            } else if (txtMobileNumber.isEmpty()) {
                edt_EnterMobile.setError("Mobile Number is Required..!");
                error = true;
            } else if (txtEnterPassword.isEmpty()) {
                edt_EnterPassword.setError("Password is Required..!");
                error = true;
            } else if (txtConfromPassword.isEmpty()) {
                edt_EnterConformPassword.setError("Conform Password is Required..!");
                error = true;
            } else if (!txtEnterPassword.equals(txtConfromPassword)) {
                edt_EnterConformPassword.setError("Conform Password Not Match..!");
                error = true;
            } else if (!Patterns.PHONE.matcher(txtMobileNumber).matches()) {
                edt_EnterMobile.setError("Enter Valid Mobile Number..!");
                error = true;
            } else if (txtMobileNumber.length() != 10) {
                edt_EnterMobile.setError("Mobile Number Must Be 10 Digits..!");
                error = true;
            } else {
                error = false;
            }
        }
    }
}
