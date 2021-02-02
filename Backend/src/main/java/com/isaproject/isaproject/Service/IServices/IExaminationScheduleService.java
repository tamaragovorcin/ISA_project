package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.ExaminationScheduleDTO;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;

import java.util.List;
import java.util.Optional;

public interface IExaminationScheduleService {
   ExaminationSchedule findById(Integer id);
    List<ExaminationSchedule> findAll ();
    ExaminationSchedule save(ExaminationScheduleDTO examinationScheduleDTO);
    ExaminationSchedule update(ExaminationSchedule examinationSchedule, Boolean tf);
    void delete(ExaminationSchedule medication);
}
