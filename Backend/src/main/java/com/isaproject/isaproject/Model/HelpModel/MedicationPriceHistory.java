package com.isaproject.isaproject.Model.HelpModel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class MedicationPriceHistory {
    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "medication", nullable = false)
    private Integer medication_id;


    @Column(name = "price", nullable = false)
    private double price;


    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "endDate", nullable = false)
    private LocalDate endDate;

    @Column(name = "pharmacy", nullable = false)
    private Integer pharmacy_id;

    public MedicationPriceHistory() {
    }

    public MedicationPriceHistory(Integer id, Integer medication_id, double price, LocalDate startDate, LocalDate endDate, Integer pharmacy_id) {
        this.id = id;
        this.medication_id = medication_id;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pharmacy_id = pharmacy_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedication_id() {
        return medication_id;
    }

    public void setMedication_id(Integer medication_id) {
        this.medication_id = medication_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getPharmacy_id() {
        return pharmacy_id;
    }

    public void setPharmacy_id(Integer pharmacy_id) {
        this.pharmacy_id = pharmacy_id;
    }
}
