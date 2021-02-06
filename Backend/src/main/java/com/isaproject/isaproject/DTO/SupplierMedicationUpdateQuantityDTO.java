package com.isaproject.isaproject.DTO;

public class SupplierMedicationUpdateQuantityDTO {
    private Integer medicationId;
    private int newQuantity;

    public SupplierMedicationUpdateQuantityDTO() { }

    public SupplierMedicationUpdateQuantityDTO(Integer medicationId, int newQuantity) {
        this.medicationId = medicationId;
        this.newQuantity = newQuantity;
    }

    public Integer getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Integer medicationId) {
        this.medicationId = medicationId;
    }

    public int getNewQuantity() {
        return newQuantity;
    }

    public void setNewQuantity(int newQuantity) {
        this.newQuantity = newQuantity;
    }
}