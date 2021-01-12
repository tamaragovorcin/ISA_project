package com.isaproject.isaproject.Model.Examinations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MedicationsInPrescription  {


    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "medicationCode", nullable = false)
    private long medicationCode;


    @Column(name = "quantity", nullable = false)
    private int quantity;


    @Column(name = "prescriptionId", nullable = false)
    private int prescriptionId;

    public MedicationsInPrescription() {
    }

    public MedicationsInPrescription(Integer id, long medicationCode, int quantity, int prescriptionId) {
        this.id = id;
        this.medicationCode = medicationCode;
        this.quantity = quantity;
        this.prescriptionId = prescriptionId;
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

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }
}
