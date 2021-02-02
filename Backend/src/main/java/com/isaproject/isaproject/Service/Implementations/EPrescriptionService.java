package com.isaproject.isaproject.Service.Implementations;
import com.isaproject.isaproject.DTO.QRcodeInformationDTO;
import com.isaproject.isaproject.Model.Examinations.EPrescription;
import com.isaproject.isaproject.Model.Medicine.MedicationEPrescription;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Repository.EPrescriptionRepository;
import com.isaproject.isaproject.Repository.MedicationEPrescriptionRepository;
import com.isaproject.isaproject.Repository.PatientRepository;
import com.isaproject.isaproject.Service.IServices.IEPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class EPrescriptionService implements IEPrescriptionService {
    @Autowired
    EPrescriptionRepository ePrescriptionRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    MedicationEPrescriptionRepository medicationEPrescriptionRepository;

    @Override
    public EPrescription findById(Integer id) {
        return ePrescriptionRepository.findById(id).get();
    }

    @Override
    public List<EPrescription> findAll() {
        return ePrescriptionRepository.findAll();
    }

    @Override
    public EPrescription save(List<QRcodeInformationDTO> qRcodeInformationDTOS) {
        try {
            Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
            PersonUser user = (PersonUser) currentUser.getPrincipal();
            Patient patient = patientRepository.getOne(user.getId());
            EPrescription ePrescription = new EPrescription();
            ePrescription.setPatient(patient);
            ePrescription.setDate(LocalDate.now());
            ePrescription.setCode(UUID.randomUUID());
            EPrescription ePrescription1= ePrescriptionRepository.save(ePrescription);

            for (QRcodeInformationDTO medication : qRcodeInformationDTOS) {
                MedicationEPrescription medicationEPrescription = new MedicationEPrescription();
                medicationEPrescription.setCode(medication.getMedicationCode());
                medicationEPrescription.setName(medication.getMedicationName());
                medicationEPrescription.setQuantity(medicationEPrescription.getQuantity());
                medicationEPrescription.setePrescription(ePrescription1);
                medicationEPrescriptionRepository.save(medicationEPrescription);
            }
            return ePrescription1;
        }
        catch (Exception e) {
            return null;
        }
    }
}
