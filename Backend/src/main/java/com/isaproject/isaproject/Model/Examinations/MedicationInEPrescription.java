package com.isaproject.isaproject.Model.Examinations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MedicationInEPrescription  {


    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "medicationCode", nullable = false)
    private long medicationCode;


    @Column(name = "quantity", nullable = false)
    private int quantity;


    @Column(name = "ePrescriptionId", nullable = false)
    private int ePrescriptionId;

    public MedicationInEPrescription() {
    }

    public MedicationInEPrescription(Integer id, long medicationCode, int quantity, int ePrescriptionId) {
        this.id = id;
        this.medicationCode = medicationCode;
        this.quantity = quantity;
        this.ePrescriptionId = ePrescriptionId;
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

    public int getePrescriptionId() {
        return ePrescriptionId;
    }

    public void setePrescriptionId(int ePrescriptionId) {
        this.ePrescriptionId = ePrescriptionId;
    }
}
