package com.isaproject.isaproject.Model.Examinations;

import com.isaproject.isaproject.Model.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class ExaminationSchedule extends Entity {
    private int dermatologistId;
    private int pharmacyId;
    private LocalDate date;
    private LocalTime startTime;
    private double duration;
    private Boolean finished;
    private double price;


    public ExaminationSchedule() {
        super();
    }

    public ExaminationSchedule(int id, int dermatologistId, int pharmacyId, LocalDate date, LocalTime startTime, double duration, Boolean finished, double price) {
        super(id);
        this.dermatologistId = dermatologistId;
        this.pharmacyId = pharmacyId;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.finished = finished;
        this.price = price;
    }

    public ExaminationSchedule(int dermatologistId, int pharmacyId, LocalDate date, LocalTime startTime, double duration, Boolean finished, double price) {
        this.dermatologistId = dermatologistId;
        this.pharmacyId = pharmacyId;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.finished = finished;
        this.price = price;
    }

    public int getDermatologistId() {
        return dermatologistId;
    }

    public void setDermatologistId(int dermatologistId) {
        this.dermatologistId = dermatologistId;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
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
