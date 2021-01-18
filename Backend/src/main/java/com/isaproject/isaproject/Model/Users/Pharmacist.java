package com.isaproject.isaproject.Model.Users;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Schedule.HolidayScheduleDermatologist;
import com.isaproject.isaproject.Model.Schedule.HolidaySchedulePharmacist;
import com.isaproject.isaproject.Model.Schedule.WorkingHoursPharmacist;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//ovom anotacijom se navodi vrednost diskriminatorske kolone koja vazi za
//objekte ove klase

@Entity
@DiscriminatorValue("Pharmacist")
public class Pharmacist extends  PersonUser {

    @OneToMany(mappedBy = "pharmacist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Consulting> consulting = new HashSet<Consulting>();


    @OneToMany(mappedBy = "pharmacist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<HolidaySchedulePharmacist> holidaySchedulePharmacists = new HashSet<HolidaySchedulePharmacist>();

    @OneToMany(mappedBy = "pharmacist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<WorkingHoursPharmacist> workingHoursPharmacists = new HashSet<WorkingHoursPharmacist>();

    @Column(name = "markPharmacist", nullable = true)
    private double markPharmacist;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_Pharmacist", referencedColumnName = "id", nullable = true, unique = false)
    private Pharmacy pharmacy;


    public Set<Consulting> getConsulting() {
        return consulting;
    }

    public void setConsulting(Set<Consulting> consulting) {
        this.consulting = consulting;
    }


    public Pharmacist(Set<Consulting> consulting,  double markPharmacist) {
        this.consulting = consulting;
        this.markPharmacist = markPharmacist;
    }

    public Pharmacist() { super();}

    public Set<WorkingHoursPharmacist> getWorkingHoursPharmacists() {
        return workingHoursPharmacists;
    }

    public void setWorkingHoursPharmacists(Set<WorkingHoursPharmacist> workingHoursPharmacists) {
        this.workingHoursPharmacists = workingHoursPharmacists;
    }

    public Set<HolidaySchedulePharmacist> getHolidaySchedulePharmacists() {
        return holidaySchedulePharmacists;
    }

    public void setHolidaySchedulePharmacists(Set<HolidaySchedulePharmacist> holidaySchedulePharmacists) {
        this.holidaySchedulePharmacists = holidaySchedulePharmacists;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public double getMarkPharmacist() {
        return markPharmacist;
    }

    public void setMarkPharmacist(double mark) {
        this.markPharmacist = mark;
    }
}

