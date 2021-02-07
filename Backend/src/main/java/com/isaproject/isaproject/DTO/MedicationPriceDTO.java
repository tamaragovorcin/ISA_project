package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import java.time.LocalDate;

public class MedicationPriceDTO {

    private Medication medication;
    private double price;
    private LocalDate date;
    private Integer pharmacy;

    public MedicationPriceDTO(Medication medication, double price, LocalDate date, Integer pharmacy) {
        this.medication = medication;
        this.price = price;
        this.date = date;
        this.pharmacy = pharmacy;
    }

    public MedicationPriceDTO() {}

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

    public Integer getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Integer pharmacy) {
        this.pharmacy = pharmacy;
    }
}