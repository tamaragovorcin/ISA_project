package com.isaproject.isaproject.Model.Orders;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MedicationsInOrder  {

    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "medicationCode", nullable = false)
    private long medicationCode;


    @Column(name = "quantity", nullable = false)
    private int quantity;


    @Column(name = "orderId", nullable = false)
    private int orderId;


    public MedicationsInOrder() {
    }

    public MedicationsInOrder(Integer id, long medicationCode, int quantity, int orderId) {
        this.id = id;
        this.medicationCode = medicationCode;
        this.quantity = quantity;
        this.orderId = orderId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
