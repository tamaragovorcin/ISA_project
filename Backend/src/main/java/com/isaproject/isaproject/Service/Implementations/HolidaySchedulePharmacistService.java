package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.HolidaySchedulePharmacistDTO;
import com.isaproject.isaproject.Model.Schedule.HolidaySchedulePharmacist;
import com.isaproject.isaproject.Repository.DermatologistRepository;
import com.isaproject.isaproject.Repository.HolidaySchedulePharmacistRepository;
import com.isaproject.isaproject.Service.IServices.IHolidaySchedulePharmacistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidaySchedulePharmacistService implements IHolidaySchedulePharmacistsService {
    @Autowired
    HolidaySchedulePharmacistRepository holidaySchedulePharmacistRepository;

    @Override
    public HolidaySchedulePharmacist findById(Integer id) {
        return null;
    }

    @Override
    public List<HolidaySchedulePharmacist> findAll() {
        return null;
    }

    @Override
    public HolidaySchedulePharmacist save(HolidaySchedulePharmacistDTO scheduleDTO) {
        HolidaySchedulePharmacist offer = new HolidaySchedulePharmacist();
        offer.setPharmacist(scheduleDTO.getPharmacist());
        offer.setStartDate(scheduleDTO.getStartDate());
        offer.setEndDate(scheduleDTO.getEndDate());
        offer.setType(scheduleDTO.getType());
        offer.setApproved("WAIT_FOR_RESPONSE");

        return holidaySchedulePharmacistRepository.save(offer);
    }
}
