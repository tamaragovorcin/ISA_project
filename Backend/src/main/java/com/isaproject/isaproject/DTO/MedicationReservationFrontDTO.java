package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Patient;

import java.time.LocalDateTime;

public class MedicationReservationFrontDTO {

    private Integer id;

   private String name;

   private long code;

   private String form;

    private LocalDateTime dateOfTakeOver;

    private String pharmacyName;



    public MedicationReservationFrontDTO(String name, long code, String form, LocalDateTime dateOfTakeOver, String pharmacyName) {
        this.name = name;
        this.code = code;
        this.form = form;
        this.dateOfTakeOver = dateOfTakeOver;
        this.pharmacyName = pharmacyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MedicationReservationFrontDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public LocalDateTime getDateOfTakeOver() {
        return dateOfTakeOver;
    }

    public void setDateOfTakeOver(LocalDateTime dateOfTakeOver) {
        this.dateOfTakeOver = dateOfTakeOver;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }
}
