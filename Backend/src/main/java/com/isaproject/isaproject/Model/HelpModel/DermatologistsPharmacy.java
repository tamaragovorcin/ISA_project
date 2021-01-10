package com.isaproject.isaproject.Model.HelpModel;

import com.isaproject.isaproject.Model.Entity;

public class DermatologistsPharmacy extends Entity {
    private int dermatologist;
    private int pharmacy;

    public DermatologistsPharmacy(int id, int dermatologist, int pharmacy) {
        super(id);
        this.dermatologist = dermatologist;
        this.pharmacy = pharmacy;
    }

    public DermatologistsPharmacy(int dermatologist, int pharmacy) {
        this.dermatologist = dermatologist;
        this.pharmacy = pharmacy;
    }
    public DermatologistsPharmacy() {
        super();
    }

    public int getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(int dermatologist) {
        this.dermatologist = dermatologist;
    }

    public int getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(int pharmacy) {
        this.pharmacy = pharmacy;
    }
}
