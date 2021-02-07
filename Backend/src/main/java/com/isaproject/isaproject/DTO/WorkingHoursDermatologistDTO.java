package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import java.time.LocalTime;

public class WorkingHoursDermatologistDTO {
    private Integer dermatologistId;
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

    public WorkingHoursDermatologistDTO() { }

    public Integer getDermatologistId() {
        return dermatologistId;
    }

    public void setDermatologistId(Integer dermatologistId) {
        this.dermatologistId = dermatologistId;
    }

    public Integer getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Integer pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public LocalTime getStartTimeMonday() {
        return startTimeMonday;
    }

    public void setStartTimeMonday(LocalTime startTimeMonday) {
        this.startTimeMonday = startTimeMonday;
    }

    public LocalTime getStartTimeTuesday() {
        return startTimeTuesday;
    }

    public void setStartTimeTuesday(LocalTime startTimeTuesday) {
        this.startTimeTuesday = startTimeTuesday;
    }

    public LocalTime getStartTimeWednesday() {
        return startTimeWednesday;
    }

    public void setStartTimeWednesday(LocalTime startTimeWednesday) {
        this.startTimeWednesday = startTimeWednesday;
    }

    public LocalTime getStartTimeThursday() {
        return startTimeThursday;
    }

    public void setStartTimeThursday(LocalTime startTimeThursday) {
        this.startTimeThursday = startTimeThursday;
    }

    public LocalTime getStartTimeFriday() {
        return startTimeFriday;
    }

    public void setStartTimeFriday(LocalTime startTimeFriday) {
        this.startTimeFriday = startTimeFriday;
    }

    public LocalTime getStartTimeSaturday() {
        return startTimeSaturday;
    }

    public void setStartTimeSaturday(LocalTime startTimeSaturday) {
        this.startTimeSaturday = startTimeSaturday;
    }

    public LocalTime getStartTimeSunday() {
        return startTimeSunday;
    }

    public void setStartTimeSunday(LocalTime startTimeSunday) {
        this.startTimeSunday = startTimeSunday;
    }

    public LocalTime getEndTimeMonday() {
        return endTimeMonday;
    }

    public void setEndTimeMonday(LocalTime endTimeMonday) {
        this.endTimeMonday = endTimeMonday;
    }

    public LocalTime getEndTimeTuesday() {
        return endTimeTuesday;
    }

    public void setEndTimeTuesday(LocalTime endTimeTuesday) {
        this.endTimeTuesday = endTimeTuesday;
    }

    public LocalTime getEndTimeWednesday() {
        return endTimeWednesday;
    }

    public void setEndTimeWednesday(LocalTime endTimeWednesday) {
        this.endTimeWednesday = endTimeWednesday;
    }

    public LocalTime getEndTimeThursday() {
        return endTimeThursday;
    }

    public void setEndTimeThursday(LocalTime endTimeThursday) {
        this.endTimeThursday = endTimeThursday;
    }

    public LocalTime getEndTimeFriday() {
        return endTimeFriday;
    }

    public void setEndTimeFriday(LocalTime endTimeFriday) {
        this.endTimeFriday = endTimeFriday;
    }

    public LocalTime getEndTimeSaturday() {
        return endTimeSaturday;
    }

    public void setEndTimeSaturday(LocalTime endTimeSaturday) {
        this.endTimeSaturday = endTimeSaturday;
    }

    public LocalTime getEndTimeSunday() {
        return endTimeSunday;
    }

    public void setEndTimeSunday(LocalTime endTimeSunday) {
        this.endTimeSunday = endTimeSunday;
    }
}