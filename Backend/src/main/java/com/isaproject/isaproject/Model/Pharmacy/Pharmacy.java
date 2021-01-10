package com.isaproject.isaproject.Model.Pharmacy;

import com.isaproject.isaproject.Model.Entity;

public class Pharmacy extends Entity {
    private String name;
    private String town;
    private String street;
    private int number;
    private int postalCode;
    private String country;
    private double mark;
    private double consultingPrice;

    public Pharmacy() {
        super();
    }
    public Pharmacy(int id, String name, String town, String street, int number, int postalCode, String country, double mark, double consultingPrice) {
        super(id);
        this.name = name;
        this.town = town;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.country = country;
        this.mark = mark;
        this.consultingPrice = consultingPrice;
    }

    public Pharmacy(String name, String town, String street, int number, int postalCode, String country, double mark, double consultingPrice) {
        this.name = name;
        this.town = town;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.country = country;
        this.mark = mark;
        this.consultingPrice = consultingPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public double getConsultingPrice() {
        return consultingPrice;
    }

    public void setConsultingPrice(double consultingPrice) {
        this.consultingPrice = consultingPrice;
    }
}
