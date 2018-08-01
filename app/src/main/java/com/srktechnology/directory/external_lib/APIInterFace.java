package com.srktechnology.directory.external_lib;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import com.srktechnology.directory.Model.Advertisement.Advertisement;
import com.srktechnology.directory.Model.CheckUser.UserDetail;
import com.srktechnology.directory.Model.Login.Login;
import com.srktechnology.directory.Model.Register.Register;

import java.util.Observable;

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
    Call<Register> userRegister(
            @Field("First_Name") String First_Name,
            @Field("Middel_Name") String Middel_Name,
            @Field("Last_Name") String Last_Name,
            @Field("Mobile_Number") String Mobile_Number,
            @Field("Occupation") String Occupation,
            @Field("Area") String Area,
            @Field("City") String City,
            @Field("Pincode") String Pincode,
            @Field("Password") String Password);

    @POST(Constant.API_LOGIN)
    @FormUrlEncoded
    Call<Login> login(
            @Field("mobile") String mobile,
            @Field("password") String password);

    @GET(Constant.API_Advertisement)
    io.reactivex.Observable<Advertisement> getAdvertisement();
}
