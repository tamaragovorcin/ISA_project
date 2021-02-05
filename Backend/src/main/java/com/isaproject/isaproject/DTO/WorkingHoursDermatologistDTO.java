package com.isaproject.isaproject.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.Pharmacist;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
public class WorkingHoursDermatologistDTO {
    private Dermatologist dermatologist;
    private Integer pharmacyId;
    private LocalTime startTimeMonday;
    private LocalTime startTimeTuesday;
    private LocalTime startTimeWednesday;
    private LocalTime startTimeThursday;
    private LocalTime startTimeFriday;
    private LocalTime startTimeSaturday;
    private LocalTime startTimeSunday;
    private LocalTime endTimeMonday;
    private LocalTime endTimeTuesday;
    private LocalTime endTimeWednesday;
    private LocalTime endTimeThursday;
    private LocalTime endTimeFriday;
    private LocalTime endTimeSaturday;
    private LocalTime endTimeSunday;

    public WorkingHoursDermatologistDTO() {
    }
    @JsonIgnore
    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }
    @JsonIgnore
    public Integer getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Integer pharmacyId) {
        this.pharmacyId = pharmacyId;
    }
    @JsonIgnore
    public LocalTime getStartTimeMonday() {
        return startTimeMonday;
    }

    public void setStartTimeMonday(LocalTime startTimeMonday) {
        this.startTimeMonday = startTimeMonday;
    }
    @JsonIgnore
    public LocalTime getStartTimeTuesday() {
        return startTimeTuesday;
    }

    public void setStartTimeTuesday(LocalTime startTimeTuesday) {
        this.startTimeTuesday = startTimeTuesday;
    }
    @JsonIgnore
    public LocalTime getStartTimeWednesday() {
        return startTimeWednesday;
    }

    public void setStartTimeWednesday(LocalTime startTimeWednesday) {
        this.startTimeWednesday = startTimeWednesday;
    }
    @JsonIgnore
    public LocalTime getStartTimeThursday() {
        return startTimeThursday;
    }

    public void setStartTimeThursday(LocalTime startTimeThursday) {
        this.startTimeThursday = startTimeThursday;
    }
    @JsonIgnore
    public LocalTime getStartTimeFriday() {
        return startTimeFriday;
    }

    public void setStartTimeFriday(LocalTime startTimeFriday) {
        this.startTimeFriday = startTimeFriday;
    }
    @JsonIgnore
    public LocalTime getStartTimeSaturday() {
        return startTimeSaturday;
    }

    public void setStartTimeSaturday(LocalTime startTimeSaturday) {
        this.startTimeSaturday = startTimeSaturday;
    }
    @JsonIgnore
    public LocalTime getStartTimeSunday() {
        return startTimeSunday;
    }

    public void setStartTimeSunday(LocalTime startTimeSunday) {
        this.startTimeSunday = startTimeSunday;
    }
    @JsonIgnore
    public LocalTime getEndTimeMonday() {
        return endTimeMonday;
    }

    public void setEndTimeMonday(LocalTime endTimeMonday) {
        this.endTimeMonday = endTimeMonday;
    }
    @JsonIgnore
    public LocalTime getEndTimeTuesday() {
        return endTimeTuesday;
    }

    public void setEndTimeTuesday(LocalTime endTimeTuesday) {
        this.endTimeTuesday = endTimeTuesday;
    }
    @JsonIgnore
    public LocalTime getEndTimeWednesday() {
        return endTimeWednesday;
    }

    public void setEndTimeWednesday(LocalTime endTimeWednesday) {
        this.endTimeWednesday = endTimeWednesday;
    }
    @JsonIgnore
    public LocalTime getEndTimeThursday() {
        return endTimeThursday;
    }

    public void setEndTimeThursday(LocalTime endTimeThursday) {
        this.endTimeThursday = endTimeThursday;
    }
    @JsonIgnore
    public LocalTime getEndTimeFriday() {
        return endTimeFriday;
    }

    public void setEndTimeFriday(LocalTime endTimeFriday) {
        this.endTimeFriday = endTimeFriday;
    }
    @JsonIgnore
    public LocalTime getEndTimeSaturday() {
        return endTimeSaturday;
    }

    public void setEndTimeSaturday(LocalTime endTimeSaturday) {
        this.endTimeSaturday = endTimeSaturday;
    }
    @JsonIgnore
    public LocalTime getEndTimeSunday() {
        return endTimeSunday;
    }

    public void setEndTimeSunday(LocalTime endTimeSunday) {
        this.endTimeSunday = endTimeSunday;
    }


    @Override
    public String toString() {
        return "WorkingHoursDermatologistDTO{" +
                "dermatologist=" + dermatologist +
                ", pharmacyId=" + pharmacyId +
                ", startTimeMonday=" + startTimeMonday +
                ", startTimeTuesday=" + startTimeTuesday +
                ", startTimeWednesday=" + startTimeWednesday +
                ", startTimeThursday=" + startTimeThursday +
                ", startTimeFriday=" + startTimeFriday +
                ", startTimeSaturday=" + startTimeSaturday +
                ", startTimeSunday=" + startTimeSunday +
                ", endTimeMonday=" + endTimeMonday +
                ", endTimeTuesday=" + endTimeTuesday +
                ", endTimeWednesday=" + endTimeWednesday +
                ", endTimeThursday=" + endTimeThursday +
                ", endTimeFriday=" + endTimeFriday +
                ", endTimeSaturday=" + endTimeSaturday +
                ", endTimeSunday=" + endTimeSunday +
                '}';
    }
}
