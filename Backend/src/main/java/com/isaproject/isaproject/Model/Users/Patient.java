package com.isaproject.isaproject.Model.Users;

public class Patient extends User{
    private int penalties;
    private int points;
    private String loyaltyCategory;
    private double discount;

    public Patient(int penalties, int points, String loyaltyCategory, double discount) {
        this.penalties = penalties;
        this.points = points;
        this.loyaltyCategory = loyaltyCategory;
        this.discount = discount;
    }

    public Patient(int id, String name, String surname, String email, String password, String phoneNumber, Boolean firstLogged, String town, String street, int number, int postalCode, String country, int penalties, int points, String loyaltyCategory, double discount) {
        super(id, name, surname, email, password, phoneNumber, firstLogged, town, street, number, postalCode, country);
        this.penalties = penalties;
        this.points = points;
        this.loyaltyCategory = loyaltyCategory;
        this.discount = discount;
    }

    public Patient(String name, String surname, String email, String password, String phoneNumber, Boolean firstLogged, String town, String street, int number, int postalCode, String country, int penalties, int points, String loyaltyCategory, double discount) {
        super(name, surname, email, password, phoneNumber, firstLogged, town, street, number, postalCode, country);
        this.penalties = penalties;
        this.points = points;
        this.loyaltyCategory = loyaltyCategory;
        this.discount = discount;
    }
    public Patient() {
        super();

    }

    public int getPenalties() {
        return penalties;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getLoyaltyCategory() {
        return loyaltyCategory;
    }

    public void setLoyaltyCategory(String loyaltyCategory) {
        this.loyaltyCategory = loyaltyCategory;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
