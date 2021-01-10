package com.isaproject.isaproject.Model.HelpModel;

import com.isaproject.isaproject.Model.Entity;

import java.time.LocalDate;

public class MedicationPrice extends Entity {
    private long medicineCode;
    private double price;
    private LocalDate date;
    private int pharmacyId;

    public MedicationPrice() {
        super();
    }

    public MedicationPrice(int id, long medicineCode, double price, LocalDate date, int pharmacyId) {
        super(id);
        this.medicineCode = medicineCode;
        this.price = price;
        this.date = date;
        this.pharmacyId = pharmacyId;
    }

    public MedicationPrice(long medicineCode, double price, LocalDate date, int pharmacyId) {
        this.medicineCode = medicineCode;
        this.price = price;
        this.date = date;
        this.pharmacyId = pharmacyId;
    }

    public long getMedicineCode() {
        return medicineCode;
    }

    public void setMedicineCode(long medicineCode) {
        this.medicineCode = medicineCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }
}
