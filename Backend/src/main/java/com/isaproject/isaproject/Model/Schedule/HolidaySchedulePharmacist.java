package com.isaproject.isaproject.Model.Schedule;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class HolidaySchedulePharmacist {
    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "pharmacistId", nullable = false)
    private int pharmacistId;


    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;


    @Column(name = "endDate", nullable = false)
    private LocalDate endDate;


    @Column(name = "approved", nullable = false)
    private Boolean approved;


    @Column(name = "massage", nullable = false)
    private String massage;

    public HolidaySchedulePharmacist() {
        super();
    }

    public HolidaySchedulePharmacist(Integer id, int pharmacistId, LocalDate startDate, LocalDate endDate, Boolean approved, String massage) {
        this.id = id;
        this.pharmacistId = pharmacistId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approved = approved;
        this.massage = massage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPharmacistId() {
        return pharmacistId;
    }

    public void setPharmacistId(int pharmacistId) {
        this.pharmacistId = pharmacistId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
