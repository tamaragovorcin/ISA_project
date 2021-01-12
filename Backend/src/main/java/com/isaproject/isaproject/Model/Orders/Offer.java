package com.isaproject.isaproject.Model.Orders;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;
@Entity
public class Offer {


    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "supplierId", nullable = false)
    private int supplierId;


    @Column(name = "orderId", nullable = false)
    private int orderId;


    @Column(name = "dateOfDelivery", nullable = false)
    private LocalDate dateOfDelivery;


    @Column(name = "summaryPrice", nullable = false)
    private double summaryPrice;

    public Offer() {
    }

    public Offer(Integer id, int supplierId, int orderId, LocalDate dateOfDelivery, double summaryPrice) {
        this.id = id;
        this.supplierId = supplierId;
        this.orderId = orderId;
        this.dateOfDelivery = dateOfDelivery;
        this.summaryPrice = summaryPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(LocalDate dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public double getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(double summaryPrice) {
        this.summaryPrice = summaryPrice;
    }
}
