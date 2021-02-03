package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Dermatologist;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class ExaminationScheduleDTO {

    private Dermatologist dermatologist;

    private Pharmacy pharmacy;

    private LocalDate date;

    private LocalTime startTime;

    private double duration;

    private Boolean finished;

    private double price;

    public ExaminationScheduleDTO() {
    }

    public ExaminationScheduleDTO(Dermatologist dermatologist, Pharmacy pharmacy, LocalDate date, LocalTime startTime, double duration, Boolean finished, double price) {
        this.dermatologist = dermatologist;
        this.pharmacy = pharmacy;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.finished = finished;
        this.price = price;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
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

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
