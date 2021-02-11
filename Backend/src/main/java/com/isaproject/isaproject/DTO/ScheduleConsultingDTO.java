package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.Pharmacist;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleConsultingDTO {
    private Integer pharmacist;
    private Integer patient;
    private LocalDate date;
    private LocalTime startTime;

    public ScheduleConsultingDTO() {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
}
