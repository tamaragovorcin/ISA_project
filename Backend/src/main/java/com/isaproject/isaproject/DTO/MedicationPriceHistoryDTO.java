package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Medicine.Medication;

import java.time.LocalDate;

public class MedicationPriceHistoryDTO {

    private Integer medication;
    private double price;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer pharmacy;

    public MedicationPriceHistoryDTO() {
    }

    public MedicationPriceHistoryDTO(Integer medication, double price, LocalDate startDate, LocalDate endDate, Integer pharmacy) {
        this.medication = medication;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pharmacy = pharmacy;
    }

    public Integer getMedication() {
        return medication;
    }

    public void setMedication(Integer medication) {
        this.medication = medication;
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

    public Integer getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Integer pharmacy) {
        this.pharmacy = pharmacy;
    }
}
