package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Users.Pharmacist;

import java.time.LocalDate;

public class HolidaySchedulePharmacistFrontDTO {
    private Integer scheduleId;
    private String pharmacist;
    private String email;
    private LocalDate startDate;
    private LocalDate endDate;
    private String type;
    private String approved;

    public HolidaySchedulePharmacistFrontDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(String pharmacist) {
        this.pharmacist = pharmacist;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }
}
