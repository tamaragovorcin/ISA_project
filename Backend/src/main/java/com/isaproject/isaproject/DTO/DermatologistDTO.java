package com.isaproject.isaproject.DTO;

public class DermatologistDTO {
    private String email;

    private String password;

    private String firstname;

    private String surname;

    private String phonenumber;

    private AddressDTO address;

    private double markDermatologist;


    public DermatologistDTO(String email, String password, String firstname, String surname, String phonenumber, AddressDTO address, double markDermatologist) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.phonenumber = phonenumber;
        this.address = address;
        this.markDermatologist = markDermatologist;
    }

    public DermatologistDTO() { }

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

    public double getMarkDermatologist() {
        return markDermatologist;
    }

    public void setMarkDermatologist(double markDermatologist) {
        this.markDermatologist = markDermatologist;
    }
}
