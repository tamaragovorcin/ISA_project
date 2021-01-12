package com.isaproject.isaproject.Model.HelpModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PatientsMedicationAlergy {


    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "patientsId", nullable = false)
    private  int patientsId;


    @Column(name = "medicationCode", nullable = false)
    private int medicationCode;


    @Column(name = "description", nullable = false)
    private String description;


    public PatientsMedicationAlergy() {
    }

    public PatientsMedicationAlergy(Integer id, int patientsId, int medicationCode, String description) {
        this.id = id;
        this.patientsId = patientsId;
        this.medicationCode = medicationCode;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
