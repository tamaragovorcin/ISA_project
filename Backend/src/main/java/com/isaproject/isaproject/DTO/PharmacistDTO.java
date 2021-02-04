package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;

public class PharmacistDTO {

    private Integer id;

    private String email;

    private String password;

    private String firstname;

    private String surname;

    private String phonenumber;

    private AddressDTO address;

    private Pharmacy pharmacy;

    private double mark;

    public PharmacistDTO() {}

    public PharmacistDTO(String email, String password, String firstname, String surname, String phonenumber, AddressDTO address, Pharmacy pharmacy) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.phonenumber = phonenumber;
        this.address = address;
        this.pharmacy = pharmacy;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
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
}