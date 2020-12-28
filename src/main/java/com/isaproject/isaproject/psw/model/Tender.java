package com.isaproject.isaproject.psw.model;

import java.util.List;

public class Tender {
    private String date;
    private List<MedicineQuantity> medicineQuantityList;
    private int id;
    private String priceOffer;
    private String pharmacyApi;

    public Tender(){}


    public Tender(int id, String date, List<MedicineQuantity> medicineQuantityList) {
        this.id = id;
        this.date = date;
        this.medicineQuantityList = medicineQuantityList;
        this.priceOffer ="";
        this.pharmacyApi="";
    }

    public String getPharmacyApi() {
        return pharmacyApi;
    }

    public void setPharmacyApi(String pharmacyApi) {
        this.pharmacyApi = pharmacyApi;
    }

    public String getPriceOffer() {
        return priceOffer;
    }

    public void setPriceOffer(String priceOffer) {
        this.priceOffer = priceOffer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<MedicineQuantity> getMedicineQuantityList() {
        return medicineQuantityList;
    }

    public void setMedicineQuantityList(List<MedicineQuantity> medicineQuantityList) {
        this.medicineQuantityList = medicineQuantityList;
    }
}
