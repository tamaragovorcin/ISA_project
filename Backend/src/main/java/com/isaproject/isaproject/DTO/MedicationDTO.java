package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Medicine.Medication;
import java.util.Set;

public class MedicationDTO {

    private String name;

    private long code;

    private String form;

    private String type;

    private String issuanceRegime;

    private double mark;

    private double loyaltyPoints;

    private SpecificationDTO specification;

    private Set<Medication> medicationsAlternatives;

    private String wayOfSelling;

    public MedicationDTO(String name, long code, String form, String type) {
        this.name = name;
        this.code = code;
        this.form = form;
        this.type = type;
    }

    public MedicationDTO(String name, long code, String form, String type, String issuanceRegime, double mark, double loyaltyPoints, SpecificationDTO specification, Set<Medication> medicationsAlternatives, String wayOfSelling) {
        this.name = name;
        this.code = code;
        this.form = form;
        this.type = type;
        this.issuanceRegime = issuanceRegime;
        this.mark = mark;
        this.loyaltyPoints = loyaltyPoints;
        this.specification = specification;
        this.medicationsAlternatives = medicationsAlternatives;
        this.wayOfSelling = wayOfSelling;
    }

    public MedicationDTO() { }

    public String getWayOfSelling() {
        return wayOfSelling;
    }

    public void setWayOfSelling(String wayOfSelling) {
        this.wayOfSelling = wayOfSelling;
    }

    public Set<Medication> getMedicationsAlternatives() {
        return medicationsAlternatives;
    }

    public void setMedicationsAlternatives(Set<Medication> medicationsAlternatives) {
        this.medicationsAlternatives = medicationsAlternatives;
    }

    public SpecificationDTO getSpecification() {
        return specification;
    }

    public void setSpecification(SpecificationDTO specification) {
        this.specification = specification;
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
}