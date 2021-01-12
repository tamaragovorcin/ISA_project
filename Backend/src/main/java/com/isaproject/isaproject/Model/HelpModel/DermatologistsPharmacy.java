package com.isaproject.isaproject.Model.HelpModel;

import jdk.jfr.Enabled;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DermatologistsPharmacy {


    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "dermatologist", nullable = false)
    private int dermatologist;


    @Column(name = "pharmacy", nullable = false)
    private int pharmacy;

    public DermatologistsPharmacy(Integer id, int dermatologist, int pharmacy) {
        this.id = id;
        this.dermatologist = dermatologist;
        this.pharmacy = pharmacy;
    }

    public DermatologistsPharmacy() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
