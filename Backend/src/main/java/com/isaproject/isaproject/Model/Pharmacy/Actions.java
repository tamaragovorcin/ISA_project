package com.isaproject.isaproject.Model.Pharmacy;

import com.isaproject.isaproject.Model.Entity;

import java.time.LocalDate;
import java.util.Date;

public class Actions extends Entity {
    private int pharmacyId;
    private String description;
    private LocalDate expiryDate;

    public Actions() {
        super();
    }
    public Actions(int id, int pharmacyId, String description, LocalDate expiryDate) {
        super(id);
        this.pharmacyId = pharmacyId;
        this.description = description;
        this.expiryDate = expiryDate;
    }

    public Actions(int pharmacyId, String description, LocalDate expiryDate) {
        this.pharmacyId = pharmacyId;
        this.description = description;
        this.expiryDate = expiryDate;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
