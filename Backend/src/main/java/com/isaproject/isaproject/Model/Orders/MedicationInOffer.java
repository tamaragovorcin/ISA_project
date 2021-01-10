package com.isaproject.isaproject.Model.Orders;

import com.isaproject.isaproject.Model.Entity;

public class MedicationInOffer extends Entity {
    private long medicationCode;
    private double price;
    private int quantity;
    private int offerId;

    public MedicationInOffer() {
        super();
    }
    public MedicationInOffer(int id, long medicationCode, double price, int quantity, int offerId) {
        super(id);
        this.medicationCode = medicationCode;
        this.price = price;
        this.quantity = quantity;
        this.offerId = offerId;
    }

    public MedicationInOffer(long medicationCode, double price, int quantity, int offerId) {
        this.medicationCode = medicationCode;
        this.price = price;
        this.quantity = quantity;
        this.offerId = offerId;
    }

    public long getMedicationCode() {
        return medicationCode;
    }

    public void setMedicationCode(long medicationCode) {
        this.medicationCode = medicationCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }
}
