package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;

import java.time.LocalDate;
import java.util.Date;

public class ActionsDTO {
    private Integer pharmacy;

    private String description;

    private LocalDate expiryDate;

    public ActionsDTO() {
    }

    public ActionsDTO(Integer pharmacy, String description, LocalDate expiryDate) {
        this.pharmacy = pharmacy;
        this.description = description;
        this.expiryDate = expiryDate;
    }

    public Integer getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Integer pharmacy) {
        this.pharmacy = pharmacy;
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