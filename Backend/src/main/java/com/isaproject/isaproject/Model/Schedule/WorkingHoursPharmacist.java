package com.isaproject.isaproject.Model.Schedule;

import com.isaproject.isaproject.Model.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class WorkingHoursPharmacist extends Entity {
    private LocalDate date;
    private int pharmacistId;
    private LocalTime startTime;
    private LocalTime endTime;

    public WorkingHoursPharmacist() {
        super();
    }
    public WorkingHoursPharmacist(int id, LocalDate date, int pharmacistId, LocalTime startTime, LocalTime endTime) {
        super(id);
        this.date = date;
        this.pharmacistId = pharmacistId;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public WorkingHoursPharmacist(LocalDate date, int pharmacistId, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.pharmacistId = pharmacistId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPharmacistId() {
        return pharmacistId;
    }

    public void setPharmacistId(int pharmacistId) {
        this.pharmacistId = pharmacistId;
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
