package com.isaproject.isaproject.Model.Pharmacy;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.Examinations.Prescription;
import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Orders.Order;
import com.isaproject.isaproject.Model.Schedule.WorkingHoursDermatologist;
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

    @Column(name = "consultingPrice", nullable = true)
    private double consultingPrice;


    @JsonManagedReference(value="pharmacy-schedule")
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<ExaminationSchedule> examinationSchedules = new HashSet<ExaminationSchedule>();

    @JsonManagedReference(value="pharmacy-medication")
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<MedicationReservation> medicationReservations = new HashSet<MedicationReservation>();

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

    @JsonManagedReference(value = "pharmacy-order")
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Order> order = new HashSet<Order>();


    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<WorkingHoursDermatologist> workingHoursDermatologists = new HashSet<WorkingHoursDermatologist>();

    @ManyToMany
    @JoinTable(name = "medications_pharmacies", joinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "medication_id", referencedColumnName = "id"))
    private Set<Medication> medications = new HashSet<Medication>();

    public Pharmacy() {}

    public Pharmacy(Integer id, String pharmacyName, double mark, double consultingPrice, Set<ExaminationSchedule> examinationSchedules, Set<MedicationReservation> medicationReservations, Set<Prescription> prescriptions, Address address, Set<Dermatologist> dermatologists, Set<Pharmacist> pharmacists, Set<PharmacyAdmin> pharmacyAdmins, Set<Order> order, Set<WorkingHoursDermatologist> workingHoursDermatologists, Set<Medication> medications) {
        this.id = id;
        this.pharmacyName = pharmacyName;
        this.mark = mark;
        this.consultingPrice = consultingPrice;
        this.examinationSchedules = examinationSchedules;
        this.medicationReservations = medicationReservations;
        this.prescriptions = prescriptions;
        this.address = address;
        this.dermatologists = dermatologists;
        this.pharmacists = pharmacists;
        this.order = order;
        this.workingHoursDermatologists = workingHoursDermatologists;
        this.medications = medications;
    }

    public Set<WorkingHoursDermatologist> getWorkingHoursDermatologists() {
        return workingHoursDermatologists;
    }

    public void setWorkingHoursDermatologists(Set<WorkingHoursDermatologist> workingHoursDermatologists) {
        this.workingHoursDermatologists = workingHoursDermatologists;
    }

    public Set<Order> getOrder() {
        return order;
    }

    public void setOrder(Set<Order> order) {
        this.order = order;
    }

    public Set<MedicationReservation> getMedicationReservations() {
        return medicationReservations;
    }

    public void setMedicationReservations(Set<MedicationReservation> medicationReservations) {
        this.medicationReservations = medicationReservations;
    }

    //public Set<Actions> getActions() {  return actions; }

   // public void setActions(Set<Actions> actions) {
       // this.actions = actions;
    //}

    public Set<Medication> getMedications() {
        return medications;
    }

    public void setMedications(Set<Medication> medications) {
        this.medications = medications;
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

    public Set<ExaminationSchedule> getExaminationSchedules() {
        return examinationSchedules;
    }

    public void setExaminationSchedules(Set<ExaminationSchedule> examinationSchedules) {
        this.examinationSchedules = examinationSchedules;
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

    public double getConsultingPrice() {
        return consultingPrice;
    }

    public void setConsultingPrice(double consultingPrice) {
        this.consultingPrice = consultingPrice;
    }
}
