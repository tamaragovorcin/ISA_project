package com.isaproject.isaproject.Model.Orders;

import com.isaproject.isaproject.Model.Entity;

public class MedicationsInOrder extends Entity {
    private long medicationCode;
    private int quantity;
    private int orderId;


    public MedicationsInOrder() {
        super();
    }
    public MedicationsInOrder(int id, long medicationCode, int quantity, int orderId) {
        super(id);
        this.medicationCode = medicationCode;
        this.quantity = quantity;
        this.orderId = orderId;
    }

    public MedicationsInOrder(long medicationCode, int quantity, int orderId) {
        this.medicationCode = medicationCode;
        this.quantity = quantity;
        this.orderId = orderId;
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
