package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.Pharmacist;

public class MarkDTO {

    private Pharmacy pharmacy;

    private Medication medication;

    private Pharmacist pharmacist;

    private Dermatologist dermatologist;

    private Patient patient;

    private int mark;

    public MarkDTO() {}

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    @Override
    public String toString() {
        return "MarkDTO{" +
                "pharmacy=" + pharmacy.getAddress().getTown() +
                ", medication=" + medication +
                ", pharmacist=" + pharmacist +
                ", dermatologist=" + dermatologist +
                ", patient=" + patient.getName() +
                ", mark=" + mark +
                '}';
    }
}