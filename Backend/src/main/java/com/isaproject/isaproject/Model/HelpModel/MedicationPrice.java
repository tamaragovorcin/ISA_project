package com.isaproject.isaproject.Model.HelpModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
@Entity
public class MedicationPrice {


    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "medicineCode", nullable = false)
    private long medicineCode;


    @Column(name = "price", nullable = false)
    private double price;


    @Column(name = "date", nullable = false)
    private LocalDate date;


    @Column(name = "pharmacyId", nullable = false)
    private int pharmacyId;

    public MedicationPrice() {
    }

    public MedicationPrice(Integer id, long medicineCode, double price, LocalDate date, int pharmacyId) {
        this.id = id;
        this.medicineCode = medicineCode;
        this.price = price;
        this.date = date;
        this.pharmacyId = pharmacyId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
