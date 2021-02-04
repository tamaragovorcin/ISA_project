package com.isaproject.isaproject.DTO;

public class SpecificationDTO {

    private String contraIndications;

    private String structure;

    private String recommendedConsumption;

    private String manufacturer;

    public SpecificationDTO(String contraIndications, String structure, String recommendedConsumption, String manufacturer) {
        this.contraIndications = contraIndications;
        this.structure = structure;
        this.recommendedConsumption = recommendedConsumption;
        this.manufacturer = manufacturer;
    }

    public SpecificationDTO() {}

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