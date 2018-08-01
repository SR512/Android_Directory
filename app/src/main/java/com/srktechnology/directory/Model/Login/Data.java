package com.srktechnology.directory.Model.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("Register_Number")
    @Expose
    private String registerNumber;
    @SerializedName("Profile")
    @Expose
    private String profile;
    @SerializedName("First_Name")
    @Expose
    private String firstName;
    @SerializedName("Middel_Name")
    @Expose
    private String middelName;
    @SerializedName("Last_Name")
    @Expose
    private String lastName;
    @SerializedName("Mobile_Number")
    @Expose
    private String mobileNumber;
    @SerializedName("Occupation")
    @Expose
    private String occupation;
    @SerializedName("Area")
    @Expose
    private String area;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("Pincode")
    @Expose
    private String pincode;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Status")
    @Expose
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddelName() {
        return middelName;
    }

    public void setMiddelName(String middelName) {
        this.middelName = middelName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", registerNumber='" + registerNumber + '\'' +
                ", profile='" + profile + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middelName='" + middelName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", occupation='" + occupation + '\'' +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", pincode='" + pincode + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
