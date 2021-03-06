package com.isaproject.isaproject.Model.Examinations;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isaproject.isaproject.Model.Medicine.MedicationEPrescription;
import com.isaproject.isaproject.Model.Users.Patient;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="eprescriptions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EPrescription {

    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;


    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @JsonBackReference(value="eprescription-patient")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false, unique = false)
    private Patient patient;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @JsonManagedReference(value="eprescription-medications")
    @OneToMany(mappedBy = "ePrescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicationEPrescription> medications = new HashSet<MedicationEPrescription>();


    @Column(name = "pharmacyId", nullable = false)
    private Integer pharmacyId;

    public EPrescription() {}

    public EPrescription(Integer id, String code, Patient patient, LocalDate date, Set<MedicationEPrescription> medications, Integer pharmacyId) {
        this.id = id;
        this.code = code;
        this.patient = patient;
        this.date = date;
        this.medications = medications;
        this.pharmacyId = pharmacyId;
    }

    public Integer getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Integer pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<MedicationEPrescription> getMedications() {
        return medications;
    }

    public void setMedications(Set<MedicationEPrescription> medications) {
        this.medications = medications;
    }
}
