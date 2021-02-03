package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Medicine.Medication;

import java.util.HashSet;
import java.util.Set;

public class PrescriptionSendDTO {
    private Integer consultingId;
    private Set<Medication> medications = new HashSet<Medication>();
    private double durationOfTherapy;

    public PrescriptionSendDTO() {}


    public Integer getConsultingId() {
        return consultingId;
    }

    public void setConsultingId(Integer consultingId) {
        this.consultingId = consultingId;
    }

    public Set<Medication> getMedications() {
        return medications;
    }

    public void setMedications(Set<Medication> medications) {
        this.medications = medications;
    }

    public PrescriptionSendDTO(Integer consultingId, Set<Medication> medications, double durationOfTherapy) {
        this.consultingId = consultingId;
        this.medications = medications;
        this.durationOfTherapy = durationOfTherapy;
    }

    public double getDurationOfTherapy() {
        return durationOfTherapy;
    }

    public void setDurationOfTherapy(double durationOfTherapy) {
        this.durationOfTherapy = durationOfTherapy;
    }
}
