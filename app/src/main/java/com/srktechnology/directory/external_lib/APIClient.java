package com.srktechnology.directory.external_lib;

import com.srktechnology.directory.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static Retrofit retrofit = null;

    public static Retrofit getApiClient()
    {
        if (retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(Constant.SERVER)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
