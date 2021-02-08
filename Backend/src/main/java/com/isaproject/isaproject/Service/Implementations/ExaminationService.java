package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.ExaminationDTO;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Examinations.Examination;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Repository.ExaminationRepository;
import com.isaproject.isaproject.Repository.ExaminationScheduleRepository;
import com.isaproject.isaproject.Repository.PatientRepository;
import com.isaproject.isaproject.Service.IServices.IExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ExaminationService implements IExaminationService {

    @Autowired
    ExaminationRepository examinationRepository;

    @Autowired
    ExaminationScheduleRepository examinationScheduleRepository;
    @Autowired
    PatientRepository patientRepository;

    @Override
    public Examination getById(Integer id) {
        return examinationRepository.findById(id).get();
    }

    @Override
    public Optional<Examination> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Examination> findAll() {
        return examinationRepository.findAll();
    }

    @Override
    public Examination save(ExaminationDTO examinationDTO) {
        List<ExaminationSchedule> es = new ArrayList<ExaminationSchedule>();
        ExaminationSchedule examinationSchedule =  new ExaminationSchedule();
        es = examinationScheduleRepository.findAll();

        for (ExaminationSchedule e : es){
            if(examinationDTO.getExaminationId() == e.getId()){
                examinationSchedule = e;
                System.out.println(examinationSchedule);
            }
        }

        Examination examination = new Examination();

        examination.setExaminationSchedule(examinationSchedule);
        examination.setInformation(examinationDTO.getInformation());
        examination.setCancelled(examinationDTO.getCancelled());
        examination.setShowedUp(examinationDTO.getShowedUp());
        examination.setPatient(examinationDTO.getPatient());

        return examinationRepository.save(examination);
    }

    @Override
    public Examination update(Examination examination) {
        return null;
    }

    @Override
    public void delete(Examination examination) {
        examinationRepository.delete(examination);
    }

    public Boolean checkIfPatientHasExamination(Integer pharmacyId) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        Patient patient = patientRepository.findById(user.getId()).get();

        Set<Examination> examinations = patient.getExaminations();
        for (Examination examination : examinations) {
            if(examination.getShowedUp()==true && examination.getExaminationSchedule().getPharmacy().getId()==pharmacyId) {
                return true;
            }
        }
        return false;
    }

    public boolean canMakeComplaintDermatologist(Integer dermatologistId) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        Patient patient = patientRepository.findById(user.getId()).get();

        Boolean able = false;

        List<Examination> examinations = findAll();

        for(Examination examination: examinations){
            if(examination.getExaminationSchedule().getDermatologist().getId() == dermatologistId && examination.getPatient().getId()== patient.getId()){
                if(examination.getShowedUp()) {
                    able = true;
                }
            }
        }
        return able;
    }
}
