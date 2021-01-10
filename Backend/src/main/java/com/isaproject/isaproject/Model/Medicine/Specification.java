package com.isaproject.isaproject.Model.Medicine;

import com.isaproject.isaproject.Model.Entity;

public class Specification extends Entity {
    private int medicationId;
    private String contraIndications;
    private String structure;
    private String recommendedConsumption;
    private String manufacturer;

    public Specification(int id, int medicationId, String contraIndications, String structure, String recommendedConsumption, String manufacturer) {
        super(id);
        this.medicationId = medicationId;
        this.contraIndications = contraIndications;
        this.structure = structure;
        this.recommendedConsumption = recommendedConsumption;
        this.manufacturer = manufacturer;
    }
    public Specification() {
        super();
    }


    public Specification(int medicationId, String contraIndications, String structure, String recommendedConsumption, String manufacturer) {
        this.medicationId = medicationId;
        this.contraIndications = contraIndications;
        this.structure = structure;
        this.recommendedConsumption = recommendedConsumption;
        this.manufacturer = manufacturer;
    }

    public int getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(int medicationId) {
        this.medicationId = medicationId;
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
