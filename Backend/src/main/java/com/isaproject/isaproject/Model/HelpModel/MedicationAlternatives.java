package com.isaproject.isaproject.Model.HelpModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MedicationAlternatives  {


    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "medicationCode", nullable = false)
    private long medicationCode;


    @Column(name = "medicationCodeAlternative", nullable = false)
    private long medicationCodeAlternative;

    public MedicationAlternatives() {
    }

    public MedicationAlternatives(Integer id, long medicationCode, long medicationCodeAlternative) {
        this.id = id;
        this.medicationCode = medicationCode;
        this.medicationCodeAlternative = medicationCodeAlternative;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getMedicationCode() {
        return medicationCode;
    }

    public void setMedicationCode(long medicationCode) {
        this.medicationCode = medicationCode;
    }

    public long getMedicationCodeAlternative() {
        return medicationCodeAlternative;
    }

    public void setMedicationCodeAlternative(long medicationCodeAlternative) {
        this.medicationCodeAlternative = medicationCodeAlternative;
    }
}
