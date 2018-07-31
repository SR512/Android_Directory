package com.srktechnology.directory.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Data {
    @SerializedName("Mobile_Number")
    @Expose
    private List<String> mobileNumber = null;

    public List<String> getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(List<String> mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "Data{" +
                "mobileNumber=" + mobileNumber +
                '}';
    }
}
