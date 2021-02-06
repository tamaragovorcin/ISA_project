package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Medicine.Medication;
import java.util.HashSet;
import java.util.Set;

public class PrescriptionSendDTO {
    private Integer consultingId;
    private Medication medication;
    private double durationOfTherapy;

    public PrescriptionSendDTO() {}

    public Integer getConsultingId() {
        return consultingId;
    }

    public void setConsultingId(Integer consultingId) {
        this.consultingId = consultingId;
    }

    public PrescriptionSendDTO(Integer consultingId, Medication medication, double durationOfTherapy) {
        this.consultingId = consultingId;
        this.medication = medication;
        this.durationOfTherapy = durationOfTherapy;
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