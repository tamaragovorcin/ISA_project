package com.isaproject.isaproject.DTO;

public class PharmacistDTO {

    private Integer id;

    private String email;

    private String password;

    private String rewritePassword;

    private String firstname;

    private String surname;

    private String phonenumber;

    private AddressDTO address;

    private Integer pharmacyID;

    private double mark;

    public PharmacistDTO() {}

    public PharmacistDTO(Integer id, String email, String password, String rewritePassword, String firstname, String surname, String phonenumber, AddressDTO address, Integer pharmacyID, double mark) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.rewritePassword = rewritePassword;
        this.firstname = firstname;
        this.surname = surname;
        this.phonenumber = phonenumber;
        this.address = address;
        this.pharmacyID = pharmacyID;
        this.mark = mark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRewritePassword() {
        return rewritePassword;
    }

    public void setRewritePassword(String rewritePassword) {
        this.rewritePassword = rewritePassword;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public Integer getPharmacyID() {
        return pharmacyID;
    }

    public void setPharmacyID(Integer pharmacyID) {
        this.pharmacyID = pharmacyID;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
}