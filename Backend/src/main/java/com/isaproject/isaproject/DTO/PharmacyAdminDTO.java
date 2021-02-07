package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;

public class PharmacyAdminDTO {
    private String email;

    private String password;

    private String rewritePassword;

    private String firstname;

    private String surname;

    private String phonenumber;

    private AddressDTO address;

    private Integer pharmacyId;

    public PharmacyAdminDTO() {}

    public PharmacyAdminDTO(String email, String password, String rewritePassword, String firstname, String surname, String phonenumber, AddressDTO address, Integer pharmacyId) {
        this.email = email;
        this.password = password;
        this.rewritePassword = rewritePassword;
        this.firstname = firstname;
        this.surname = surname;
        this.phonenumber = phonenumber;
        this.address = address;
        this.pharmacyId = pharmacyId;
    }

    public String getRewritePassword() {
        return rewritePassword;
    }

    public void setRewritePassword(String rewritePassword) {
        this.rewritePassword = rewritePassword;
    }

    public Integer getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Integer pharmacyId) {
        this.pharmacyId = pharmacyId;
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
