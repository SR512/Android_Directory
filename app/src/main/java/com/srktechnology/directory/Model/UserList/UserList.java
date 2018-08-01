package com.srktechnology.directory.Model.UserList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserList {

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("data")
    @Expose
    private List<Data> data = null;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UserList{" +
                "error='" + error + '\'' +
                ", data=" + data +
                '}';
    }
}
