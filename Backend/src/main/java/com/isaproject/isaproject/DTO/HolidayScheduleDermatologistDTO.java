package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Users.Dermatologist;


import java.time.LocalDate;

public class HolidayScheduleDermatologistDTO {
    private Dermatologist dermatologist;
    private LocalDate startDate;
    private LocalDate endDate;
    private String type;//HOLIDAY or ABSENCE
    private String approved;//APPROVED or REFUSED or WAITING_FOR_RESPONSE


    public HolidayScheduleDermatologistDTO(Dermatologist dermatologist, LocalDate startDate, LocalDate endDate, String type, String approved) {
        this.dermatologist = dermatologist;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.approved = approved;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
