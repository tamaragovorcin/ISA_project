package com.isaproject.isaproject.Model.Pharmacy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="actions_table")
public class Actions{

    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "pharmacyId", nullable = true)
    private int pharmacyId;


    @Column(name = "description", nullable = true)
    private String description;


    @Column(name = "expiryDate", nullable = true)
    private LocalDate expiryDate;

    public Actions() {
    }

    public Actions(Integer id, int pharmacyId, String description, LocalDate expiryDate) {
        this.id = id;
        this.pharmacyId = pharmacyId;
        this.description = description;
        this.expiryDate = expiryDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
