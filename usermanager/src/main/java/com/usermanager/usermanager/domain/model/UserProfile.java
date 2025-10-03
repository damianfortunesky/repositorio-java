package com.usermanager.usermanager.domain.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column(length = 50, unique = true)
    private String nationalIdNumber;

    @Column(length = 20)
    private String phoneNumber;

    @Column(length = 100)
    private String address;

    @Column(length = 20)
    private String postalCode;

    @Column(length = 100)
    private String province;

    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String country;

    @Column(length = 20)
    private String sex;

    @Column(length = 20)
    private LocalDate birthday;

    @Column(length = 100)
    private String taxStatus;

    @Column(length = 255)
    private String userImageUrl;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    //  Constructor protegido requerido por JPA
    public UserProfile() {}

    // Constructor de conveniencia — útil en tests o lógica
    public UserProfile(
            String firstName,
            String lastName,
            String nationalIdNumber,
            String phoneNumber,
            String address,
            String postalCode,
            String province,
            String city,
            String country,
            String sex,
            LocalDate birthday,
            String taxStatus,
            String userImageUrl
    ) {
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
        this.userImageUrl = userImageUrl;
    }

    // Getters y Setters
    public Long getId() { return id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getNationalIdNumber() { return nationalIdNumber; }
    public void setNationalIdNumber(String nationalIdNumber) { this.nationalIdNumber = nationalIdNumber; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }

    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }

    public String getTaxStatus() { return taxStatus; }
    public void setTaxStatus(String taxStatus) { this.taxStatus = taxStatus; }

    public String getUserImageUrl() { return userImageUrl; }
    public void setUserImageUrl(String userImageUrl) { this.userImageUrl = userImageUrl; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}

