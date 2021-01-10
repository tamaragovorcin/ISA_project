package com.isaproject.isaproject.Model.HelpModel;

import com.isaproject.isaproject.Model.Entity;

public class PatientsMedicationAlergy extends Entity {
    private  int patientsId;
    private int medicationCode;
    private String description;

    public PatientsMedicationAlergy(int id, int patientsId, int medicationCode, String description) {
        super(id);
        this.patientsId = patientsId;
        this.medicationCode = medicationCode;
        this.description = description;
    }

    public PatientsMedicationAlergy(int patientsId, int medicationCode, String description) {
        this.patientsId = patientsId;
        this.medicationCode = medicationCode;
        this.description = description;
    }

    public int getPatientsId() {
        return patientsId;
    }

    public void setPatientsId(int patientsId) {
        this.patientsId = patientsId;
    }

    public int getMedicationCode() {
        return medicationCode;
    }

    public void setMedicationCode(int medicationCode) {
        this.medicationCode = medicationCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PatientsMedicationAlergy() {
        super();
    }
}
