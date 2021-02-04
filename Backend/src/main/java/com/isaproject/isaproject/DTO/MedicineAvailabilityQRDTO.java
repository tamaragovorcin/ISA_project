package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import java.util.List;

public class MedicineAvailabilityQRDTO {
    private Pharmacy pharmacy;
    List<QRcodeInformationDTO> listMedications;

    public MedicineAvailabilityQRDTO() { }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public List<QRcodeInformationDTO> getListMedications() {
        return listMedications;
    }

    public void setListMedications(List<QRcodeInformationDTO> listMedications) {
        this.listMedications = listMedications;
    }
}
