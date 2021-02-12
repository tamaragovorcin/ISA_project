package com.isaproject.isaproject.Service.Implementations;
import com.isaproject.isaproject.DTO.MedicationReservationDTO;
import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Repository.MedicationReservationRepository;
import com.isaproject.isaproject.Service.IServices.IMedicationReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class MedicationReservationService implements IMedicationReservationService {

    @Autowired
    MedicationReservationRepository medicationRepository;

    @Autowired
    MedicationService medicationService;

    @Autowired
    PatientService patientService;

    @Autowired
    PharmacyService pharmacyService;

    @Override
    public MedicationReservation findById(Integer id) {
        return medicationRepository.findById(id).get();
    }

    @Override
    public List<MedicationReservation> findAll() {

        return medicationRepository.findAll();
    }

    @Override
    public MedicationReservation save(MedicationReservationDTO medicationDTO, UUID uuid) {


        Medication medication = medicationService.findById(medicationDTO.getMedicationId());
        Pharmacy pharmacy = pharmacyService.findById(medicationDTO.getPharmacyId());
        MedicationReservation medicationReservation = new MedicationReservation();
        medicationReservation.setDateOfTakeOver(medicationDTO.getDateOfTakeOver());
        medicationReservation.setPharmacy(pharmacy);
        medicationReservation.setMedicine(medication);
        Patient patient = patientService.findById(medicationDTO.getPatient());
        medicationReservation.setPatient(patient);
        medicationReservation.setDateOfReservation(LocalDate.now());
        medicationReservation.setReservationCode(uuid);
        return medicationRepository.save(medicationReservation);
    }

    @Override
    public void delete(MedicationReservation medication) {
            medicationRepository.delete(medication);
    }
}
