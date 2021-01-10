package com.isaproject.isaproject.Model.Users;

import com.isaproject.isaproject.Model.Entity;

public class User extends Entity {

    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private Boolean firstLogged;
    private String town;
    private String street;
    private int number;
    private int postalCode;
    private String country;


    public User() {
        super();
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User(int id, String name, String surname, String email, String password, String phoneNumber, Boolean firstLogged, String town, String street, int number, int postalCode, String country) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.firstLogged = firstLogged;
        this.town = town;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.country = country;
    }

    public User(String name, String surname, String email, String password, String phoneNumber, Boolean firstLogged, String town, String street, int number, int postalCode, String country) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.firstLogged = firstLogged;
        this.town = town;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getFirstLogged() {
        return firstLogged;
    }

    public void setFirstLogged(Boolean firstLogged) {
        this.firstLogged = firstLogged;
    }


}
