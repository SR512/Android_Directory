package com.srktechnology.directory.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ipaulpro.afilechooser.utils.FileUtils;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;
import com.srktechnology.directory.Model.CheckUser.UserDetail;
import com.srktechnology.directory.R;
import com.srktechnology.directory.external_lib.APIInterFace;
import com.srktechnology.directory.external_lib.ApiUtils;
import com.srktechnology.directory.external_lib.ConnectivityReceiver;
import com.srktechnology.directory.external_lib.Constant;
import com.srktechnology.directory.external_lib.ProgressRequestBody;
import com.srktechnology.directory.external_lib.SharedPref;
import com.srktechnology.directory.external_lib.UplodCallBacks;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener, TextWatcher, UplodCallBacks {

    private Menu mMenu;
    ImageView imgChange;
    CircleImageView imgProfileChange;
    Button btnUploadProfile;
    EditText edt_PFirstName, edt_PMiddelName, edt_PLastName, edt_PEnterAddress, edt_PEnterCity, edt_PPincode, edt_PEnterOccupation, edt_PEnterMobile, edt_PEnterPassword;
    Typeface Poppins_ExtraLight;
    String txtProfile, txtPFirstName, txtPMideelName, txtPLastName, txtPAddress, txtPCity, txtPPincode, txtPOccupation, txtPMobileNumber, txtPEnterPassword, txtid, txtRegisterNo;
    AlertDialog alertDialog;
    private static final String TAG = "Profile";
    private APIInterFace mAPIService;
    private boolean error = false;
    private static final int REQUEST_GALLERY = 764;
    private Uri selectedImageUri;
    MultipartBody.Part body;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_profile);

        //  Initial Retrofit Service

        mAPIService = ApiUtils.getAPIService();

        //  Initial Spots Dialog

        alertDialog = new SpotsDialog(Profile.this);
        alertDialog.setCancelable(false);

        btnUploadProfile = findViewById(R.id.btnUpload);
        imgChange = findViewById(R.id.imgChange);
        imgProfileChange = findViewById(R.id.imgProfileChange);

        Poppins_ExtraLight = Typeface.createFromAsset(getAssets(), Constant.Poppins_ExtraLight);

        edt_PFirstName = (MaterialEditText) findViewById(R.id.edt_PfirstName);
        edt_PMiddelName = (MaterialEditText) findViewById(R.id.edt_PmiddelName);
        edt_PLastName = (MaterialEditText) findViewById(R.id.edt_PlastName);
        edt_PEnterAddress = (MaterialEditText) findViewById(R.id.edt_Parea);
        edt_PEnterCity = (MaterialEditText) findViewById(R.id.edt_Pcity);
        edt_PPincode = (MaterialEditText) findViewById(R.id.edt_Ppincode);
        edt_PEnterOccupation = (MaterialEditText) findViewById(R.id.edt_Pocupation);
        edt_PEnterMobile = (MaterialEditText) findViewById(R.id.edt_Pmobile);
        edt_PEnterPassword = (MaterialEditText) findViewById(R.id.edt_Password);

        edt_PFirstName.setTypeface(Poppins_ExtraLight);
        edt_PMiddelName.setTypeface(Poppins_ExtraLight);
        edt_PLastName.setTypeface(Poppins_ExtraLight);
        edt_PEnterAddress.setTypeface(Poppins_ExtraLight);
        edt_PEnterCity.setTypeface(Poppins_ExtraLight);
        edt_PPincode.setTypeface(Poppins_ExtraLight);
        edt_PEnterOccupation.setTypeface(Poppins_ExtraLight);
        edt_PEnterMobile.setTypeface(Poppins_ExtraLight);
        edt_PEnterPassword.setTypeface(Poppins_ExtraLight);
        btnUploadProfile.setTypeface(Poppins_ExtraLight);

        SharedPref.init(getApplicationContext(), "User_Profile");
        txtPFirstName = SharedPref.read("First_Name", "");
        txtPMideelName = SharedPref.read("Middel_Name", "");
        txtPLastName = SharedPref.read("Last_Name", "");
        txtPAddress = SharedPref.read("Area", "");
        txtPCity = SharedPref.read("City", "");
        txtPPincode = SharedPref.read("Pincode", "");
        txtPOccupation = SharedPref.read("Occupation", "");
        txtPMobileNumber = SharedPref.read("Mobile_Number", "");
        txtPEnterPassword = SharedPref.read("Password", "");
        txtid = SharedPref.read("id", "");
        txtRegisterNo = SharedPref.read("Register_Number", "");
        txtProfile = SharedPref.read("Profile", "");


        Picasso.with(getApplicationContext()).load(Constant.PROFILE_PATH + txtProfile).into(imgProfileChange);


        edt_PFirstName.setText(txtPFirstName);
        edt_PMiddelName.setText(txtPMideelName);
        edt_PLastName.setText(txtPLastName);
        edt_PEnterAddress.setText(txtPAddress);
        edt_PEnterCity.setText(txtPCity);
        edt_PPincode.setText(txtPPincode);
        edt_PEnterOccupation.setText(txtPOccupation);
        edt_PEnterMobile.setText(txtPMobileNumber);
        edt_PEnterPassword.setText(txtPEnterPassword);

        edt_PFirstName.addTextChangedListener(this);
        edt_PMiddelName.addTextChangedListener(this);
        edt_PLastName.addTextChangedListener(this);
        edt_PEnterAddress.addTextChangedListener(this);
        edt_PEnterCity.addTextChangedListener(this);
        edt_PPincode.addTextChangedListener(this);
        edt_PEnterMobile.addTextChangedListener(this);
        edt_PEnterPassword.addTextChangedListener(this);


        Toolbar toolbar = findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        imgChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galleryIntent();
            }
        });

        btnUploadProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkConnection()) {

                    uploadfile();
                } else {
                    showSnack(checkConnection());
                }
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == -1) {
            Intent i;

            if (requestCode == REQUEST_GALLERY) {
                this.selectedImageUri = data.getData();
                imgProfileChange.setImageURI(this.selectedImageUri);
                btnUploadProfile.setVisibility(View.VISIBLE);
            }
        }
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.PICK");
        startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.select_picture)), REQUEST_GALLERY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_edit) {
            mMenu.findItem(R.id.action_save).setVisible(true);
            mMenu.findItem(R.id.action_edit).setVisible(false);

            edt_PFirstName.setEnabled(true);
            edt_PMiddelName.setEnabled(true);
            edt_PLastName.setEnabled(true);
            edt_PEnterAddress.setEnabled(true);
            edt_PEnterCity.setEnabled(true);
            edt_PPincode.setEnabled(true);
            edt_PEnterOccupation.setEnabled(true);
            edt_PEnterMobile.setEnabled(true);
            edt_PEnterPassword.setEnabled(true);


            Toast.makeText(getApplicationContext(), "It's Work", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.action_save) {

            mMenu.findItem(R.id.action_save).setVisible(false);
            mMenu.findItem(R.id.action_edit).setVisible(true);

            if (error) {

                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.activity_profile), "All Field is Required..!", Snackbar.LENGTH_LONG);
                snackbar.show();
            } else {
                if (checkConnection()) {
                    uploadUserData();
                } else {
                    showSnack(checkConnection());
                }

            }

            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    private void uploadUserData() {
        mAPIService.uploadUserData(txtPFirstName, txtPMideelName, txtPLastName, txtPMobileNumber, txtPOccupation, txtPAddress, txtPCity, txtPPincode, txtPEnterPassword, txtid, txtRegisterNo).enqueue(new Callback<UserDetail>() {
            @Override
            public void onResponse(Call<UserDetail> call, Response<UserDetail> response) {
                alertDialog.hide();

                if (response.body().getError().equals("false")) {

                    edt_PFirstName.setEnabled(false);
                    edt_PMiddelName.setEnabled(false);
                    edt_PLastName.setEnabled(false);
                    edt_PEnterAddress.setEnabled(false);
                    edt_PEnterCity.setEnabled(false);
                    edt_PPincode.setEnabled(false);
                    edt_PEnterOccupation.setEnabled(false);
                    edt_PEnterMobile.setEnabled(false);
                    edt_PEnterPassword.setEnabled(false);

                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.activity_profile), "Profile SuccessFully Updated...!", Snackbar.LENGTH_LONG);
                    snackbar.show();

                } else {

                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.activity_profile), "Please Try Again..!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }


            }

            @Override
            public void onFailure(Call<UserDetail> call, Throwable t) {
                alertDialog.hide();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

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
                .make(findViewById(R.id.activity_profile), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
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

        txtPFirstName = edt_PFirstName.getText().toString();
        txtPMideelName = edt_PMiddelName.getText().toString();
        txtPLastName = edt_PLastName.getText().toString();
        txtPAddress = edt_PEnterAddress.getText().toString();
        txtPCity = edt_PEnterCity.getText().toString();
        txtPPincode = edt_PPincode.getText().toString();
        txtPOccupation = edt_PEnterOccupation.getText().toString();
        txtPMobileNumber = edt_PEnterMobile.getText().toString();
        txtPEnterPassword = edt_PEnterPassword.getText().toString();

        if (txtPFirstName.isEmpty()) {
            edt_PFirstName.setError("First Name is Required..!");
            error = true;
        } else if (txtPMideelName.isEmpty()) {
            edt_PMiddelName.setError("Middel Name is Required..!");
            error = true;
        } else if (txtPLastName.isEmpty()) {
            edt_PLastName.setError("Last Name is Required..!");
            error = true;
        } else if (txtPAddress.isEmpty()) {
            edt_PEnterAddress.setError("Address is Required..!");
            error = true;
        } else if (txtPCity.isEmpty()) {
            edt_PEnterCity.setError("City is Required..!");
            error = true;
        } else if (txtPPincode.isEmpty()) {
            edt_PPincode.setError("Pincode is Required..!");
            error = true;
        } else if (txtPPincode.length() != 6) {
            edt_PPincode.setError("Pincode Must Be 6 Digits.!");
            error = true;
        } else if (txtPOccupation.isEmpty()) {
            edt_PEnterOccupation.setError("Occupation is Required..!");
            error = true;
        } else if (txtPMobileNumber.isEmpty()) {
            edt_PEnterMobile.setError("Mobile Number is Required..!");
            error = true;
        } else if (txtPEnterPassword.isEmpty()) {
            edt_PEnterPassword.setError("Password is Required..!");
            error = true;
        } else if (!Patterns.PHONE.matcher(txtPMobileNumber).matches()) {
            edt_PEnterMobile.setError("Enter Valid Mobile Number..!");
            error = true;
        } else if (txtPMobileNumber.length() != 10) {
            edt_PEnterMobile.setError("Mobile Number Must Be 10 Digits..!");
            error = true;
        } else {
            error = false;
        }
    }


    @Override
    public void onProgressUpdate(int percentage) {
        progressDialog.setProgress(percentage);
    }

    private void uploadfile() {


        if (this.selectedImageUri != null) {
            progressDialog = new ProgressDialog(Profile.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setIndeterminate(false);
            progressDialog.setMax(100);
            progressDialog.setCancelable(false);
            progressDialog.show();


            File file = FileUtils.getFile(this, this.selectedImageUri);

            ProgressRequestBody requestBody = new ProgressRequestBody(file, this);

            body = MultipartBody.Part.createFormData("uploaded_file", file.getName(), requestBody);

            new Thread(new Runnable() {
                @Override
                public void run() {

                    mAPIService.uploadFile(body, txtid, txtPFirstName)
                            .enqueue(new Callback<com.srktechnology.directory.Model.Profile.Profile>() {
                                @Override
                                public void onResponse(Call<com.srktechnology.directory.Model.Profile.Profile> call, Response<com.srktechnology.directory.Model.Profile.Profile> response) {
                                    progressDialog.dismiss();

                                    if (response.body().getError().equals("false")) {

                                        Snackbar snackbar = Snackbar
                                                .make(findViewById(R.id.activity_profile), "Profile Succesfully Uploded..!", Snackbar.LENGTH_LONG);
                                        snackbar.show();

                                        btnUploadProfile.setVisibility(View.GONE);
                                    } else {

                                        Snackbar snackbar = Snackbar
                                                .make(findViewById(R.id.activity_profile), "Profile Not Uploded Try Again..!", Snackbar.LENGTH_LONG);
                                        snackbar.show();

                                    }


                                }

                                @Override
                                public void onFailure(Call<com.srktechnology.directory.Model.Profile.Profile> call, Throwable t) {
                                    progressDialog.dismiss();
                                    Snackbar snackbar = Snackbar
                                            .make(findViewById(R.id.activity_profile), "Profile Not Uploded Network Problem.. Try Again..!", Snackbar.LENGTH_LONG);
                                    snackbar.show();

                                }
                            });
                }
            }).start();
        } else {
            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.activity_profile), "Image Not Found...!", Snackbar.LENGTH_LONG);
            snackbar.show();

        }
    }

}
