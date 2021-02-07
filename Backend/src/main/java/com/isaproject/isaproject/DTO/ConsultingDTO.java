package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.Pharmacist;
import java.time.LocalDate;
import java.time.LocalTime;

public class ConsultingDTO {
    //private Pharmacist pharmacist;
    //private Patient patient;
    private Integer patient;
    private Integer pharmacist;
    private LocalDate date;
    private LocalTime startTime;
    private double duration;
    private double price;
    private Boolean cancelled;

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }

    public Integer getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Integer pharmacist) {
        this.pharmacist = pharmacist;
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

    public ConsultingDTO(Integer patient, Integer pharmacist, LocalDate date, LocalTime startTime, double duration, double price, Boolean cancelled, Boolean showedUp, String information) {
        this.patient = patient;
        this.pharmacist = pharmacist;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.price = price;
        this.cancelled = cancelled;
        this.showedUp = showedUp;
        this.information = information;
    }

    public ConsultingDTO(){ }
}
