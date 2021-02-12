package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import java.util.List;

public class MedicineAvailabilityQRDTO {
    private Integer pharmacy;
    List<QRcodeInformationDTO> listMedications;

    public MedicineAvailabilityQRDTO() { }

    public Integer getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Integer pharmacy) {
        this.pharmacy = pharmacy;
    }

    public List<QRcodeInformationDTO> getListMedications() {
        return listMedications;
    }

    public void setListMedications(List<QRcodeInformationDTO> listMedications) {
        this.listMedications = listMedications;
    }
}
