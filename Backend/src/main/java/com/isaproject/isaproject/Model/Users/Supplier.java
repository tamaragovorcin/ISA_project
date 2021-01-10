package com.isaproject.isaproject.Model.Users;

public class Supplier extends User{
    public Supplier() {
    }

    public Supplier(int id, String name, String surname, String email, String password, String phoneNumber, Boolean firstLogged, String town, String street, int number, int postalCode, String country) {
        super(id, name, surname, email, password, phoneNumber, firstLogged, town, street, number, postalCode, country);
    }

    public Supplier(String name, String surname, String email, String password, String phoneNumber, Boolean firstLogged, String town, String street, int number, int postalCode, String country) {
        super(name, surname, email, password, phoneNumber, firstLogged, town, street, number, postalCode, country);
    }
}
