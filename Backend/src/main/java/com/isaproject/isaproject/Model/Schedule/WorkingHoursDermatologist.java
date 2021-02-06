package com.isaproject.isaproject.Model.Schedule;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.Pharmacist;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
//@DiscriminatorValue("WorkingHoursDermatologist")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WorkingHoursDermatologist  {
    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @JsonBackReference(value = "pharmacy-dermatologist")
    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "pharmacy_id", referencedColumnName = "id", nullable = true, unique = false)
    private Pharmacy pharmacy;


    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "dermatologist_id", referencedColumnName = "id", nullable = true, unique = false)
    private Dermatologist dermatologist;


    @JsonBackReference(value="schedule-monday1")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "mondaySchedule", referencedColumnName = "id", nullable = true, unique = false)
    private MondaySchedule mondaySchedule;

    @JsonBackReference(value="schedule-tuesday1")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "tuesdaySchedule", referencedColumnName = "id", nullable = true, unique = false)
    private TuesdaySchedule tuesdaySchedule;

    @JsonBackReference(value="schedule-wednesday1")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "wednesdaySchedule", referencedColumnName = "id", nullable = true, unique = false)
    private WednesdaySchedule wednesdaySchedule;

    @JsonBackReference(value="schedule-thursday1")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "thursdaySchedule", referencedColumnName = "id", nullable = true, unique = false)
    private ThursdaySchedule thursdaySchedule;

    @JsonBackReference(value="schedule-friday1")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "fridaySchedule", referencedColumnName = "id", nullable = false, unique = false)
    private FridaySchedule fridaySchedule;


    @JsonBackReference(value="schedule-saturday1")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "saturdaySchedule", referencedColumnName = "id", nullable = false, unique = false)
    private SaturdaySchedule saturdaySchedule;


    @JsonBackReference(value="schedule-sunday1")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "sundaySchedule", referencedColumnName = "id", nullable = false, unique = false)
    private SundaySchedule sundaySchedule;

    public WorkingHoursDermatologist() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public MondaySchedule getMondaySchedule() {
        return mondaySchedule;
    }

    public void setMondaySchedule(MondaySchedule mondaySchedule) {
        this.mondaySchedule = mondaySchedule;
    }

    public TuesdaySchedule getTuesdaySchedule() {
        return tuesdaySchedule;
    }

    public void setTuesdaySchedule(TuesdaySchedule tuesdaySchedule) {
        this.tuesdaySchedule = tuesdaySchedule;
    }

    public WednesdaySchedule getWednesdaySchedule() {
        return wednesdaySchedule;
    }

    public void setWednesdaySchedule(WednesdaySchedule wednesdaySchedule) {
        this.wednesdaySchedule = wednesdaySchedule;
    }

    public ThursdaySchedule getThursdaySchedule() {
        return thursdaySchedule;
    }

    public void setThursdaySchedule(ThursdaySchedule thursdaySchedule) {
        this.thursdaySchedule = thursdaySchedule;
    }

    public FridaySchedule getFridaySchedule() {
        return fridaySchedule;
    }

    public void setFridaySchedule(FridaySchedule fridaySchedule) {
        this.fridaySchedule = fridaySchedule;
    }

    public SaturdaySchedule getSaturdaySchedule() {
        return saturdaySchedule;
    }

    public void setSaturdaySchedule(SaturdaySchedule saturdaySchedule) {
        this.saturdaySchedule = saturdaySchedule;
    }

    public SundaySchedule getSundaySchedule() {
        return sundaySchedule;
    }

    public void setSundaySchedule(SundaySchedule sundaySchedule) {
        this.sundaySchedule = sundaySchedule;
    }
}
