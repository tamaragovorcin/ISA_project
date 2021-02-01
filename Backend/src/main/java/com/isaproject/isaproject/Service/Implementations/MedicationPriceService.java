package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.ChoosenPharmacyDTO;
import com.isaproject.isaproject.DTO.MedicationPriceDTO;
import com.isaproject.isaproject.DTO.QRcodeInformationDTO;
import com.isaproject.isaproject.Model.HelpModel.MedicationPrice;
import com.isaproject.isaproject.Model.Orders.MedicationInOrder;
import com.isaproject.isaproject.Model.Orders.Order;
import com.isaproject.isaproject.Repository.MedicationPriceRepository;
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
        System.out.println("-----------------------------------------------------");
        System.out.println("DOSAO DO SERVISA");

        MedicationPrice medicationPrice = new MedicationPrice();
        medicationPrice.setMedication(medicationDTO.getMedication());
        medicationPrice.setPrice(medicationDTO.getPrice());
        medicationPrice.setPharmacy(medicationDTO.getPharmacy());
        medicationPrice.setDate(medicationDTO.getDate());
        medicationPrice.setQuantity(0);
        return medicationPriceRepository.save(medicationPrice);
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
                    if(medicationPrice.getMedication().getName().equals(med.getMedicine().getName())){
                         Integer quantity = medicationPrice.getQuantity();
                         quantity = quantity + med.getQuantity();
                         MedicationPrice medicationPrice1 = findByName(medicationPrice.getMedication().getName());
                         medicationPrice1.setQuantity(quantity);
                         this.medicationPriceRepository.save(medicationPrice1);

                    }
                    else{
                        MedicationPrice medicationPrice1 = new MedicationPrice();
                        medicationPrice1.setMedication(med.getMedicine());
                        medicationPrice1.setQuantity(med.getQuantity());
                        medicationPrice1.setPharmacy(order.getPharmacyAdmin().getPharmacy());
                        medicationPriceRepository.save(medicationPrice1);
                }
                }
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

    public boolean updateMedicineQuantityEreceipt(ChoosenPharmacyDTO choosenPharmacy) {
        try {
            List<MedicationPrice> pharmacyMedications = findByPharmacy(choosenPharmacy.getPharmacyId());
            for (QRcodeInformationDTO medication : choosenPharmacy.getMedications()) {
                for (MedicationPrice medicationPrice : pharmacyMedications) {
                    if(medicationPrice.getMedication().getCode()==medication.getMedicationCode() &&
                            medicationPrice.getMedication().getName().equals(medication.getMedicationName())) {
                        medicationPrice.setQuantity(medication.getQuantity()-medication.getQuantity());
                        this.medicationPriceRepository.save(medicationPrice);
                    }
                }
            }
            return true;
        }
        catch(Exception e) {return false;}
    }
}
