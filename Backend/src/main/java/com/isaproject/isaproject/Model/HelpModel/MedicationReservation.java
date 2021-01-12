package com.isaproject.isaproject.Model.HelpModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
public class MedicationReservation {

    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "patientsId", nullable = false)
    private int patientsId;


    @Column(name = "medicineCode", nullable = false)
    private long medicineCode;


    @Column(name = "pharmacyId", nullable = false)
    private int pharmacyId;


    @Column(name = "dateOfReservation", nullable = false)
    private LocalDateTime dateOfReservation;


    @Column(name = "dateOfTakeOver", nullable = false)
    private LocalDateTime dateOfTakeOver;


    @Column(name = "collected", nullable = false)
    private Boolean collected;//da li je preuzet lijek


    @Column(name = "reservationCode", nullable = false)
    private long reservationCode;


    @Column(name = "cancelled", nullable = false)
    private Boolean cancelled;

    public MedicationReservation() {
    }

    public MedicationReservation(Integer id, int patientsId, long medicineCode, int pharmacyId, LocalDateTime dateOfReservation, LocalDateTime dateOfTakeOver, Boolean collected, long reservationCode, Boolean cancelled) {
        this.id = id;
        this.patientsId = patientsId;
        this.medicineCode = medicineCode;
        this.pharmacyId = pharmacyId;
        this.dateOfReservation = dateOfReservation;
        this.dateOfTakeOver = dateOfTakeOver;
        this.collected = collected;
        this.reservationCode = reservationCode;
        this.cancelled = cancelled;
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

    public long getMedicineCode() {
        return medicineCode;
    }

    public void setMedicineCode(long medicineCode) {
        this.medicineCode = medicineCode;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public LocalDateTime getDateOfReservation() {
        return dateOfReservation;
    }

    public void setDateOfReservation(LocalDateTime dateOfReservation) {
        this.dateOfReservation = dateOfReservation;
    }

    public LocalDateTime getDateOfTakeOver() {
        return dateOfTakeOver;
    }

    public void setDateOfTakeOver(LocalDateTime dateOfTakeOver) {
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
