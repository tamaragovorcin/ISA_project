package com.isaproject.isaproject.DTO;

import java.time.LocalDate;
import java.util.Date;

public class ActionsDTO {
    private Integer pharmacyId;

    private String description;

    private Date expiryDate;

    public ActionsDTO() {
    }

    public ActionsDTO(Integer pharmacyId, String description, Date expiryDate) {
        this.pharmacyId = pharmacyId;
        this.description = description;
        this.expiryDate = expiryDate;
    }

    public Integer getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Integer pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
