package com.isaproject.isaproject.Model.Schedule;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;
@Entity
public class WorkingHoursPharmacist {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "date", nullable = false)
    private LocalDate date;


    @Column(name = "pharmacistId", nullable = false)
    private int pharmacistId;


    @Column(name = "startTime", nullable = false)
    private LocalTime startTime;


    @Column(name = "endTime", nullable = false)
    private LocalTime endTime;

    public WorkingHoursPharmacist() {
        super();
    }

    public WorkingHoursPharmacist(Integer id, LocalDate date, int pharmacistId, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.date = date;
        this.pharmacistId = pharmacistId;
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

    public int getPharmacistId() {
        return pharmacistId;
    }

    public void setPharmacistId(int pharmacistId) {
        this.pharmacistId = pharmacistId;
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

