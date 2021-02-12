package com.isaproject.isaproject.DTO;


public class LackMedicineDTO {


    private Integer id;


    private String nameMedicine;


    private String namePharmacy;

    public LackMedicineDTO(Integer id, String nameMedicine, String namePharmacy) {
        this.id = id;
        this.nameMedicine = nameMedicine;
        this.namePharmacy = namePharmacy;
    }

    public LackMedicineDTO() {

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
