package com.isaproject.isaproject.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Orders.Order;

import javax.persistence.*;

public class MedicationsInOrderDTO {
    private Medication medicine;
    private int quantity;
    private Order order;

    public MedicationsInOrderDTO() {
    }

    public MedicationsInOrderDTO(Medication medicine, int quantity, Order order) {
        this.medicine = medicine;
        this.quantity = quantity;
        this.order = order;
    }

    public Medication getMedicine() {
        return medicine;
    }

    public void setMedicine(Medication medicine) {
        this.medicine = medicine;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
