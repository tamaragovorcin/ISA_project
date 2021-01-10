package com.isaproject.isaproject.Model.Schedule;

import com.isaproject.isaproject.Model.Entity;

import java.time.LocalDate;
import java.util.Date;

public class HolidaySchedulePharmacist extends Entity {
    private int pharmacistId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean approved;
    private String massage;

    public HolidaySchedulePharmacist() {
        super();
    }
    public HolidaySchedulePharmacist(int id, int pharmacistId, LocalDate startDate, LocalDate endDate, Boolean approved, String massage) {
        super(id);
        this.pharmacistId = pharmacistId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approved = approved;
        this.massage = massage;
    }

    public HolidaySchedulePharmacist(int pharmacistId, LocalDate startDate, LocalDate endDate, Boolean approved, String massage) {
        this.pharmacistId = pharmacistId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approved = approved;
        this.massage = massage;
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
