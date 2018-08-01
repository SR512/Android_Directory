package com.srktechnology.directory.external_lib;

public class ApiUtils {

    private ApiUtils() {
    }


    public static APIInterFace getAPIService() {

        return RetrofitClient.getClient(Constant.SERVER).create(APIInterFace.class);
    }
}