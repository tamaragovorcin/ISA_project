package com.isaproject.isaproject.Model.HelpModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MedicationInPharmacy  {


    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "pharmacyId", nullable = false)
    private int pharmacyId;


    @Column(name = "medicationCode", nullable = false)
    private long medicationCode;


    @Column(name = "price", nullable = false)
    private double price;


    @Column(name = "quantity", nullable = false)
    private int quantity;

    public MedicationInPharmacy() {
    }

    public MedicationInPharmacy(Integer id, int pharmacyId, long medicationCode, double price, int quantity) {
        this.id = id;
        this.pharmacyId = pharmacyId;
        this.medicationCode = medicationCode;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public long getMedicationCode() {
        return medicationCode;
    }

    public void setMedicationCode(long medicationCode) {
        this.medicationCode = medicationCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
