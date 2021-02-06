package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Medicine.Medication;

import java.util.HashSet;
import java.util.Set;

public class Prescription2DTO {
    private Integer examinationId;
    private Set<Medication> medications = new HashSet<Medication>();
    private double durationOfTherapy;

    public Prescription2DTO(Integer examinationId, Set<Medication> medications, double durationOfTherapy) {
        this.examinationId = examinationId;
        this.medications = medications;
        this.durationOfTherapy = durationOfTherapy;
    }

    public Prescription2DTO() {

    }

    public Integer getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public Set<Medication> getMedications() {
        return medications;
    }

    public void setMedications(Set<Medication> medications) {
        this.medications = medications;
    }

    public double getDurationOfTherapy() {
        return durationOfTherapy;
    }

    public void setDurationOfTherapy(double durationOfTherapy) {
        this.durationOfTherapy = durationOfTherapy;
    }
}
