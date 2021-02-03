package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.PrescriptionDTO;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Examinations.Prescription;
import com.isaproject.isaproject.Repository.ConsultingRepository;
import com.isaproject.isaproject.Repository.PrescriptionRepository;
import com.isaproject.isaproject.Service.IServices.IPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService implements IPrescriptionService {

    @Autowired
    PrescriptionRepository prescriptionRepository;


    @Override
    public Prescription findById(Integer id) {
        return prescriptionRepository.findById(id).get();
    }

    @Override
    public List<Prescription> findAll() {
        return prescriptionRepository.findAll();
    }

    @Override
    public Prescription save(PrescriptionDTO prescriptionDTO) {
        System.out.println("+****************************************************************");
        System.out.println(prescriptionDTO.getDate());

        Prescription prescription = new Prescription();
        prescription.setPatient(prescriptionDTO.getPatient());
        prescription.setPharmacy(prescriptionDTO.getPharmacy());
        prescription.setDate(prescriptionDTO.getDate());
        prescription.setTaken(false);
        prescription.setDurationOfTherapy(prescriptionDTO.getDurationOfTherapy());
        prescription.setMedications(prescriptionDTO.getMedications());
        prescription.setInformation("");

        return this.prescriptionRepository.save(prescription);
    }

    @Override
    public Prescription update(Prescription prescription) {
        return this.prescriptionRepository.save(prescription);

    }
}
