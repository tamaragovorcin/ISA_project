package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.ExaminationScheduleDTO;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Repository.ExaminationScheduleRepository;
import com.isaproject.isaproject.Service.IServices.IExaminationScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExaminationScheduleService implements IExaminationScheduleService {
    @Autowired
    ExaminationScheduleRepository examinationScheduleRepository;


    @Override
    public Optional<ExaminationSchedule> findById(Integer id) {
        return examinationScheduleRepository.findById(id);
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
    public ExaminationSchedule update(ExaminationSchedule examinationSchedule) {
        return null;
    }
}
