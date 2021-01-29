package com.isaproject.isaproject.DTO;



import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.Users.Patient;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalTime;

public class ExaminationFrontDTO {

    private Integer patientId;

    private Integer id;

    private String dermatologistFirst;

    private String DermatologistLast;

    private String pharmacy;

    private LocalDate date;

    private LocalTime startTime;

    private double duration;

    private double price;




    public ExaminationFrontDTO() {
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDermatologistFirst() {
        return dermatologistFirst;
    }

    public void setDermatologistFirst(String dermatologistFirst) {
        this.dermatologistFirst = dermatologistFirst;
    }

    public String getDermatologistLast() {
        return DermatologistLast;
    }

    public void setDermatologistLast(String dermatologistLast) {
        DermatologistLast = dermatologistLast;
    }

    public String getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(String pharmacy) {
        this.pharmacy = pharmacy;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
