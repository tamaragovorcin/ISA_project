package com.isaproject.isaproject.Model.Users;

public class PharmacyAdmin extends User{
    private int pharmacyId;

    public PharmacyAdmin(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public PharmacyAdmin(int id, String name, String surname, String email, String password, String phoneNumber, Boolean firstLogged, String town, String street, int number, int postalCode, String country, int pharmacyId) {
        super(id, name, surname, email, password, phoneNumber, firstLogged, town, street, number, postalCode, country);
        this.pharmacyId = pharmacyId;
    }

    public PharmacyAdmin(String name, String surname, String email, String password, String phoneNumber, Boolean firstLogged, String town, String street, int number, int postalCode, String country, int pharmacyId) {
        super(name, surname, email, password, phoneNumber, firstLogged, town, street, number, postalCode, country);
        this.pharmacyId = pharmacyId;
    }
    public PharmacyAdmin() {
        super();
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }
}
