package com.isaproject.isaproject.Model.Users;

public class Dermatologist extends User{
    private double mark;

    public Dermatologist(double mark) {
        this.mark = mark;
    }

    public Dermatologist(int id, String name, String surname, String email, String password, String phoneNumber, Boolean firstLogged, String town, String street, int number, int postalCode, String country, double mark) {
        super(id, name, surname, email, password, phoneNumber, firstLogged, town, street, number, postalCode, country);
        this.mark = mark;
    }

    public Dermatologist(String name, String surname, String email, String password, String phoneNumber, Boolean firstLogged, String town, String street, int number, int postalCode, String country, double mark) {
        super(name, surname, email, password, phoneNumber, firstLogged, town, street, number, postalCode, country);
        this.mark = mark;
    }
    public Dermatologist() {
        super();

    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

}
