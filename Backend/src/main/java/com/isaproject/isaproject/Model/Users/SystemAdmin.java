package com.isaproject.isaproject.Model.Users;

public class SystemAdmin extends User{
    public SystemAdmin() {
    }

    public SystemAdmin(int id, String name, String surname, String email, String password, String phoneNumber, Boolean firstLogged, String town, String street, int number, int postalCode, String country) {
        super(id, name, surname, email, password, phoneNumber, firstLogged, town, street, number, postalCode, country);
    }

    public SystemAdmin(String name, String surname, String email, String password, String phoneNumber, Boolean firstLogged, String town, String street, int number, int postalCode, String country) {
        super(name, surname, email, password, phoneNumber, firstLogged, town, street, number, postalCode, country);
    }
}
