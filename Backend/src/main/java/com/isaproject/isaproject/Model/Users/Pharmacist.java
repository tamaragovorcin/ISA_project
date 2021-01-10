package com.isaproject.isaproject.Model.Users;

public class Pharmacist extends  User{
    private int pharmacyId;
    private double mark;

    public Pharmacist() {
        super();
    }

    public Pharmacist(int pharmacyId, double mark) {
        this.pharmacyId = pharmacyId;
        this.mark = mark;
    }

    public Pharmacist(int id, String name, String surname, String email, String password, String phoneNumber, Boolean firstLogged, String town, String street, int number, int postalCode, String country, int pharmacyId, double mark) {
        super(id, name, surname, email, password, phoneNumber, firstLogged, town, street, number, postalCode, country);
        this.pharmacyId = pharmacyId;
        this.mark = mark;
    }

    public Pharmacist(String name, String surname, String email, String password, String phoneNumber, Boolean firstLogged, String town, String street, int number, int postalCode, String country, int pharmacyId, double mark) {
        super(name, surname, email, password, phoneNumber, firstLogged, town, street, number, postalCode, country);
        this.pharmacyId = pharmacyId;
        this.mark = mark;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
}
