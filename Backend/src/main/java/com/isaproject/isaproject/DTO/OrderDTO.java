package com.isaproject.isaproject.DTO;


import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.PharmacyAdmin;

import javax.persistence.*;
import java.time.LocalDate;


public class OrderDTO {
    private Pharmacy pharmacy;
    private PharmacyAdmin pharmacyAdmin;
    private LocalDate date;
    private String status;

    public OrderDTO() {
    }

    public OrderDTO(Pharmacy pharmacy, PharmacyAdmin pharmacyAdmin, LocalDate date, String status) {
        this.pharmacy = pharmacy;
        this.pharmacyAdmin = pharmacyAdmin;
        this.date = date;
        this.status = status;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public PharmacyAdmin getPharmacyAdmin() {
        return pharmacyAdmin;
    }

    public void setPharmacyAdmin(PharmacyAdmin pharmacyAdmin) {
        this.pharmacyAdmin = pharmacyAdmin;
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
}
