package com.isaproject.isaproject.Model.Schedule;

import com.isaproject.isaproject.Model.Entity;

import java.time.LocalDate;
import java.util.Date;

public class HolidayScheduleDermatologist extends Entity {
    private int dermatologistsId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean approved;
    private String massage;

    public HolidayScheduleDermatologist() {
        super();
    }
    public HolidayScheduleDermatologist(int id, int dermatologistsId, LocalDate startDate, LocalDate endDate, Boolean approved, String massage) {
        super(id);
        this.dermatologistsId = dermatologistsId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approved = approved;
        this.massage = massage;
    }

    public HolidayScheduleDermatologist(int dermatologistsId, LocalDate startDate, LocalDate endDate, Boolean approved, String massage) {
        this.dermatologistsId = dermatologistsId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approved = approved;
        this.massage = massage;

    }

    public int getDermatologistsId() {
        return dermatologistsId;
    }

    public void setDermatologistsId(int dermatologistsId) {
        this.dermatologistsId = dermatologistsId;
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
