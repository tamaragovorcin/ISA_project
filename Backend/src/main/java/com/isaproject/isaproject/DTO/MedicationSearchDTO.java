package com.isaproject.isaproject.DTO;

public class MedicationSearchDTO {
    private String name;

    private String form;

    private String type;

    private String issuanceRegime;

    private double mark;

    private SpecificationDTO specification;

    public MedicationSearchDTO() {
    }

    public MedicationSearchDTO(String name, String form, String type, String issuanceRegime, double mark, SpecificationDTO specification) {
        this.name = name;
        this.form = form;
        this.type = type;
        this.issuanceRegime = issuanceRegime;
        this.mark = mark;
        this.specification = specification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public SpecificationDTO getSpecification() {
        return specification;
    }

    public void setSpecification(SpecificationDTO specification) {
        this.specification = specification;
    }
}
