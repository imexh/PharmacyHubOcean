package com.noobz.pharmacyhub.Classes;

import com.noobz.pharmacyhub.Basic.Signup.SignupForm;

public class Account {
    String pharmacyName;
    String registrationId;
    String contactNo;
    String ownerName;
    String province;
    String city;
    String district;
    String password;

    String imageUrl;

    public Account() {
    }

    public void setAll(String registrationId, String pharmacyName, String contactNo, String ownerName,
                       String province, String district, String city, String password, String imageUrl)
    {
        this.registrationId = registrationId;
        this.pharmacyName = pharmacyName;
        this.contactNo = contactNo;
        this.ownerName = ownerName;
        this.province = province;
        this.district = district;
        this.city = city;
        this.password = password;
        this.imageUrl = imageUrl;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
