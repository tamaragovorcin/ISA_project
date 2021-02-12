package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Users.Pharmacist;

import java.time.LocalDate;
import java.time.LocalTime;

public class PharmacistConsFrontDTO {

    private LocalTime time;

    private LocalTime start;

    private LocalDate date;

    private Integer pharmacist;

    private Integer patient;


    public PharmacistConsFrontDTO() {
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Integer pharmacist) {
        this.pharmacist = pharmacist;
    }

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }
}

