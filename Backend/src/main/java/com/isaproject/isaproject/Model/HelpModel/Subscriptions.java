package com.isaproject.isaproject.Model.HelpModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Subscriptions  {

    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "patientsId", nullable = false)
    private int patientsId;


    @Column(name = "pharmacyId", nullable = false)
    private int pharmacyId;


    @Column(name = "subscribed", nullable = false)
    private Boolean subscribed;

    public Subscriptions() {
    }

    public Subscriptions(Integer id, int patientsId, int pharmacyId, Boolean subscribed) {
        this.id = id;
        this.patientsId = patientsId;
        this.pharmacyId = pharmacyId;
        this.subscribed = subscribed;
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
