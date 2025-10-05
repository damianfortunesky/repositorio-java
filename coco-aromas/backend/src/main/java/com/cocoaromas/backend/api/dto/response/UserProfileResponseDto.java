package com.cocoaromas.backend.api.dto.response;

import java.util.UUID;

public class UserProfileResponseDto {

    private Long id;
    private UUID userId;
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
    private String birthday;
    private String taxStatus;
    private String avatarUrl;

    public UserProfileResponseDto(Long id, UUID userId, String firstName, String lastName, String nationalIdNumber,
            String phoneNumber, String address, String postalCode, String province, String city, String country,
            String sex, String birthday, String taxStatus, String avatarUrl) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalIdNumber = nationalIdNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.postalCode = postalCode;
        this.province = province;
        this.city = city;
        this.country = country;
        this.sex = sex;
        this.birthday = birthday;
        this.taxStatus = taxStatus;
        this.avatarUrl = avatarUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
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
