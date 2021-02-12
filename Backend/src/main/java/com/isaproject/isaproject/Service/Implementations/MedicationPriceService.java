package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Model.HelpModel.MedicationPrice;
import com.isaproject.isaproject.Model.HelpModel.MedicationPriceHistory;
import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Orders.MedicationInOrder;
import com.isaproject.isaproject.Model.Orders.Order;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Repository.MedicationPriceRepository;
import com.isaproject.isaproject.Repository.PatientRepository;
import com.isaproject.isaproject.Service.IServices.IMedicationPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MedicationPriceService implements IMedicationPriceService {

    @Autowired
    MedicationPriceRepository medicationPriceRepository;
    @Autowired
    MedicationReservationService medicationReservationService;
    @Autowired
    PharmacyService pharmacyService;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    LoyaltyProgramService loyaltyProgramService;
    @Autowired
    MedicationService medicationService;
    @Autowired
    MedicationPriceHistoryService medicationPriceHistoryService;

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
        MedicationPrice medicationPrice1 = new MedicationPrice();
        Pharmacy pharmacy = pharmacyService.findById(medicationDTO.getPharmacy());
        medicationPrice1.setMedication(medicationDTO.getMedication());
        medicationPrice1.setQuantity(0);
        medicationPrice1.setPrice(medicationDTO.getPrice());
        medicationPrice1.setDate(medicationDTO.getDate());
        medicationPrice1.setPharmacy(pharmacy);
        MedicationPriceHistoryDTO dto = new MedicationPriceHistoryDTO();
        dto.setMedication(medicationDTO.getMedication().getId());
        dto.setPharmacy(medicationDTO.getPharmacy());
        dto.setStartDate(LocalDate.now());
        dto.setEndDate(medicationDTO.getDate());
        dto.setPrice(medicationDTO.getPrice());
        medicationPriceHistoryService.save(dto);


        return medicationPriceRepository.save(medicationPrice1);

    }


    public MedicationPrice updatePrice(MedicationPriceDTO medicationDTO) {

        MedicationPrice medicationPrice = findByMedicationID(medicationDTO.getMedication().getId());
        medicationPrice.setPrice(medicationDTO.getPrice());
        medicationPrice.setDate(medicationDTO.getDate());
        MedicationPriceHistoryDTO medicationPriceHistoryDTO = new MedicationPriceHistoryDTO();
        medicationPriceHistoryDTO.setMedication(medicationDTO.getMedication().getId());
        medicationPriceHistoryDTO.setPharmacy(medicationDTO.getPharmacy());
        medicationPriceHistoryDTO.setPrice(medicationDTO.getPrice());
        medicationPriceHistoryDTO.setStartDate(LocalDate.now());
        medicationPriceHistoryDTO.setEndDate(medicationDTO.getDate());
        for(MedicationPriceHistory medicationPriceHistory : medicationPriceHistoryService.findAll()){
            if(medicationPriceHistory.getMedication_id() == medicationDTO.getMedication().getId()){
                if(medicationPriceHistory.getStartDate().isBefore(LocalDate.now().plusDays(1))) {
                    if (medicationPriceHistory.getEndDate().isBefore(medicationDTO.getDate()) || medicationPriceHistory.getEndDate().isEqual(medicationDTO.getDate())) {
                        medicationPriceHistoryService.delete(medicationPriceHistory);
                    }
                }
            }
        }
        medicationPriceHistoryService.save(medicationPriceHistoryDTO);
        return this.medicationPriceRepository.save(medicationPrice);


    }
    public MedicationPrice findByMedicationID(Integer id){
        System.out.println(id);
        for(MedicationPrice medicationPrice : medicationPriceRepository.findAll()){
            if(medicationPrice.getMedication().getId().toString().equals(id.toString())){
                return medicationPrice;
            }
        }
        return null;
    }

    @Override
    public void delete(MedicationPrice medication) {

    }

    @Override
    public MedicationPrice update(MedicationPrice medicationPrice) {
        return medicationPriceRepository.save(medicationPrice);
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
                if(medicationPrice.getPharmacy().getId().toString().equals(med.getOrder().getPharmacyAdmin().getPharmacy().getId().toString())){
                    if(medicationPrice.getMedication().getName().equals(med.getMedicine().getName())) {
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("PROSAO IF-POSTOJI");

                        Integer quantity = medicationPrice.getQuantity();
                        quantity +=  + med.getQuantity();
                        MedicationPrice medicationPrice1 = findByName(medicationPrice.getMedication().getName(),order.getPharmacyAdmin().getPharmacy());
                        medicationPrice1.setQuantity(quantity);
                        this.medicationPriceRepository.save(medicationPrice1);


                    }
                }
            }

        }
    for(MedicationInOrder medication : order.getMedicationInOrders()){
        if(findByName(medication.getMedicine().getName(),order.getPharmacyAdmin().getPharmacy())== null){
            MedicationPrice medicationPrice1 = new MedicationPrice();
            medicationPrice1.setMedication(medication.getMedicine());
            medicationPrice1.setQuantity(medication.getQuantity());
            medicationPrice1.setPrice(0);
            medicationPrice1.setPharmacy(order.getPharmacyAdmin().getPharmacy());
            medicationPriceRepository.save(medicationPrice1);
        }
    }
    }
    private MedicationPrice findByName(String name, Pharmacy pharmacy){
        for(MedicationPrice medicationPrice :  medicationPriceRepository.findAll()){
            if (medicationPrice.getMedication().getName().equals(name) && medicationPrice.getPharmacy().getId() == pharmacy.getId()){
                return medicationPrice;
            }
        }
        return null;
    }
    public Boolean remove(MedicationForRemovingDTO dto) {
    Medication medication = medicationService.findById(dto.getMedication());
        for (MedicationPrice medicationPrice : medicationPriceRepository.findAll()) {
            if (medicationPrice.getMedication().getCode() == medication.getCode() && medicationPrice.getMedication().getName().equals(medication.getName())){
                if( medicationPrice.getPharmacy().getId() == dto.getPharmacy()) {
                        if (!isMedicationReserved(dto)) {
                            medicationPriceRepository.delete(medicationPrice);
                            return true;
                        }
                    }
            }
        }
        return false;
    }

    public Boolean isMedicationReserved(MedicationForRemovingDTO dto){
        Medication medication = medicationService.findById(dto.getMedication());
        for(MedicationReservation medicationReservation : medicationReservationService.findAll()) {
            if (medicationReservation.getMedicine().getCode() == medication.getCode() && medicationReservation.getMedicine().getName().equals(medication.getName())
                    && medicationReservation.getPharmacy().getId() == dto.getPharmacy() && medicationReservation.getCollected() == null) {

                return true;
            }
            if (medicationReservation.getCollected() != null){
                if (medicationReservation.getMedicine().getCode() == medication.getCode() && medicationReservation.getMedicine().getName().equals(medication.getName())
                        && medicationReservation.getPharmacy().getId() == dto.getPharmacy() && !medicationReservation.getCollected()) {

                    return true;
                }
        }
        }

        return false;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean updateMedicineQuantityEreceipt(ChoosenPharmacyDTO choosenPharmacy) {
        int points = 0;
        try {
            List<MedicationPrice> pharmacyMedications = findByPharmacy(choosenPharmacy.getPharmacyId());
            for (QRcodeInformationDTO medication : choosenPharmacy.getMedications()) {
                for (MedicationPrice medicationPrice : pharmacyMedications) {
                    if(medicationPrice.getMedication().getCode()==medication.getMedicationCode() &&
                            medicationPrice.getMedication().getName().equals(medication.getMedicationName())) {
                        medicationPrice.setQuantity(medicationPrice.getQuantity()-medication.getQuantity());
                        points += medicationPrice.getMedication().getLoyaltyPoints();
                        this.medicationPriceRepository.save(medicationPrice);
                    }
                }
            }

            loyaltyProgramService.updatePatientsLoyaltyPoints(points);
            return true;
        }
        catch(Exception e) {return false;}
    }

}
