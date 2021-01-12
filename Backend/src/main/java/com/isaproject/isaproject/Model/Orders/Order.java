package com.isaproject.isaproject.Model.Orders;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity

@Table(name="order_table")
public class Order  {

    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "pharmacyId", nullable = false)
    private int pharmacyId;


    @Column(name = "adminId", nullable = false)
    private int adminId;


    @Column(name = "date", nullable = false)
    private LocalDate date;


    @Column(name = "status", nullable = false)
    private String status;

    public Order() {
    }

    public Order(Integer id, int pharmacyId, int adminId, LocalDate date, String status) {
        this.id = id;
        this.pharmacyId = pharmacyId;
        this.adminId = adminId;
        this.date = date;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
