package com.isaproject.isaproject.Model.Examinations;

import com.isaproject.isaproject.Model.Entity;

public class MedicationInEPrescription extends Entity {
    private long medicationCode;
    private int quantity;
    private int ePrescriptionId;

    public MedicationInEPrescription() {
        super();
    }
    public MedicationInEPrescription(int id, long medicationCode, int quantity, int ePrescriptionId) {
        super(id);
        this.medicationCode = medicationCode;
        this.quantity = quantity;
        this.ePrescriptionId = ePrescriptionId;
    }

    public MedicationInEPrescription(long medicationCode, int quantity, int ePrescriptionId) {
        this.medicationCode = medicationCode;
        this.quantity = quantity;
        this.ePrescriptionId = ePrescriptionId;
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

    public int getePrescriptionId() {
        return ePrescriptionId;
    }

    public void setePrescriptionId(int ePrescriptionId) {
        this.ePrescriptionId = ePrescriptionId;
    }
}
