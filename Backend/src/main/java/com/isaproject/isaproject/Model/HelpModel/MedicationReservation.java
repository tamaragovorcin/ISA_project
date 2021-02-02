package com.isaproject.isaproject.Model.HelpModel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.Pharmacist;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MedicationReservation {

    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @JsonBackReference(value="patient-medication")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = true)
    private Patient patient;

    @JsonBackReference(value="medicine-medicationReservations")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine", referencedColumnName = "id", nullable = true, unique = false)
    private Medication medicine;

    @JsonBackReference(value="pharmacy-medication")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id", referencedColumnName = "id", nullable = false, unique = false)
    private Pharmacy pharmacy;


    @Column(name = "dateOfReservation", nullable = true)
    private LocalDate dateOfReservation;


    @Column(name = "dateOfTakeOver", nullable = true)
    private LocalDate dateOfTakeOver;


    @Column(name = "collected", nullable = true)
    private Boolean collected;//da li je preuzet lijek


    @Column(name = "reservationCode", nullable = true)
    private long reservationCode;


    @Column(name = "cancelled", nullable = true)
    private Boolean cancelled;

    public MedicationReservation() {
    }

    public Medication getMedicine() {
        return medicine;
    }

    public void setMedicine(Medication medicine) {
        this.medicine = medicine;
    }

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateOfReservation() {
        return dateOfReservation;
    }

    public void setDateOfReservation(LocalDate dateOfReservation) {
        this.dateOfReservation = dateOfReservation;
    }

    public LocalDate getDateOfTakeOver() {
        return dateOfTakeOver;
    }

    public void setDateOfTakeOver(LocalDate dateOfTakeOver) {
        this.dateOfTakeOver = dateOfTakeOver;
    }

    public Boolean getCollected() {
        return collected;
    }

    public void setCollected(Boolean collected) {
        this.collected = collected;
    }

    public long getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(long reservationCode) {
        this.reservationCode = reservationCode;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }
}
