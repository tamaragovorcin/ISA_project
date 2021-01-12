package com.isaproject.isaproject.Model.Orders;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MedicationInOffer  {


    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "medicationCode", nullable = true)
    private long medicationCode;


    @Column(name = "price", nullable = true)
    private double price;


    @Column(name = "quantity", nullable = true)
    private int quantity;


    @Column(name = "offerId", nullable = true)
    private int offerId;

    public MedicationInOffer() {
    }

    public MedicationInOffer(Integer id, long medicationCode, double price, int quantity, int offerId) {
        this.id = id;
        this.medicationCode = medicationCode;
        this.price = price;
        this.quantity = quantity;
        this.offerId = offerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
