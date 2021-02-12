package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.Pharmacist;

import java.time.LocalDate;
import java.time.LocalTime;

public class NewConsultingDTO {
    private Integer pharmacistId;
    private Integer patientId;
    private LocalDate date;
    private LocalTime startTime;
    private double duration;
    private double price;
    private Boolean cancelled;

    public NewConsultingDTO() { }

    public NewConsultingDTO(Integer pharmacistId, Integer patientId, LocalDate date, LocalTime startTime, double duration, double price, Boolean cancelled) {
        this.pharmacistId = pharmacistId;
        this.patientId = patientId;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.price = price;
        this.cancelled = cancelled;
    }

    public Integer getPharmacistId() {
        return pharmacistId;
    }

    public void setPharmacistId(Integer pharmacistId) {
        this.pharmacistId = pharmacistId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
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

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }
}
