package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.HolidayScheduleDermatologistDTO;
import com.isaproject.isaproject.Model.Schedule.HolidayScheduleDermatologist;
import com.isaproject.isaproject.Repository.HolidayScheduleDermatologistRepository;
import com.isaproject.isaproject.Service.IServices.IHolidayScheduleDermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class HolidayScheduleDermatologistService implements IHolidayScheduleDermatologistService {
    @Autowired
    HolidayScheduleDermatologistRepository holidayScheduleRepository;


    @Override
    public HolidayScheduleDermatologist findById(Integer id) {
        return null;
    }

    @Override
    public List<HolidayScheduleDermatologist> findAll() {
        return null;
    }

    @Override
    public HolidayScheduleDermatologist save(HolidayScheduleDermatologistDTO scheduleDTO) {
        HolidayScheduleDermatologist offer = new HolidayScheduleDermatologist();
        offer.setDermatologist(scheduleDTO.getDermatologist());
        offer.setStartDate(scheduleDTO.getStartDate());
        offer.setEndDate(scheduleDTO.getEndDate());
        offer.setType(scheduleDTO.getType());
        offer.setApproved("WAIT_FOR_RESPONSE");

        return holidayScheduleRepository.save(offer);
    }
}
