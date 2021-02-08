package com.isaproject.isaproject.Model.Users;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Schedule.HolidayScheduleDermatologist;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("Dermatologist")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Dermatologist extends PersonUser{

    @Column(name = "markDermatologist", nullable = true)
    private double markDermatologist;

    @JsonManagedReference(value="dermatologist-schedule")
    @OneToMany(mappedBy = "dermatologist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ExaminationSchedule> examinationSchedules = new HashSet<ExaminationSchedule>();

    @JsonManagedReference(value="dermatologist-holiday")
    @OneToMany(mappedBy = "dermatologist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<HolidayScheduleDermatologist> holidayScheduleDermatologists = new HashSet<HolidayScheduleDermatologist>();


    @ManyToMany
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JoinTable(name = "dermatologists_pharmacies", joinColumns = @JoinColumn(name = "dermatologist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"))
    private Set<Pharmacy> pharmacies = new HashSet<Pharmacy>();

    @JsonManagedReference(value="dermatologist-mark")
    @OneToMany(mappedBy = "dermatologist", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<MarkDermatologist> marks = new HashSet<MarkDermatologist>();

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

    public Set<HolidayScheduleDermatologist> getHolidayScheduleDermatologists() {
        return holidayScheduleDermatologists;
    }

    public void setHolidayScheduleDermatologists(Set<HolidayScheduleDermatologist> holidayScheduleDermatologists) {
        this.holidayScheduleDermatologists = holidayScheduleDermatologists;
    }

}
