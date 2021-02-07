package com.isaproject.isaproject.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class TermsFrontDTO {
    private  String dermatologistName;
    private  String dermatologistSurname;
    private LocalDate date;
    private LocalTime time;
    private Long duration;
    private double price;

    public TermsFrontDTO(String dermatologistName, String dermatologistSurname, LocalDate date, LocalTime time, Long duration, double price) {
        this.dermatologistName = dermatologistName;
        this.dermatologistSurname = dermatologistSurname;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.price = price;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public TermsFrontDTO() {
    }

    public String getDermatologistName() {
        return dermatologistName;
    }

    public void setDermatologistName(String dermatologistName) {
        this.dermatologistName = dermatologistName;
    }

    public String getDermatologistSurname() {
        return dermatologistSurname;
    }

    public void setDermatologistSurname(String dermatologistSurname) {
        this.dermatologistSurname = dermatologistSurname;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
