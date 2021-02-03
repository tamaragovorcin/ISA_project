package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.ExaminationDTO;
import com.isaproject.isaproject.DTO.ExaminationScheduleDTO;
import com.isaproject.isaproject.Model.Examinations.Examination;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.HelpModel.MedicationReservation;

import java.util.List;
import java.util.Optional;

public interface IExaminationService {

    Optional<Examination> findById(Integer id);
    List<Examination> findAll ();
    Examination save(ExaminationDTO examinationDTO);
    Examination update(Examination examination);
    void delete(Examination examination);
}
