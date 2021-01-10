package com.isaproject.isaproject.Model.Examinations;

import com.isaproject.isaproject.Model.Entity;

public class MedicationsInPrescription extends Entity {
    private long medicationCode;
    private int quantity;
    private int prescriptionId;

    public MedicationsInPrescription() {
        super();
    }
    public MedicationsInPrescription(int id, long medicationCode, int quantity, int prescriptionId) {
        super(id);
        this.medicationCode = medicationCode;
        this.quantity = quantity;
        this.prescriptionId = prescriptionId;
    }

    public MedicationsInPrescription(long medicationCode, int quantity, int prescriptionId) {
        this.medicationCode = medicationCode;
        this.quantity = quantity;
        this.prescriptionId = prescriptionId;
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

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }
}
