package com.isaproject.isaproject.Model.Schedule;

import com.isaproject.isaproject.Model.Entity;

import java.time.LocalDate;
import java.time.LocalTime;


public class WorkingHoursDermatologist extends Entity {
    private LocalDate date;
    private int pharmacyId;
    private int dermatologistId;
    private LocalTime startTime;
    private LocalTime endTime;

    public WorkingHoursDermatologist() {
        super();
    }
    public WorkingHoursDermatologist(int id, LocalDate date, int pharmacyId, int dermatologistId, LocalTime startTime, LocalTime endTime) {
        super(id);
        this.date = date;
        this.pharmacyId = pharmacyId;
        this.dermatologistId = dermatologistId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public WorkingHoursDermatologist(LocalDate date, int pharmacyId, int dermatologistId, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.pharmacyId = pharmacyId;
        this.dermatologistId = dermatologistId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public int getDermatologistId() {
        return dermatologistId;
    }

    public void setDermatologistId(int dermatologistId) {
        this.dermatologistId = dermatologistId;
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
