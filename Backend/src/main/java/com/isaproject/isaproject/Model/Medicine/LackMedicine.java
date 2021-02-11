package com.isaproject.isaproject.Model.Medicine;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@DiscriminatorValue("LackMedicine")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LackMedicine {

    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name = "nameMedicine", nullable = true)
    private String nameMedicine;

    @Column(name = "namePharmacy", nullable = true)
    private String namePharmacy;

    public LackMedicine() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameMedicine() {
        return nameMedicine;
    }

    public void setNameMedicine(String nameMedicine) {
        this.nameMedicine = nameMedicine;
    }

    public String getNamePharmacy() {
        return namePharmacy;
    }

    public void setNamePharmacy(String namePharmacy) {
        this.namePharmacy = namePharmacy;
    }
}
