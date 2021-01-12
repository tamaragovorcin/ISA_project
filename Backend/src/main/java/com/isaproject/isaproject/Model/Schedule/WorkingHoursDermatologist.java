package com.isaproject.isaproject.Model.Schedule;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
public class WorkingHoursDermatologist  {
    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "date", nullable = false)
    private LocalDate date;


    @Column(name = "pharmacyId", nullable = false)
    private int pharmacyId;


    @Column(name = "dermatologistId", nullable = false)
    private int dermatologistId;


    @Column(name = "startTime", nullable = false)
    private LocalTime startTime;


    @Column(name = "endTime", nullable = false)
    private LocalTime endTime;

    public WorkingHoursDermatologist() {
        super();
    }

    public WorkingHoursDermatologist(Integer id, LocalDate date, int pharmacyId, int dermatologistId, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.date = date;
        this.pharmacyId = pharmacyId;
        this.dermatologistId = dermatologistId;
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

    public int getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public int getDermatologistId() {
        return dermatologistId;
    }

    public void setDermatologistId(int dermatologistId) {
        this.dermatologistId = dermatologistId;
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
