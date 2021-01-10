package com.isaproject.isaproject.Model.Examinations;

import com.isaproject.isaproject.Model.Entity;

import java.time.LocalDate;

public class EPrescription extends Entity {
    private int patientId;
    private long code;
    private LocalDate date;
    private String status;

    public EPrescription() {
        super();
    }
    public EPrescription(int id, int patientId, long code, LocalDate date, String status) {
        super(id);
        this.patientId = patientId;
        this.code = code;
        this.date = date;
        this.status = status;
    }

    public EPrescription(int patientId, long code, LocalDate date, String status) {
        this.patientId = patientId;
        this.code = code;
        this.date = date;
        this.status = status;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
