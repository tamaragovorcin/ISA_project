package com.isaproject.isaproject.Model.Medicine;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isaproject.isaproject.Model.Examinations.EPrescription;
import com.isaproject.isaproject.Model.Examinations.Prescription;
import com.isaproject.isaproject.Model.HelpModel.MedicationPrice;
import com.isaproject.isaproject.Model.HelpModel.PatientsMedicationAlergy;
import com.isaproject.isaproject.Model.Orders.MedicationInOrder;
import com.isaproject.isaproject.Model.Orders.Offer;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Pharmacist;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("Medication")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Medication  {
    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "name", nullable = true)
    private String name;


    @Column(name = "code", unique=true, nullable = true)
    private long code;


    @Column(name = "form", nullable = true)
    private String form;


    @Column(name = "type", nullable = true)
    private String type;


    @Column(name = "issuanceRegime", nullable = true)
    private String issuanceRegime;//rezim upotrebe


    @Column(name = "mark", nullable = true)
    private double mark;


    @Column(name = "loyaltyPoints", nullable = true)
    private double loyaltyPoints;

    @Column(name = "wayOfSelling", nullable = true)
    private String wayOfSelling;


    @ManyToMany
    @JoinTable(name = "eprescription_medications", joinColumns = @JoinColumn(name = "medication_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "eprescription_id", referencedColumnName = "id"))
    private Set<EPrescription> ePrescriptions = new HashSet<EPrescription>();

    @ManyToMany
    @JoinTable(name = "prescription_medications", joinColumns = @JoinColumn(name = "medication_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "prescription_id", referencedColumnName = "id"))
    private Set<Prescription> prescriptions = new HashSet<Prescription>();


    @ManyToMany
    @JoinTable(name = "medications_alternatives", joinColumns = @JoinColumn(name = "medication_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "medication_alternative_id", referencedColumnName = "id"))
    private Set<Medication> medications = new HashSet<Medication>();

    @OneToMany(mappedBy = "medication", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PatientsMedicationAlergy> patientsMedicationAlergy = new HashSet<PatientsMedicationAlergy>();


    @JsonManagedReference(value="medication-medicationInOrder")
    @OneToMany(mappedBy = "medication", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicationInOrder> medicationInOrder = new HashSet<MedicationInOrder>();

    @JsonManagedReference(value="medication-medicationPrice")
    @OneToMany(mappedBy = "medication", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicationPrice> medicationPrices = new HashSet<MedicationPrice>();

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "specification_id", referencedColumnName = "id", nullable = true, unique = false)
    private Specification specification;


    public Medication() {}

    public Medication(Integer id, String name, long code, String form, String type, String issuanceRegime, double mark, double loyaltyPoints, String wayOfSelling, Set<EPrescription> ePrescriptions, Set<Offer> offer, Set<Prescription> prescriptions, Set<Medication> medications, Set<PatientsMedicationAlergy> patientsMedicationAlergy, Set<MedicationInOrder> medicationInOrder, Set<MedicationPrice> medicationPrices, Specification specification) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.form = form;
        this.type = type;
        this.issuanceRegime = issuanceRegime;
        this.mark = mark;
        this.loyaltyPoints = loyaltyPoints;
        this.wayOfSelling = wayOfSelling;
        this.ePrescriptions = ePrescriptions;

    }

    public String getWayOfSelling() {
        return wayOfSelling;
    }

    public void setWayOfSelling(String wayOfSelling) {
        this.wayOfSelling = wayOfSelling;
    }


    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }


    public Set<PatientsMedicationAlergy> getPatientsMedicationAlergy() {
        return patientsMedicationAlergy;
    }

    public void setPatientsMedicationAlergy(Set<PatientsMedicationAlergy> patientsMedicationAlergy) {
        this.patientsMedicationAlergy = patientsMedicationAlergy;
    }

    public Set<Medication> getMedications() {
        return medications;
    }

    public void setMedications(Set<Medication> medications) {
        this.medications = medications;
    }

    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
        this.medications = medications;
        this.patientsMedicationAlergy = patientsMedicationAlergy;
        this.medicationInOrder = medicationInOrder;
        this.medicationPrices = medicationPrices;
        this.specification = specification;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIssuanceRegime() {
        return issuanceRegime;
    }

    public void setIssuanceRegime(String issuanceRegime) {
        this.issuanceRegime = issuanceRegime;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(double loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public Set<EPrescription> getePrescriptions() {
        return ePrescriptions;
    }

    public void setePrescriptions(Set<EPrescription> ePrescriptions) {
        this.ePrescriptions = ePrescriptions;
    }

    public Set<MedicationInOrder> getMedicationInOrder() {
        return medicationInOrder;
    }

    public void setMedicationInOrder(Set<MedicationInOrder> medicationInOrder) {
        this.medicationInOrder = medicationInOrder;
    }

    public Set<MedicationPrice> getMedicationPrices() {
        return medicationPrices;
    }

    public void setMedicationPrices(Set<MedicationPrice> medicationPrices) {
        this.medicationPrices = medicationPrices;
    }
}