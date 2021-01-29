package com.isaproject.isaproject.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;

import javax.persistence.*;
import java.time.LocalDate;

public class MedicationPriceDTO {

    private Medication medication;
    private double price;
    private LocalDate date;
    private Pharmacy pharmacy;

    public MedicationPriceDTO(Medication medication, double price, LocalDate date, Pharmacy pharmacy) {
        this.medication = medication;
        this.price = price;
        this.date = date;
        this.pharmacy = pharmacy;
    }

    public MedicationPriceDTO() {
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
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

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}
