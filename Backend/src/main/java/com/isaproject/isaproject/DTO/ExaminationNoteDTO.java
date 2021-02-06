package com.isaproject.isaproject.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class ExaminationNoteDTO {
    private Integer examinationId;
    private Integer patientId;
    private String namePatient;
    private String surnamePatient;
    private String pharmacy;
    private LocalDate date;
    private LocalTime startTime;

    public ExaminationNoteDTO(Integer examinationId, Integer patientId, String namePatient, String surnamePatient, String pharmacy, LocalDate date, LocalTime startTime) {
        this.examinationId = examinationId;
        this.patientId = patientId;
        this.namePatient = namePatient;
        this.surnamePatient = surnamePatient;
        this.pharmacy = pharmacy;
        this.date = date;
        this.startTime = startTime;
    }

    public ExaminationNoteDTO() {

    }

    public Integer getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getNamePatient() {
        return namePatient;
    }

    public void setNamePatient(String namePatient) {
        this.namePatient = namePatient;
    }

    public String getSurnamePatient() {
        return surnamePatient;
    }

    public void setSurnamePatient(String surnamePatient) {
        this.surnamePatient = surnamePatient;
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
}
