package com.isaproject.isaproject.Service.IServices;
import com.isaproject.isaproject.DTO.SupplierMedicationsDTO;
import com.isaproject.isaproject.Model.Medicine.SupplierMedications;

import java.util.List;

public interface ISupplierMedicationService {
    SupplierMedications findById(Integer id);
    List<SupplierMedications> findAll ();
    SupplierMedications save(SupplierMedicationsDTO supplierMedicationsDTO);
}
