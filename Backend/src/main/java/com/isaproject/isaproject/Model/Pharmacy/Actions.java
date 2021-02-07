package com.isaproject.isaproject.Model.Pharmacy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import static javax.persistence.DiscriminatorType.STRING;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@DiscriminatorValue("Actions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Actions implements Serializable {

    @Id
    @Column(name="id", unique=true, nullable=false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenV2")
    private Integer id;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_actions", referencedColumnName = "id", nullable = true, unique = false)
    private Pharmacy pharmacy;

    @Column(name = "description", nullable = true)
    private String description;


    @Column(name = "expiryDate", nullable = true)
    private LocalDate expiryDate;

    public Actions() {}

    public Actions(Integer id, int pharmacyId, String description, LocalDate expiryDate) {
        this.id = id;
        this.description = description;
        this.expiryDate = expiryDate;
    }

    public Actions(Pharmacy pharmacy, String description, LocalDate expiryDate) {
        this.pharmacy = pharmacy;
        this.description = description;
        this.expiryDate = expiryDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
