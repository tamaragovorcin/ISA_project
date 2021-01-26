package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Dermatologist;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class WorkingHoursDermatologistDTO {
    private LocalDate date;
    private Pharmacy pharmacy;
    private Dermatologist dermatologist;
    private LocalTime startTime;
    private LocalTime endTime;

    public WorkingHoursDermatologistDTO() {
    }

    public WorkingHoursDermatologistDTO(LocalDate date, Pharmacy pharmacy, Dermatologist dermatologist, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.pharmacy = pharmacy;
        this.dermatologist = dermatologist;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
