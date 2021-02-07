package com.isaproject.isaproject.Model.Examinations;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.Pharmacist;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ExaminationSchedule {
    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;


    @JsonBackReference(value="dermatologist-schedule")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "dermatologist_id", referencedColumnName = "id", nullable = false, unique = false)
    private Dermatologist dermatologist;

    @JsonBackReference(value="pharmacy-schedule")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id", referencedColumnName = "id", nullable = true, unique = false)
    private Pharmacy pharmacy;



    @Column(name = "date", nullable = true)
    private LocalDate date;


    @Column(name = "startTime", nullable = true)
    private LocalTime startTime;


    @Column(name = "duration", nullable = true)
    private Long duration;


    @Column(name = "finished", nullable = true)
    private Boolean finished;


    @Column(name = "price", nullable = true)
    private double price;


    public ExaminationSchedule() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
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