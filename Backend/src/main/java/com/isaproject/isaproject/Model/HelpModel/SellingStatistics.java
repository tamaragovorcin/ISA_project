package com.isaproject.isaproject.Model.HelpModel;

import com.isaproject.isaproject.Model.Entity;

import java.time.LocalDate;

public class SellingStatistics extends Entity {
    private long medicationCode;
    private LocalDate date;
    private int quantity;
    private double price;

    public SellingStatistics() {
        super();
    }
    public SellingStatistics(int id, long medicationCode, LocalDate date, int quantity, double price) {
        super(id);
        this.medicationCode = medicationCode;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
    }

    public SellingStatistics(long medicationCode, LocalDate date, int quantity, double price) {
        this.medicationCode = medicationCode;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
    }

    public long getMedicationCode() {
        return medicationCode;
    }

    public void setMedicationCode(long medicationCode) {
        this.medicationCode = medicationCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
