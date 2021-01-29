package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.MedicationDTO;
import com.isaproject.isaproject.DTO.MedicationPriceDTO;
import com.isaproject.isaproject.Model.HelpModel.MedicationPrice;
import com.isaproject.isaproject.Model.Medicine.Medication;
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
        MedicationPrice medicationPrice = new MedicationPrice();
        medicationPrice.setMedication(medicationDTO.getMedication());
        medicationPrice.setPrice(medicationDTO.getPrice());
        medicationPrice.setPharmacy(medicationDTO.getPharmacy());
        medicationPrice.setDate(medicationDTO.getDate());
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
}
