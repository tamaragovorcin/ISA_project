package com.isaproject.isaproject.Model.Examinations;

import com.isaproject.isaproject.Model.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Consulting extends Entity {
    private int pharmacistId;
    private int patientId;
    private LocalDate date;
    private LocalTime startTime;
    private double duration;
    private double price;
    private Boolean cancelled;
    private Boolean showedUp;
    private String information;


    public Consulting() {
        super();
    }

    public Consulting(int id, int pharmacistId, int patientId, LocalDate date, LocalTime startTime, double duration, double price, Boolean cancelled, Boolean showedUp, String information) {
        super(id);
        this.pharmacistId = pharmacistId;
        this.patientId = patientId;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.price = price;
        this.cancelled = cancelled;
        this.showedUp = showedUp;
        this.information = information;
    }

    public Consulting(int pharmacistId, int patientId, LocalDate date, LocalTime startTime, double duration, double price, Boolean cancelled, Boolean showedUp, String information) {
        this.pharmacistId = pharmacistId;
        this.patientId = patientId;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.price = price;
        this.cancelled = cancelled;
        this.showedUp = showedUp;
        this.information = information;
    }

    public int getPharmacistId() {
        return pharmacistId;
    }

    public void setPharmacistId(int pharmacistId) {
        this.pharmacistId = pharmacistId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
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

    public Boolean getShowedUp() {
        return showedUp;
    }

    public void setShowedUp(Boolean showedUp) {
        this.showedUp = showedUp;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
