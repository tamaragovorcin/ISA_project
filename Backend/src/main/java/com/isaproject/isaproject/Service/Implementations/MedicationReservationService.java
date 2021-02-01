package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.MedicationReservationDTO;
import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;
import com.isaproject.isaproject.Repository.MedicationReservationRepository;
import com.isaproject.isaproject.Service.IServices.IMedicationReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationReservationService implements IMedicationReservationService {

    @Autowired
    MedicationReservationRepository medicationRepository;

    @Override
    public MedicationReservation findById(Integer id) {
        return null;
    }

    @Override
    public List<MedicationReservation> findAll() {

        return medicationRepository.findAll();
    }

    @Override
    public MedicationReservation save(MedicationReservationDTO medicationDTO) {

        System.out.println(medicationDTO.getPharmacy().getPharmacyName());
        MedicationReservation medication = new MedicationReservation();
        medication.setDateOfTakeOver(medicationDTO.getDateOfTakeOver());
        medication.setPharmacy(medicationDTO.getPharmacy());
        medication.setMedicine(medicationDTO.getMedication());
        medication.setPatient(medicationDTO.getPatient());


        return medicationRepository.save(medication);
    }

    @Override
    public void delete(MedicationReservation medication) {
            medicationRepository.delete(medication);
    }
}
