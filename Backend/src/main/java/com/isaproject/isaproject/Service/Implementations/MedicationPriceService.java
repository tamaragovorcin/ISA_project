package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.MedicationDTO;
import com.isaproject.isaproject.DTO.MedicationPriceDTO;
import com.isaproject.isaproject.Model.HelpModel.MedicationPrice;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Orders.MedicationInOrder;
import com.isaproject.isaproject.Model.Orders.Order;
import com.isaproject.isaproject.Repository.MedicationPriceRepository;
import com.isaproject.isaproject.Repository.MedicationRepository;
import com.isaproject.isaproject.Service.IServices.IMedicationPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MedicationPriceService implements IMedicationPriceService {

    @Autowired
    MedicationPriceRepository medicationPriceRepository;

    @Override
    public MedicationPrice findById(Integer id) {
        return medicationPriceRepository.getOne(id);
    }

    @Override
    public List<MedicationPrice> findAll() {
        return medicationPriceRepository.findAll();
    }

    @Override
    public MedicationPrice save(MedicationPriceDTO medicationDTO) {
        return null;
    }


    public MedicationPrice updatePrice(MedicationPriceDTO medicationDTO) {
        System.out.println("-----------------------------------------------------");
        System.out.println("DOSAO DO SERVISA");

        MedicationPrice medicationPrice = findByMedicationID(medicationDTO.getMedication().getId());
        medicationPrice.setPrice(medicationDTO.getPrice());
        medicationPrice.setDate(medicationDTO.getDate());
        return this.medicationPriceRepository.save(medicationPrice);
    }
    public MedicationPrice findByMedicationID(Integer id){
        for(MedicationPrice medicationPrice : medicationPriceRepository.findAll()){
            if(medicationPrice.getMedication().getId() == id){
                return medicationPrice;
            }
        }
        return null;
    }

    @Override
    public void delete(MedicationPrice medication) {

    }

    public List<MedicationPrice> findByPharmacy(Integer id){


        List<MedicationPrice> medicationPrices =  new ArrayList<MedicationPrice>();
           try {
               for (MedicationPrice med : medicationPriceRepository.findAll()) {
                   if (med.getPharmacy().getId() == id) {
                       medicationPrices.add(med);
                   }
               }
               return medicationPrices;
           }catch (Exception e){
               return null;
           }
    }
    public List<Medication> findMedicationByPharmacy(Integer id){


        List<Medication> medication =  new ArrayList<Medication>();
        try {
            for (MedicationPrice med : medicationPriceRepository.findAll()) {
                if (med.getPharmacy().getId() == id) {
                    medication.add(med.getMedication());
                }
            }
            return medication;
        }catch (Exception e){
            return null;
        }
    }
    public List<MedicationPrice> findByMedication(Integer id){
        List<MedicationPrice> medicationPrices =  new ArrayList<MedicationPrice>();
        for (MedicationPrice med : medicationPriceRepository.findAll()){
            if(med.getMedication().getId() == id){
                medicationPrices.add(med);
            }
        }
        return medicationPrices;
    }
    public void updateMedicineQuantityTender(Order order){
        for(MedicationPrice medicationPrice : medicationPriceRepository.findAll()){
            for(MedicationInOrder med : order.getMedicationInOrders()){
                if(medicationPrice.getPharmacy().getId() == med.getOrder().getPharmacyAdmin().getPharmacy().getId()){
                    if(medicationPrice.getMedication().getName().equals(med.getMedicine().getName())) {
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("PROSAO IF-POSTOJI");

                        Integer quantity = medicationPrice.getQuantity();
                        quantity = quantity + med.getQuantity();
                        MedicationPrice medicationPrice1 = findByName(medicationPrice.getMedication().getName());
                        medicationPrice1.setQuantity(quantity);
                        this.medicationPriceRepository.save(medicationPrice1);


                    }
                }
            }

        }
    for(MedicationInOrder medication : order.getMedicationInOrders()){
        if(findByName(medication.getMedicine().getName())== null){
            MedicationPrice medicationPrice1 = new MedicationPrice();
            medicationPrice1.setMedication(medication.getMedicine());
            medicationPrice1.setQuantity(medication.getQuantity());
            medicationPrice1.setPrice(0);
            medicationPrice1.setPharmacy(order.getPharmacyAdmin().getPharmacy());
            medicationPriceRepository.save(medicationPrice1);
        }
    }
    }
    private MedicationPrice findByName(String name){
        for(MedicationPrice medicationPrice :  medicationPriceRepository.findAll()){
            if (medicationPrice.getMedication().getName().equals(name)){
                return medicationPrice;
            }
        }
        return null;
    }
}
