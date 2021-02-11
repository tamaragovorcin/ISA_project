package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Medicine.Medication;
import java.util.HashSet;
import java.util.Set;

public class PrescriptionSendDTO {
    private Integer consultingId;
    private Integer medicationId;
    private double durationOfTherapy;

    public PrescriptionSendDTO() {}

    public Integer getConsultingId() {
        return consultingId;
    }

    public void setConsultingId(Integer consultingId) {
        this.consultingId = consultingId;
    }

    public PrescriptionSendDTO(Integer consultingId, Integer medication, double durationOfTherapy) {
        this.consultingId = consultingId;
        this.medicationId = medication;
        this.durationOfTherapy = durationOfTherapy;
    }

    public Integer getMedicationId() {
        return medicationId;
    }

    public void setMedication(Integer medication) {
        this.medicationId = medication;
    }

    public double getDurationOfTherapy() {
        return durationOfTherapy;
    }

    public void setDurationOfTherapy(double durationOfTherapy) {
        this.durationOfTherapy = durationOfTherapy;
    }
}