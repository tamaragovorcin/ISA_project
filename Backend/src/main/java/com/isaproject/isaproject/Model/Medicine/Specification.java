package com.isaproject.isaproject.Model.Medicine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Specification {
    @Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    private Integer id;


    @Column(name = "contraIndications", nullable = true)
    private String contraIndications;


    @Column(name = "structure", nullable = true)
    private String structure;


    @Column(name = "recommendedConsumption", nullable = true)
    private String recommendedConsumption;


    @Column(name = "manufacturer", nullable = true)
    private String manufacturer;


    public Specification() {
    }

    public Specification(Integer id, int medicationId, String contraIndications, String structure, String recommendedConsumption, String manufacturer) {
        this.id = id;
        this.contraIndications = contraIndications;
        this.structure = structure;
        this.recommendedConsumption = recommendedConsumption;
        this.manufacturer = manufacturer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getContraIndications() {
        return contraIndications;
    }

    public void setContraIndications(String contraIndications) {
        this.contraIndications = contraIndications;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getRecommendedConsumption() {
        return recommendedConsumption;
    }

    public void setRecommendedConsumption(String recommendedConsumption) {
        this.recommendedConsumption = recommendedConsumption;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
