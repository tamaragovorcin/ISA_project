package com.isaproject.isaproject.Model.Pharmacy;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.Examinations.Prescription;
import com.isaproject.isaproject.Model.HelpModel.MedicationPrice;
import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Orders.Order;
import com.isaproject.isaproject.Model.Schedule.HolidayScheduleDermatologist;
import com.isaproject.isaproject.Model.Schedule.WorkingHoursDermatologist;
import com.isaproject.isaproject.Model.Schedule.WorkingHoursPharmacist;
import com.isaproject.isaproject.Model.Users.*;
import net.minidev.json.annotate.JsonIgnore;
import com.isaproject.isaproject.Model.Users.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="pharmacy_table")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Pharmacy implements Serializable{

    @Id
    @SequenceGenerator(name = "mySeqGenV2", sequenceName = "mySeqV2", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenV2")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "pharmacyName", nullable = true,unique=false)
    private String pharmacyName;


    @Column(name = "mark", nullable = true)
    private double mark;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "consultingPrice", nullable = true)
    private double consultingPrice;


    @JsonManagedReference(value="pharmacy-schedule")
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<ExaminationSchedule> examinationSchedules = new HashSet<ExaminationSchedule>();

    @JsonManagedReference(value="pharmacy-medication")
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<MedicationReservation> medicationReservations = new HashSet<MedicationReservation>();

    @JsonManagedReference(value="pharmacy-mark")
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Mark> marks = new HashSet<Mark>();

    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Prescription> prescriptions = new HashSet<Prescription>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false, unique = false)
    private Address address;

   @ManyToMany(mappedBy = "pharmacies")
    private Set<Dermatologist> dermatologists = new HashSet<Dermatologist>();

    @JsonManagedReference
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Pharmacist> pharmacists = new HashSet<Pharmacist>();

    @ManyToMany(mappedBy = "subscribedToPharmacies")
    private Set<Patient> subscribedPatients= new HashSet<Patient>();

    @JsonManagedReference
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PharmacyAdmin> pharmacyAdmins = new HashSet<PharmacyAdmin>();

    @JsonManagedReference
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Actions> actions = new HashSet<Actions>();

    @JsonManagedReference(value = "pharmacy-dermatologist")
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<WorkingHoursDermatologist> workingHoursDermatologists = new HashSet<WorkingHoursDermatologist>();

    @JsonManagedReference(value="pharmacy-medicationPrice")
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicationPrice> medicationPrices = new HashSet<MedicationPrice>();

    public Pharmacy() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getConsultingPrice() {
        return consultingPrice;
    }

    public void setConsultingPrice(double consultingPrice) {
        this.consultingPrice = consultingPrice;
    }

    public Set<ExaminationSchedule> getExaminationSchedules() {
        return examinationSchedules;
    }

    public void setExaminationSchedules(Set<ExaminationSchedule> examinationSchedules) {
        this.examinationSchedules = examinationSchedules;
    }

    public Set<MedicationReservation> getMedicationReservations() {
        return medicationReservations;
    }

    public void setMedicationReservations(Set<MedicationReservation> medicationReservations) {
        this.medicationReservations = medicationReservations;
    }

    public Set<Mark> getMarks() {
        return marks;
    }

    public void setMarks(Set<Mark> marks) {
        this.marks = marks;
    }

    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Dermatologist> getDermatologists() {
        return dermatologists;
    }

    public void setDermatologists(Set<Dermatologist> dermatologists) {
        this.dermatologists = dermatologists;
    }

    public Set<Pharmacist> getPharmacists() {
        return pharmacists;
    }

    public void setPharmacists(Set<Pharmacist> pharmacists) {
        this.pharmacists = pharmacists;
    }

    public Set<Patient> getSubscribedPatients() {
        return subscribedPatients;
    }

    public void setSubscribedPatients(Set<Patient> subscribedPatients) {
        this.subscribedPatients = subscribedPatients;
    }

    public Set<PharmacyAdmin> getPharmacyAdmins() {
        return pharmacyAdmins;
    }

    public void setPharmacyAdmins(Set<PharmacyAdmin> pharmacyAdmins) {
        this.pharmacyAdmins = pharmacyAdmins;
    }

    public Set<Actions> getActions() {
        return actions;
    }

    public void setActions(Set<Actions> actions) {
        this.actions = actions;
    }

    public Set<WorkingHoursDermatologist> getWorkingHoursDermatologists() {
        return workingHoursDermatologists;
    }

    public void setWorkingHoursDermatologists(Set<WorkingHoursDermatologist> workingHoursDermatologists) {
        this.workingHoursDermatologists = workingHoursDermatologists;
    }

    public Set<MedicationPrice> getMedicationPrices() {
        return medicationPrices;
    }

    public void setMedicationPrices(Set<MedicationPrice> medicationPrices) {
        this.medicationPrices = medicationPrices;
    }
}
