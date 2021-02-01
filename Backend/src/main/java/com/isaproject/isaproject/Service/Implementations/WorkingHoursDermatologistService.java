package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.WorkingHoursDermatologistDTO;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Schedule.*;
import com.isaproject.isaproject.Repository.*;
import com.isaproject.isaproject.Service.IServices.IWorkingHoursDermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkingHoursDermatologistService implements IWorkingHoursDermatologistService {
    @Autowired
    WorkingHoursDermatologistRepository workingHoursDermatologistRepository;
    @Autowired
    MondayScheduleRepository mondayScheduleRepository;
    @Autowired
    TuesdayScheduleRepository tuesdayScheduleRepository;
    @Autowired
    WednesdayScheduleRepository wednesdayScheduleRepository;
    @Autowired
    ThursdayScheduleRepository thursdayScheduleRepository;
    @Autowired
    FridayScheduleRepository fridayScheduleRepository;
    @Autowired
    SaturdayScheduleRepository saturdayScheduleRepository;
    @Autowired
    SundayScheduleRepository sundayScheduleRepository;


    @Override
    public WorkingHoursDermatologist findById(Integer id) {
        return workingHoursDermatologistRepository.getOne(id);
    }

    @Override
    public List<WorkingHoursDermatologist> findAll() {
        return workingHoursDermatologistRepository.findAll();
    }

    @Override
    public WorkingHoursDermatologist save(WorkingHoursDermatologistDTO userRequest) {
        WorkingHoursDermatologist workingHoursDermatologist = new WorkingHoursDermatologist();

        workingHoursDermatologist.setDermatologist(userRequest.getDermatologist());
        workingHoursDermatologist.setPharmacy(userRequest.getPharmacy());

        MondaySchedule mondaySchedule = new MondaySchedule();
        mondaySchedule.setStartTime(userRequest.getStartTimeMonday());
        mondaySchedule.setEndTime(userRequest.getEndTimeMonday());
        mondayScheduleRepository.save(mondaySchedule);
        workingHoursDermatologist.setMondaySchedule(mondaySchedule);

        ThursdaySchedule thursdaySchedule = new ThursdaySchedule();
        thursdaySchedule.setStartTime(userRequest.getStartTimeThursday());
        thursdaySchedule.setEndTime(userRequest.getEndTimeThursday());
        thursdayScheduleRepository.save(thursdaySchedule);
        workingHoursDermatologist.setThursdaySchedule(thursdaySchedule);

        WednesdaySchedule wednesdaySchedule = new WednesdaySchedule();
        wednesdaySchedule.setStartTime(userRequest.getStartTimeWednesday());
        wednesdaySchedule.setEndTime(userRequest.getEndTimeWednesday());
        wednesdayScheduleRepository.save(wednesdaySchedule);
        workingHoursDermatologist.setWednesdaySchedule(wednesdaySchedule);

        TuesdaySchedule tuesdaySchedule = new TuesdaySchedule();
        tuesdaySchedule.setStartTime(userRequest.getStartTimeTuesday());
        tuesdaySchedule.setEndTime(userRequest.getEndTimeTuesday());
        tuesdayScheduleRepository.save(tuesdaySchedule);
        workingHoursDermatologist.setTuesdaySchedule(tuesdaySchedule);

        FridaySchedule fridaySchedule = new FridaySchedule();
        fridaySchedule.setStartTime(userRequest.getStartTimeFriday());
        fridaySchedule.setEndTime(userRequest.getEndTimeFriday());
        fridayScheduleRepository.save(fridaySchedule);
        workingHoursDermatologist.setFridaySchedule(fridaySchedule);

        SundaySchedule sundaySchedule = new SundaySchedule();
        sundaySchedule.setStartTime(userRequest.getStartTimeSunday());
        sundaySchedule.setEndTime(userRequest.getEndTimeSunday());
        sundayScheduleRepository.save(sundaySchedule);
        workingHoursDermatologist.setSundaySchedule(sundaySchedule);

        SaturdaySchedule saturdaySchedule = new SaturdaySchedule();
        saturdaySchedule.setStartTime(userRequest.getStartTimeSaturday());
        saturdaySchedule.setEndTime(userRequest.getEndTimeSaturday());
        saturdayScheduleRepository.save(saturdaySchedule);
        workingHoursDermatologist.setSaturdaySchedule(saturdaySchedule);

        return workingHoursDermatologistRepository.save(workingHoursDermatologist);

    }

    @Override
    public void delete(WorkingHoursDermatologist workingHoursDermatologist) {

    }
}
