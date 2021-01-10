package com.isaproject.isaproject.Model.Orders;

import com.isaproject.isaproject.Model.Entity;

import java.time.LocalDate;
import java.util.Date;

public class Order extends Entity {
    private int pharmacyId;
    private int adminId;
    private LocalDate date;
    private String status;

    public Order() {
        super();
    }

    public Order(int id, int pharmacyId, int adminId, LocalDate date, String status) {
        super(id);
        this.pharmacyId = pharmacyId;
        this.adminId = adminId;
        this.date = date;
        this.status = status;
    }

    public Order(int pharmacyId, int adminId, LocalDate date, String status) {
        this.pharmacyId = pharmacyId;
        this.adminId = adminId;
        this.date = date;
        this.status = status;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
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
