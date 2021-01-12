package com.isaproject.isaproject.Model.Examinations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class EPrescription {


    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;


    @Column(name = "patientId", nullable = false)
    private int patientId;


    @Column(name = "code", nullable = false)
    private long code;


    @Column(name = "date", nullable = false)
    private LocalDate date;


    @Column(name = "status", nullable = false)
    private String status;

    public EPrescription() {

    }

    public EPrescription(Integer id, int patientId, long code, LocalDate date, String status) {
        this.id = id;
        this.patientId = patientId;
        this.code = code;
        this.date = date;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
