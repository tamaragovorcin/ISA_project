package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.Patient;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleExaminationMyDTO {
    private Patient patient;

    private String pharmacy;

    private LocalDate date;

    private LocalTime startTime;

    private double duration;

    private double price;

    public ScheduleExaminationMyDTO(Patient patient, String pharmacy, LocalDate date, LocalTime startTime, double duration, double price) {
        this.patient = patient;
        this.pharmacy = pharmacy;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.price = price;
    }

    public ScheduleExaminationMyDTO() {}

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(String pharmacy) {
        this.pharmacy = pharmacy;
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
}
