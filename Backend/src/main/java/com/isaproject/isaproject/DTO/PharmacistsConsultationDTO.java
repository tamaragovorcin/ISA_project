package com.isaproject.isaproject.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class PharmacistsConsultationDTO {

    private Integer pharmacyId;

    private LocalTime time;

    private LocalDate date;

    public Integer getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Integer pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
