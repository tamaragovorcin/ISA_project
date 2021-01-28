package com.isaproject.isaproject.DTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class OrderReviewDTO {
    private int id;
    private LocalDate date;
    private String status;
    private List<MedicationsInOrderReviewDTO> medications;
    private String pharmacyName;


    public OrderReviewDTO() {
    }

    public OrderReviewDTO(int id, LocalDate date, String status, List<MedicationsInOrderReviewDTO> medications, String pharmacyName) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.medications = medications;
        this.pharmacyName = pharmacyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MedicationsInOrderReviewDTO> getMedications() {
        return medications;
    }

    public void setMedications(List<MedicationsInOrderReviewDTO> medications) {
        this.medications = medications;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }
}
