package com.isaproject.isaproject.DTO;

import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Dermatologist;

public class PharmacyDermatologistsDTO {
    private Dermatologist dermatologist;
    private Pharmacy pharmacy;

    public PharmacyDermatologistsDTO(Dermatologist dermatologist, Pharmacy pharmacy) {
        this.dermatologist = dermatologist;
        this.pharmacy = pharmacy;
    }

    public PharmacyDermatologistsDTO() {
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}
