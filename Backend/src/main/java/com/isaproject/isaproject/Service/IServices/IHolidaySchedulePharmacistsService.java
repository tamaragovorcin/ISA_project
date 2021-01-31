package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.ConsultingDTO;
import com.isaproject.isaproject.DTO.HolidaySchedulePharmacistDTO;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Schedule.HolidaySchedulePharmacist;

import java.util.List;

public interface IHolidaySchedulePharmacistsService {

    HolidaySchedulePharmacist findById(Integer id);
    List<HolidaySchedulePharmacist> findAll ();
    HolidaySchedulePharmacist save(HolidaySchedulePharmacistDTO scheduleDTO);
}
