package com.isaproject.isaproject.Model.Examinations;

import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.Pharmacist;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Consulting implements Serializable {


    @Id
    @GeneratedValue
    @Column(name="consulting_id", unique=true, nullable=false)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacist_id", referencedColumnName = "id", nullable = false, unique = false)
    private Pharmacist pharmacist;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false, unique = false)
    private Patient patient;


    /*@ManyToMany(mappedBy = "consultings")
    private Set<Patient> patients = new HashSet<Patient>();*/


    @Column(name = "date", nullable = true)
    private LocalDate date;


    @Column(name = "startTime", nullable = true)
    private LocalTime startTime;


    @Column(name = "duration", nullable = true)
    private double duration;


    @Column(name = "price", nullable = true)
    private double price;


    @Column(name = "cancelled", nullable = true)
    private Boolean cancelled;


    @Column(name = "showedUp", nullable = true)
    private Boolean showedUp;


    @Column(name = "information", nullable = true)
    private String information;


    public Consulting() {

    }

    public Consulting(Integer id, Pharmacist pharmacist, Patient patient, LocalDate date, LocalTime startTime, double duration, double price, Boolean cancelled, Boolean showedUp, String information) {
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
