package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Orders.Order;

public class MedicationsInOrderDTO {
    private Medication medicine;
    private int quantity;

    public MedicationsInOrderDTO() { }

    public MedicationsInOrderDTO(Medication medicine, int quantity, Order order) {
        this.medicine = medicine;
        this.quantity = quantity;
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
}
