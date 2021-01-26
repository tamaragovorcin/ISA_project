package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.WorkingHoursDermatologistDTO;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Schedule.WorkingHoursDermatologist;
import com.isaproject.isaproject.Repository.WorkingHoursDermatologistRepository;
import com.isaproject.isaproject.Service.IServices.IWorkingHoursDermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkingHoursDermatologistService implements IWorkingHoursDermatologistService {
    @Autowired
    WorkingHoursDermatologistRepository workingHoursDermatologistRepository;


    @Override
    public WorkingHoursDermatologist findById(Integer id) {
        return workingHoursDermatologistRepository.getOne(id);
    }

    @Override
    public List<WorkingHoursDermatologist> findAll() {
        return workingHoursDermatologistRepository.findAll();
    }

    @Override
    public WorkingHoursDermatologist save(WorkingHoursDermatologistDTO hoursDermatologistDTO) {
        WorkingHoursDermatologist workingHoursDermatologist = new WorkingHoursDermatologist();
        workingHoursDermatologist.setDermatologist(hoursDermatologistDTO.getDermatologist());
        workingHoursDermatologist.setPharmacy(hoursDermatologistDTO.getPharmacy());
        workingHoursDermatologist.setDate(hoursDermatologistDTO.getDate());
        workingHoursDermatologist.setStartTime(hoursDermatologistDTO.getStartTime());
        workingHoursDermatologist.setEndTime(hoursDermatologistDTO.getEndTime());

        return workingHoursDermatologistRepository.save(workingHoursDermatologist);
    }

    @Override
    public void delete(WorkingHoursDermatologist workingHoursDermatologist) {

    }
}
