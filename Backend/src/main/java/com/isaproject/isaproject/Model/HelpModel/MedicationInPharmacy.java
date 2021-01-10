package com.isaproject.isaproject.Model.HelpModel;

import com.isaproject.isaproject.Model.Entity;

public class MedicationInPharmacy extends Entity {
    private int pharmacyId;
    private long medicationCode;
    private double price;
    private int quantity;

    public MedicationInPharmacy() {
        super();
    }
    public MedicationInPharmacy(int id, int pharmacyId, long medicationCode, double price, int quantity) {
        super(id);
        this.pharmacyId = pharmacyId;
        this.medicationCode = medicationCode;
        this.price = price;
        this.quantity = quantity;
    }

    public MedicationInPharmacy(int pharmacyId, long medicationCode, double price, int quantity) {
        this.pharmacyId = pharmacyId;
        this.medicationCode = medicationCode;
        this.price = price;
        this.quantity = quantity;
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
