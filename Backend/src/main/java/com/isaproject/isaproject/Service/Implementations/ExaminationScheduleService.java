package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.ExaminationScheduleDTO;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.Users.Authority;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.Supplier;
import com.isaproject.isaproject.Repository.ExaminationScheduleRepository;
import com.isaproject.isaproject.Service.IServices.IExaminationScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExaminationScheduleService implements IExaminationScheduleService {
    @Autowired
    ExaminationScheduleRepository examinationScheduleRepository;


    @Override
    public ExaminationSchedule findById(Integer id) {
        return examinationScheduleRepository.findById(id).get();
    }

    @Override
    public List<ExaminationSchedule> findAll() {
        return examinationScheduleRepository.findAll();
    }

    @Override
    public ExaminationSchedule save(ExaminationScheduleDTO examinationScheduleDTO) {
        ExaminationSchedule examinationSchedule = new ExaminationSchedule();
        examinationSchedule.setPharmacy(examinationScheduleDTO.getPharmacy());
        examinationSchedule.setDate(examinationScheduleDTO.getDate());
        examinationSchedule.setDermatologist(examinationScheduleDTO.getDermatologist());
        examinationSchedule.setDuration(10);
        examinationSchedule.setPrice(20);
        examinationSchedule.setStartTime(examinationScheduleDTO.getStartTime());
        examinationSchedule.setFinished(false);

        return examinationScheduleRepository.save(examinationSchedule);
    }

    @Override
    public ExaminationSchedule update(ExaminationSchedule examinationSchedule, Boolean tf) {


        ExaminationSchedule examinationSchedule1= findById(examinationSchedule.getId());
        examinationSchedule1.setFinished(tf);

        return this.examinationScheduleRepository.save(examinationSchedule1);
    }

    @Override
    public void delete(ExaminationSchedule medication) {
        examinationScheduleRepository.delete(medication);
    }
}
