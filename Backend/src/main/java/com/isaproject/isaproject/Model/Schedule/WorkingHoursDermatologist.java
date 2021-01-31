package com.isaproject.isaproject.Model.Schedule;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Dermatologist;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
public class WorkingHoursDermatologist  {
    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "date", nullable = true)
    private LocalDate date;


    @JsonBackReference(value = "pharmacy-dermatologist")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id", referencedColumnName = "id", nullable = true, unique = false)
    private Pharmacy pharmacy;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "dermatologist_id", referencedColumnName = "id", nullable = false, unique = false)
    private Dermatologist dermatologist;


    @Column(name = "startTime", nullable = true)
    private LocalTime startTime;


    @Column(name = "endTime", nullable = true)
    private LocalTime endTime;

    public WorkingHoursDermatologist() {
        super();
    }

    public WorkingHoursDermatologist(Integer id, LocalDate date, Pharmacy pharmacy, Dermatologist dermatologist, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.date = date;
        this.pharmacy = pharmacy;
        this.dermatologist = dermatologist;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
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
