package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.PharmacyDTO;
import com.isaproject.isaproject.DTO.WorkingHoursDermatologistDTO;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Schedule.WorkingHoursDermatologist;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Repository.WorkingHoursDermatologistRepository;

import java.util.List;

public interface IWorkingHoursDermatologistService {
    WorkingHoursDermatologist findById(Integer id);
    List<WorkingHoursDermatologist> findAll ();
    WorkingHoursDermatologist save(WorkingHoursDermatologistDTO userRequest);
    void delete(WorkingHoursDermatologist workingHoursDermatologist);

}
