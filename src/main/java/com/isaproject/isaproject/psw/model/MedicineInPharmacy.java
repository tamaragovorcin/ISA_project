package com.isaproject.isaproject.psw.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="medicinesPharmacy")
public class MedicineInPharmacy {
    @Id
    private int id;
    private String pharmacyApi;
    private String medicineName;
    private int quantity;

    public MedicineInPharmacy(int id, String pharmacyApi, String medicineName, int quantity) {
        super();
        this.id = id;
        this.pharmacyApi = pharmacyApi;
        this.medicineName = medicineName;
        this.quantity = quantity;
    }
    public MedicineInPharmacy() {
        super();
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPharmacyApi() {
        return pharmacyApi;
    }
    public void setPharmacyApi(String name) {
        this.pharmacyApi = pharmacyApi;
    }
    public String getMedicineName() {
        return medicineName;
    }
    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
