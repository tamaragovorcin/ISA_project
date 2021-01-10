package com.isaproject.isaproject.Model.HelpModel;

import com.isaproject.isaproject.Model.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class MedicationReservation extends Entity {
    private int patientsId;
    private long medicineCode;
    private int pharmacyId;
    private LocalDateTime dateOfReservation;
    private LocalDateTime dateOfTakeOver;
    private Boolean collected;//da li je preuzet lijek
    private long reservationCode;
    private Boolean cancelled;

    public MedicationReservation() {
        super();
    }

    public MedicationReservation(int id, int patientsId, long medicineCode, int pharmacyId, LocalDateTime dateOfReservation, LocalDateTime dateOfTakeOver, Boolean collected, long reservationCode, Boolean cancelled) {
        super(id);
        this.patientsId = patientsId;
        this.medicineCode = medicineCode;
        this.pharmacyId = pharmacyId;
        this.dateOfReservation = dateOfReservation;
        this.dateOfTakeOver = dateOfTakeOver;
        this.collected = collected;
        this.reservationCode = reservationCode;
        this.cancelled = cancelled;
    }

    public MedicationReservation(int patientsId, long medicineCode, int pharmacyId, LocalDateTime dateOfReservation, LocalDateTime dateOfTakeOver, Boolean collected, long reservationCode, Boolean cancelled) {
        this.patientsId = patientsId;
        this.medicineCode = medicineCode;
        this.pharmacyId = pharmacyId;
        this.dateOfReservation = dateOfReservation;
        this.dateOfTakeOver = dateOfTakeOver;
        this.collected = collected;
        this.reservationCode = reservationCode;
        this.cancelled = cancelled;
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
