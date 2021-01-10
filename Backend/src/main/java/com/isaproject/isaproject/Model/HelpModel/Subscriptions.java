package com.isaproject.isaproject.Model.HelpModel;

import com.isaproject.isaproject.Model.Entity;

public class Subscriptions extends Entity {
    private int patientsId;
    private int pharmacyId;
    private Boolean subscribed;

    public Subscriptions() {
        super();
    }

    public Subscriptions(int id, int patientsId, int pharmacyId, Boolean subscribed) {
        super(id);
        this.patientsId = patientsId;
        this.pharmacyId = pharmacyId;
        this.subscribed = subscribed;
    }

    public Subscriptions(int patientsId, int pharmacyId, Boolean subscribed) {
        this.patientsId = patientsId;
        this.pharmacyId = pharmacyId;
        this.subscribed = subscribed;
    }

    public int getPatientsId() {
        return patientsId;
    }

    public void setPatientsId(int patientsId) {
        this.patientsId = patientsId;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }
}
