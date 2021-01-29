package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.SupplierMedicationReviewDTO;
import com.isaproject.isaproject.DTO.SupplierMedicationUpdateQuantityDTO;
import com.isaproject.isaproject.DTO.SupplierMedicationsDTO;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Medicine.SupplierMedications;
import com.isaproject.isaproject.Model.Orders.MedicationInOrder;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.Supplier;
import com.isaproject.isaproject.Repository.OrderRepository;
import com.isaproject.isaproject.Repository.SupplierMedicaionRepository;
import com.isaproject.isaproject.Repository.SupplierRepository;
import com.isaproject.isaproject.Service.IServices.ISupplierMedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class SupplierMedicationService implements ISupplierMedicationService {
    @Autowired
    SupplierMedicaionRepository supplierMedicaionRepository;

    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    OrderRepository orderRepository;


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

    public SupplierMedications update(SupplierMedicationUpdateQuantityDTO supplierMedicationDTO) {
        SupplierMedications supplierMedications = supplierMedicaionRepository.findById(supplierMedicationDTO.getMedicationId()).get();
        supplierMedications.setQuantity(supplierMedicationDTO.getNewQuantity());
        return supplierMedicaionRepository.save(supplierMedications);
    }

    public Boolean updateQuantities(Integer orderId) {
        Set<MedicationInOrder> medicationInOrder = orderRepository.findById(orderId).get().getMedicationInOrders();
        for(MedicationInOrder medication : medicationInOrder) {
            if(updateQuantityForMedication(medication.getMedicine(), medication.getQuantity())) {}
            else {
                return false;
            }
        }
        return true;
    }

    private boolean updateQuantityForMedication(Medication medication, int quantity) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        Supplier supplier = supplierRepository.findById(user.getId()).get();
        Set<SupplierMedications> medicationsSupplier =supplier.getSupplierMedications();

        for (SupplierMedications supplierMedication: medicationsSupplier) {
            if(supplierMedication.getCode()==medication.getCode() && supplierMedication.getName().equals(medication.getName())) {
                if(supplierMedication.getQuantity()>quantity) {
                    int newQuantity = supplierMedication.getQuantity() - quantity;
                    supplierMedication.setQuantity(newQuantity);
                    int newReservedQuantity = supplierMedication.getReservedQuantity() + quantity;
                    supplierMedication.setReservedQuantity(newReservedQuantity);
                    return true;
                }
            }
        }
        return false;
    }
}
