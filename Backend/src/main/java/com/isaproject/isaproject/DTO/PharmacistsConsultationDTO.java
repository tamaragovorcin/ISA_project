package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.Pharmacist;
import java.time.LocalDate;
import java.time.LocalTime;

public class PharmacistsConsultationDTO {

    private Integer pharmacyId;

    private LocalTime time;

    private LocalDate date;

    private Pharmacist pharmacist;

    private Patient patient;

    public Integer getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Integer pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public LocalTime getTime() {
        return time;
    }

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PharmacistsConsultationDTO{" +
                "pharmacyId=" + pharmacyId +
                ", time=" + time +
                ", date=" + date +
                ", pharmacist=" + pharmacist.getName() +
                ", patient=" + patient.getEmail() +
                '}';
    }
}