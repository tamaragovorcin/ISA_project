package com.isaproject.isaproject.Model.Schedule;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isaproject.isaproject.Model.HelpModel.MedicationPrice;
import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;
import com.isaproject.isaproject.Model.Medicine.Specification;
import com.isaproject.isaproject.Model.Orders.Order;
import com.isaproject.isaproject.Model.Users.Pharmacist;
import com.isaproject.isaproject.Model.Users.PharmacyAdmin;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("WorkingHoursPharmacist")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WorkingHoursPharmacist {
    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;


    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "pharmacist_id", referencedColumnName = "id", nullable = true, unique = true)
    private Pharmacist pharmacist;

    @JsonBackReference(value="schedule-monday")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "mondaySchedule", referencedColumnName = "id", nullable = true, unique = false)
    private MondaySchedule mondaySchedule;

    @JsonBackReference(value="schedule-tuesday")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "tuesdaySchedule", referencedColumnName = "id", nullable = true, unique = false)
    private TuesdaySchedule tuesdaySchedule;

    @JsonBackReference(value="schedule-wednesday")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "wednesdaySchedule", referencedColumnName = "id", nullable = true, unique = false)
    private WednesdaySchedule wednesdaySchedule;

    @JsonBackReference(value="schedule-thursday")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "thursdaySchedule", referencedColumnName = "id", nullable = true, unique = false)
    private ThursdaySchedule thursdaySchedule;

    @JsonBackReference(value="schedule-friday")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "fridaySchedule", referencedColumnName = "id", nullable = false, unique = false)
    private FridaySchedule fridaySchedule;


    @JsonBackReference(value="schedule-saturday")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "saturdaySchedule", referencedColumnName = "id", nullable = false, unique = false)
    private SaturdaySchedule saturdaySchedule;


    @JsonBackReference(value="schedule-sunday")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "sundaySchedule", referencedColumnName = "id", nullable = false, unique = false)
    private SundaySchedule sundaySchedule;




    public WorkingHoursPharmacist() {

    }

    public WorkingHoursPharmacist(Integer id, Pharmacist pharmacist, MondaySchedule mondaySchedule, TuesdaySchedule tuesdaySchedule, WednesdaySchedule wednesdaySchedule, ThursdaySchedule thursdaySchedule, FridaySchedule fridaySchedule, SaturdaySchedule saturdaySchedule, SundaySchedule sundaySchedule) {
        this.id = id;
        this.pharmacist = pharmacist;
        this.mondaySchedule = mondaySchedule;
        this.tuesdaySchedule = tuesdaySchedule;
        this.wednesdaySchedule = wednesdaySchedule;
        this.thursdaySchedule = thursdaySchedule;
        this.fridaySchedule = fridaySchedule;
        this.saturdaySchedule = saturdaySchedule;
        this.sundaySchedule = sundaySchedule;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
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

