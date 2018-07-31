package com.srktechnology.directory.external_lib;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import com.srktechnology.directory.Constant;
import com.srktechnology.directory.Login.Login;
import com.srktechnology.directory.Model.CheckUser.UserDetail;

import java.util.List;

public interface APIInterFace {

    @POST(Constant.API_USERCHECK)
    @FormUrlEncoded
    Call<UserDetail> getUserDetails(
            @Field("mobile") String mobile);

    @POST(Constant.API_REGISTER)
    @FormUrlEncoded
    Call<UserDetail> uploadUserData(
            @Field("First_Name") String First_Name,
            @Field("Middel_Name") String Middel_Name,
            @Field("Last_Name") String Last_Name,
            @Field("Mobile_Number") String Mobile_Number,
            @Field("Occupation") String Occupation,
            @Field("Area") String Area,
            @Field("City") String City,
            @Field("Pincode") String Pincode,
            @Field("Password") String Password,
            @Field("id") String id,
            @Field("Register_Number") String Register_Number

    );

    @POST(Constant.API_NEWUSER_REGISTER)
    @FormUrlEncoded
    Call<Login> userRegister(
            @Field("First_Name") String First_Name,
            @Field("Middel_Name") String Middel_Name,
            @Field("Last_Name") String Last_Name,
            @Field("Mobile_Number") String Mobile_Number,
            @Field("Occupation") String Occupation,
            @Field("Area") String Area,
            @Field("City") String City,
            @Field("Pincode") String Pincode,
            @Field("Password") String Password);
}
