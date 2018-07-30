package com.srktechnology.directory.external_lib;

import com.srktechnology.directory.Constant;
import com.srktechnology.directory.external_lib.RetrofitClient;

public class ApiUtils {

    private ApiUtils() {
    }


    public static APIInterFace getAPIService() {

        return RetrofitClient.getClient(Constant.SERVER).create(APIInterFace.class);
    }
}