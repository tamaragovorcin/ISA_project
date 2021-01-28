package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.Pharmacist;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConsultingDTO {
    private Pharmacist pharmacist;
    private Patient patient;
    private LocalDate date;
    private LocalTime startTime;
    private double duration;
    private double price;
    private Boolean cancelled;

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void setShowedUp(Boolean showedUp) {
        this.showedUp = showedUp;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public double getDuration() {
        return duration;
    }

    public double getPrice() {
        return price;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public Boolean getShowedUp() {
        return showedUp;
    }

    public String getInformation() {
        return information;
    }

    private Boolean showedUp;
    private String information;

    public ConsultingDTO(Pharmacist pharmacist, Patient patient, LocalDate date, LocalTime startTime, double duration, double price, Boolean cancelled, Boolean showedUp, String information) {
        this.pharmacist = pharmacist;
        this.patient = patient;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.price = price;
        this.cancelled = cancelled;
        this.showedUp = showedUp;
        this.information = information;
    }
    public ConsultingDTO(){

    }
}
