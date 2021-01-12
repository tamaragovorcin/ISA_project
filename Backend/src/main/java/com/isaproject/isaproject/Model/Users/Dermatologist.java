package com.isaproject.isaproject.Model.Users;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Examinations.EPrescription;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//ovom anotacijom se navodi vrednost diskriminatorske kolone koja vazi za
//objekte ove klase

@Entity
@DiscriminatorValue("Dermatologist")
public class Dermatologist extends PersonUser{

    @Column(name = "markDermatologist", nullable = true)
    private double markDermatologist;

    @OneToMany(mappedBy = "dermatologist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ExaminationSchedule> examinationSchedules = new HashSet<ExaminationSchedule>();

    @ManyToMany
    @JoinTable(name = "dermatologists_pharmacies", joinColumns = @JoinColumn(name = "dermatologist_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"))
    private Set<Pharmacy> pharmacies = new HashSet<Pharmacy>();

    public Dermatologist() {}

    public Set<ExaminationSchedule> getExaminationSchedules() {
        return examinationSchedules;
    }

    public void setExaminationSchedules(Set<ExaminationSchedule> examinationSchedules) {
        this.examinationSchedules = examinationSchedules;
    }

    public Set<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(Set<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
    }

    public double getMarkDermatologist() {
        return markDermatologist;
    }

    public void setMarkDermatologist(double mark) {
        this.markDermatologist = mark;
    }
}
