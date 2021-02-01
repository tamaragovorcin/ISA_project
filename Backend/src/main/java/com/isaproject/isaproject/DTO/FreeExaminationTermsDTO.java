package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Dermatologist;

import java.time.LocalDate;
import java.time.LocalTime;

public class FreeExaminationTermsDTO {
    private Integer id;
    private String dermatologistName;
    private String dermatologistSurname;
    private Double dermatologistMark;
    private Integer pharmacyId;
    private LocalDate date;
    private LocalTime startTime;
    private Double duration;
    private Boolean finished;
    private Double price;

    public FreeExaminationTermsDTO(Integer id, String dermatologistName, String dermatologistSurname, Double dermatologistMark, Integer pharmacyId, LocalDate date, LocalTime startTime, double duration, Boolean finished, double price) {
        this.id = id;
        this.dermatologistName = dermatologistName;
        this.dermatologistSurname = dermatologistSurname;
        this.dermatologistMark = dermatologistMark;
        this.pharmacyId = pharmacyId;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.finished = finished;
        this.price = price;
    }

    public FreeExaminationTermsDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getDermatologistMark() {
        return dermatologistMark;
    }

    public void setDermatologistMark(Double dermatologistMark) {
        this.dermatologistMark = dermatologistMark;
    }

    public Integer getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Integer pharmacyId) {
        this.pharmacyId = pharmacyId;
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

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
