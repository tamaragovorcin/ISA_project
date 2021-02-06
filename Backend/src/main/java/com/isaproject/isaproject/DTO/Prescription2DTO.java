package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Medicine.Medication;

import java.util.HashSet;
import java.util.Set;

public class Prescription2DTO {
    private Integer examinationId;
    private Medication medication;
    private double durationOfTherapy;



    public Prescription2DTO() {

    }

    public Prescription2DTO(Integer examinationId, Medication medication, double durationOfTherapy) {
        this.examinationId = examinationId;
        this.medication = medication;
        this.durationOfTherapy = durationOfTherapy;
    }

    public Integer getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public double getDurationOfTherapy() {
        return durationOfTherapy;
    }

    public void setDurationOfTherapy(double durationOfTherapy) {
        this.durationOfTherapy = durationOfTherapy;
    }
}
