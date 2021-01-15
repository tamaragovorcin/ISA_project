package com.isaproject.isaproject.Model.Schedule;

import com.isaproject.isaproject.Model.Users.Pharmacist;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
@Entity
public class WorkingHoursPharmacist {
    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "date", nullable = true)
    private LocalDate date;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacist_id", referencedColumnName = "id", nullable = false, unique = false)
    private Pharmacist pharmacist;


    @Column(name = "startTime", nullable = true)
    private LocalTime startTime;


    @Column(name = "endTime", nullable = true)
    private LocalTime endTime;

    public WorkingHoursPharmacist() {
        super();
    }

    public WorkingHoursPharmacist(Integer id, LocalDate date, Pharmacist pharmacist, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.date = date;
        this.pharmacist = pharmacist;
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

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
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

