package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Medicine.SupplierMedications;

public class SupplierMedicationUpdateDTO {
    private int medicationId;
    private int quantity;

    public SupplierMedicationUpdateDTO() {
    }

    public SupplierMedicationUpdateDTO(int medicationId, int quantity) {
        this.medicationId = medicationId;
        this.quantity = quantity;
    }

    public int getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(int medicationId) {
        this.medicationId = medicationId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
