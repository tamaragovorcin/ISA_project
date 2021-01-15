package com.isaproject.isaproject.Model.HelpModel;

import jdk.jfr.Enabled;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
@Entity
public class SellingStatistics  {

    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "medicationCode", nullable = true)
    private long medicationCode;


    @Column(name = "date", nullable = true)
    private LocalDate date;


    @Column(name = "quantity", nullable = true)
    private int quantity;


    @Column(name = "price", nullable = true)
    private double price;

    public SellingStatistics() {
    }

    public SellingStatistics(Integer id, long medicationCode, LocalDate date, int quantity, double price) {
        this.id = id;
        this.medicationCode = medicationCode;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
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
