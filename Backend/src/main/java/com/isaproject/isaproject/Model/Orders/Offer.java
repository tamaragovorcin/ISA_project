package com.isaproject.isaproject.Model.Orders;

import com.isaproject.isaproject.Model.Entity;

import java.time.LocalDate;
import java.util.Date;

public class Offer extends Entity {
    private int supplierId;
    private int orderId;
    private LocalDate dateOfDelivery;
    private double summaryPrice;

    public Offer() {
        super();
    }
    public Offer(int id, int supplierId, int orderId, LocalDate dateOfDelivery, double summaryPrice) {
        super(id);
        this.supplierId = supplierId;
        this.orderId = orderId;
        this.dateOfDelivery = dateOfDelivery;
        this.summaryPrice = summaryPrice;
    }

    public Offer(int supplierId, int orderId, LocalDate dateOfDelivery, double summaryPrice) {
        this.supplierId = supplierId;
        this.orderId = orderId;
        this.dateOfDelivery = dateOfDelivery;
        this.summaryPrice = summaryPrice;
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
