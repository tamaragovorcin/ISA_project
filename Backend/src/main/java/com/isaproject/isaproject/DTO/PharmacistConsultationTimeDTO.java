package com.isaproject.isaproject.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class PharmacistConsultationTimeDTO {

    private LocalDate date;

    private LocalTime time;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
