package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.Examinations.Prescription;
import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;
import com.isaproject.isaproject.Model.Users.Address;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class PharmacyDTO {

    private String pharmacyName;

    private AddressDTO address;
    private double consultingPrice;
    private String description;

    public PharmacyDTO(String pharmacyName, AddressDTO address) {
        this.pharmacyName = pharmacyName;
        this.address = address;
    }

    public PharmacyDTO() {
    }

    public PharmacyDTO(String pharmacyName, AddressDTO address, double consultingPrice, String description) {
        this.pharmacyName = pharmacyName;
        this.address = address;
        this.consultingPrice = consultingPrice;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getConsultingPrice() {
        return consultingPrice;
    }

    public void setConsultingPrice(double consultingPrice) {
        this.consultingPrice = consultingPrice;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
