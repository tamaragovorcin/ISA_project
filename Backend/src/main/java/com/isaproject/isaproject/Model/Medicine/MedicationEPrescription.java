package com.isaproject.isaproject.Model.Medicine;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isaproject.isaproject.Model.Examinations.EPrescription;
import javax.persistence.*;

@Entity
@Table(name="medicationsEprescription")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MedicationEPrescription {

    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "code", nullable = true)
    private long code;

    @Column(name = "quantity", nullable = true)
    private int quantity;

    @JsonBackReference(value="eprescription-medications")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "ePrescriptionMedication", referencedColumnName = "id", nullable = true, unique = false)
    private EPrescription ePrescription;

    public MedicationEPrescription() {}

    public MedicationEPrescription(Integer id, String name, long code, int quantity, EPrescription ePrescription) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.quantity = quantity;
        this.ePrescription = ePrescription;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public EPrescription getePrescription() {
        return ePrescription;
    }

    public void setePrescription(EPrescription ePrescription) {
        this.ePrescription = ePrescription;
    }
}
