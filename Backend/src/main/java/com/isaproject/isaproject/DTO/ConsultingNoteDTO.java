package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.Pharmacist;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConsultingNoteDTO {
    private Integer consultingId;
    private Integer patientId;
    private String namePatient;
    private String surnamePatient;
    private LocalDate date;
    private LocalTime startTime;


    public ConsultingNoteDTO() {

    }

    public ConsultingNoteDTO(Integer consultingId, Integer patientId, String namePatient, String surnamePatient, LocalDate date, LocalTime startTime) {
        this.consultingId = consultingId;
        this.patientId = patientId;
        this.namePatient = namePatient;
        this.surnamePatient = surnamePatient;
        this.date = date;
        this.startTime = startTime;
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

    public Integer getConsultingId() {
        return consultingId;
    }

    public void setConsultingId(Integer consultingId) {
        this.consultingId = consultingId;
    }
}
