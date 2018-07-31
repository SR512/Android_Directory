package com.srktechnology.directory.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("data")
    @Expose
    private Data data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Login{" +
                "error='" + error + '\'' +
                ", data=" + data +
                '}';
    }
}
