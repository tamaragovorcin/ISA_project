package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.HolidayScheduleDermatologistDTO;
import com.isaproject.isaproject.DTO.HolidaySchedulePharmacistDTO;
import com.isaproject.isaproject.Model.Schedule.HolidayScheduleDermatologist;
import com.isaproject.isaproject.Model.Schedule.HolidaySchedulePharmacist;

import java.util.List;

public interface IHolidayScheduleDermatologistService {
    HolidayScheduleDermatologist findById(Integer id);
    List<HolidayScheduleDermatologist> findAll ();
    HolidayScheduleDermatologist save(HolidayScheduleDermatologistDTO scheduleDTO);
}
