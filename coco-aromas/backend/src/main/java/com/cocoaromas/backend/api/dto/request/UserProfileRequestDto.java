package com.cocoaromas.backend.api.dto.request;

import java.time.LocalDate;

public class UserProfileRequestDto {
    
    private String firstName;
    private String lastName;
    private String nationalIdNumber;
    private String phoneNumber;
    private String address;
    private String postalCode;
    private String province;
    private String city;
    private String country;
    private String sex;
    private LocalDate birthday;
    private String taxStatus;
    private String avatarUrl;

    public UserProfileRequestDto(){}
  
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getNationalIdNumber() {
        return nationalIdNumber;
    }
    public void setNationalIdNumber(String nationalIdNumber) {
        this.nationalIdNumber = nationalIdNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public String getTaxStatus() {
        return taxStatus;
    }
    public void setTaxStatus(String taxStatus) {
        this.taxStatus = taxStatus;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }  
   
}
