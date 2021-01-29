package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.SupplierMedicationReviewDTO;
import com.isaproject.isaproject.DTO.SupplierMedicationsDTO;
import com.isaproject.isaproject.Model.Medicine.SupplierMedications;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.Supplier;
import com.isaproject.isaproject.Repository.SupplierMedicaionRepository;
import com.isaproject.isaproject.Repository.SupplierRepository;
import com.isaproject.isaproject.Service.IServices.ISupplierMedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SupplierMedicationService implements ISupplierMedicationService {
    @Autowired
    SupplierMedicaionRepository supplierMedicaionRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public SupplierMedications findById(Integer id) {
        return supplierMedicaionRepository.findById(id).get();
    }

    @Override
    public List<SupplierMedications> findAll() {
        return supplierMedicaionRepository.findAll();
    }

    @Override
    public SupplierMedications save(SupplierMedicationsDTO supplierMedicationsDTO) {
        System.out.println("////////////////////////////////////////////////////////////////");

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();

        Supplier supplier= supplierRepository.findById(user.getId()).get();
        SupplierMedications supplierMedications = new SupplierMedications();
        supplierMedications.setSupplier(supplier);
        supplierMedications.setName(supplierMedicationsDTO.getName());
        supplierMedications.setCode(supplierMedicationsDTO.getCode());
        supplierMedications.setForm(supplierMedicationsDTO.getForm());
        supplierMedications.setQuantity(supplierMedicationsDTO.getQuantity());
        supplierMedications.setType(supplierMedicationsDTO.getType());
        supplierMedications.setReservedQuantity(0);

        return supplierMedicaionRepository.save(supplierMedications);
    }

    public SupplierMedications update(SupplierMedicationReviewDTO medication) {
        SupplierMedications supplierMedications = supplierMedicaionRepository.findById(medication.getId()).get();
        supplierMedications.setType(medication.getType());
        supplierMedications.setForm(medication.getForm());
        supplierMedications.setQuantity(medication.getQuantity());
        return supplierMedicaionRepository.save(supplierMedications);
    }
}
