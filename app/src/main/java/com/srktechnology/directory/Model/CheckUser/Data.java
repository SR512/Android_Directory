package com.srktechnology.directory.Model.CheckUser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("Register_Number")
    @Expose
    private String Register_Number;

    @SerializedName("First_Name")
    @Expose
    private String First_Name;

    @SerializedName("Middel_Name")
    @Expose
    private String Middel_Name;

    @SerializedName("Last_Name")
    @Expose
    private String Last_Name;

    @SerializedName("Mobile_Number")
    @Expose
    private String Mobile_Number;

    @SerializedName("Occupation")
    @Expose
    private String Occupation;

    @SerializedName("Area")
    @Expose
    private String Area;

    @SerializedName("City")
    @Expose
    private String City;

    @SerializedName("Pincode")
    @Expose
    private String Pincode;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegister_Number() {
        return Register_Number;
    }

    public void setRegister_Number(String register_Number) {
        Register_Number = register_Number;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getMiddel_Name() {
        return Middel_Name;
    }

    public void setMiddel_Name(String middel_Name) {
        Middel_Name = middel_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getMobile_Number() {
        return Mobile_Number;
    }

    public void setMobile_Number(String mobile_Number) {
        Mobile_Number = mobile_Number;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id='" + id + '\'' +
                ", Register_Number='" + Register_Number + '\'' +
                ", First_Name='" + First_Name + '\'' +
                ", Middel_Name='" + Middel_Name + '\'' +
                ", Last_Name='" + Last_Name + '\'' +
                ", Mobile_Number='" + Mobile_Number + '\'' +
                ", Occupation='" + Occupation + '\'' +
                ", Area='" + Area + '\'' +
                ", City='" + City + '\'' +
                ", Pincode='" + Pincode + '\'' +
                '}';
    }
}
