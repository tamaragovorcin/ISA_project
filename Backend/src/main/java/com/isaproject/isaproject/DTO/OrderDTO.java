package com.isaproject.isaproject.DTO;
import com.isaproject.isaproject.Model.Users.PharmacyAdmin;
import java.time.LocalDate;
import java.util.Set;

public class OrderDTO {
    private PharmacyAdmin pharmacyAdmin;
    private LocalDate date;
    private String status;
    private Set<MedicationsInOrderDTO> medicationsInOrderDTO;

    public OrderDTO() {}

    public OrderDTO(PharmacyAdmin pharmacyAdmin, LocalDate date, String status, Set<MedicationsInOrderDTO> medicationsInOrderDTO) {
        this.pharmacyAdmin = pharmacyAdmin;
        this.date = date;
        this.status = status;
        this.medicationsInOrderDTO = medicationsInOrderDTO;
    }

    public Set<MedicationsInOrderDTO> getMedicationsInOrderDTO() {
        return medicationsInOrderDTO;
    }

    public void setMedicationsInOrderDTO(Set<MedicationsInOrderDTO> medicationsInOrderDTO) {
        this.medicationsInOrderDTO = medicationsInOrderDTO;
    }

    public PharmacyAdmin getPharmacyAdmin() {
        return pharmacyAdmin;
    }

    public void setPharmacyAdmin(PharmacyAdmin pharmacyAdmin) {
        this.pharmacyAdmin = pharmacyAdmin;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
