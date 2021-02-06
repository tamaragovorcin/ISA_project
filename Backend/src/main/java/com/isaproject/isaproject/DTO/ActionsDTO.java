package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import java.util.Date;

public class ActionsDTO {
    private Pharmacy pharmacy;

    private String description;

    private Date expiryDate;

    public ActionsDTO() {
    }

    public ActionsDTO(Pharmacy pharmacy, String description, Date expiryDate) {
        this.pharmacy = pharmacy;
        this.description = description;
        this.expiryDate = expiryDate;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
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