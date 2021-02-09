package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.AlergiesDTO;
import com.isaproject.isaproject.Model.HelpModel.PatientsMedicationAlergy;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Repository.AlergiesRepository;
import com.isaproject.isaproject.Repository.MedicationReservationRepository;
import com.isaproject.isaproject.Repository.PatientRepository;
import com.isaproject.isaproject.Service.IServices.IAlergiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AlergiesService implements IAlergiesService {

    @Autowired
    AlergiesRepository alergiesRepository;

    @Autowired
    PatientService patientService;

    @Override
    public PatientsMedicationAlergy findById(Integer id) {
        return null;
    }

    @Override
    public List<PatientsMedicationAlergy> findAll() {
        return alergiesRepository.findAll();
    }

    @Override
    public PatientsMedicationAlergy save(AlergiesDTO alergiesDTO) {

        PatientsMedicationAlergy patientsMedicationAlergy = new PatientsMedicationAlergy();
        patientsMedicationAlergy.setMedication(alergiesDTO.getMedication());
        Patient patient = patientService.findById(alergiesDTO.getId());
        patientsMedicationAlergy.setPatient(patient);
        patientsMedicationAlergy.setDescription(null);

        return alergiesRepository.save(patientsMedicationAlergy);

    }

    @Override
    public void delete(PatientsMedicationAlergy medication) {
            alergiesRepository.delete(medication);
    }
}
