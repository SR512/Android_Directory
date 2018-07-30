package com.srktechnology.directory.external_lib;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import com.srktechnology.directory.Constant;
import com.srktechnology.directory.Model.CheckUser.UserDetail;

import java.util.List;

public interface APIInterFace {

    @POST(Constant.API_USERCHECK)
    @FormUrlEncoded
    Call<UserDetail> getUserDetails(
            @Field("mobile") String mobile);

}
