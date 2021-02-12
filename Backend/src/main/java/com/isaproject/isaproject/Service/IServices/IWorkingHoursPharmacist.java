package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.DTO.WorkingHoursPharmacistDTO;
import com.isaproject.isaproject.DTO.WorkingScheduleDTO;
import com.isaproject.isaproject.Model.Schedule.WorkingHoursPharmacist;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.Supplier;

import java.util.List;

public interface IWorkingHoursPharmacist {
    WorkingHoursPharmacist findById(Integer id);
    List<WorkingHoursPharmacist> findAll ();
    WorkingHoursPharmacist save(WorkingScheduleDTO userRequest);

}
