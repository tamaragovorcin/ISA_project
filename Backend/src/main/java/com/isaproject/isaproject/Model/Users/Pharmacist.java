package com.isaproject.isaproject.Model.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Schedule.HolidayScheduleDermatologist;
import com.isaproject.isaproject.Model.Schedule.HolidaySchedulePharmacist;
import com.isaproject.isaproject.Model.Schedule.WorkingHoursPharmacist;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("Pharmacist")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pharmacist extends  PersonUser {

    @JsonManagedReference(value="consulting-pharmacist")
    @OneToMany(mappedBy = "pharmacist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Consulting> consulting = new HashSet<Consulting>();

    @JsonManagedReference(value="holiday-pharmacist")
    @OneToMany(mappedBy = "pharmacist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<HolidaySchedulePharmacist> holidaySchedulePharmacists = new HashSet<HolidaySchedulePharmacist>();


    @Column(name = "markPharmacist", nullable = true)
    private double markPharmacist;


    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_Pharmacist", referencedColumnName = "id", nullable = true, unique = false)
    private Pharmacy pharmacy;


    public Pharmacist() { super();}

    public Pharmacist(Integer id, String name, String surname, String email, String password, String phoneNumber, Boolean firstLogged, boolean enabled, Timestamp lastPasswordResetDate, List<Authority> authorities, Address address, Set<Consulting> consulting, Set<HolidaySchedulePharmacist> holidaySchedulePharmacists, WorkingHoursPharmacist workingHoursPharmacists, double markPharmacist, Pharmacy pharmacy) {
        super(id, name, surname, email, password, phoneNumber, firstLogged, enabled, lastPasswordResetDate, authorities, address);
        this.consulting = consulting;
        this.holidaySchedulePharmacists = holidaySchedulePharmacists;
        this.markPharmacist = markPharmacist;
        this.pharmacy = pharmacy;
    }

    public Pharmacist(Set<Consulting> consulting, Set<HolidaySchedulePharmacist> holidaySchedulePharmacists, WorkingHoursPharmacist workingHoursPharmacists, double markPharmacist, Pharmacy pharmacy) {
        this.consulting = consulting;
        this.holidaySchedulePharmacists = holidaySchedulePharmacists;
        this.markPharmacist = markPharmacist;
        this.pharmacy = pharmacy;
    }

    public Set<Consulting> getConsulting() {
        return consulting;
    }

    public void setConsulting(Set<Consulting> consulting) {
        this.consulting = consulting;
    }

    public Set<HolidaySchedulePharmacist> getHolidaySchedulePharmacists() {
        return holidaySchedulePharmacists;
    }

    public void setHolidaySchedulePharmacists(Set<HolidaySchedulePharmacist> holidaySchedulePharmacists) {
        this.holidaySchedulePharmacists = holidaySchedulePharmacists;
    }


    public double getMarkPharmacist() {
        return markPharmacist;
    }

    public void setMarkPharmacist(double markPharmacist) {
        this.markPharmacist = markPharmacist;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}

