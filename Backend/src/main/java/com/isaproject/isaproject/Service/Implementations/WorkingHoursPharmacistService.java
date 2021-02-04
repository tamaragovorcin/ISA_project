package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.WorkingHoursPharmacistDTO;
import com.isaproject.isaproject.Model.Schedule.*;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Repository.*;
import com.isaproject.isaproject.Service.IServices.IWorkingHoursPharmacist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkingHoursPharmacistService implements IWorkingHoursPharmacist {
    @Autowired
    WorkingHoursPharmacistRepository workingHoursPharmacistRepository;
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
    public WorkingHoursPharmacist findById(Integer id) {
        return workingHoursPharmacistRepository.getOne(id);
    }


    @Override
    public List<WorkingHoursPharmacist> findAll() {
        return workingHoursPharmacistRepository.findAll();
    }

    @Override
    public WorkingHoursPharmacist save(WorkingHoursPharmacistDTO userRequest) {
        System.out.println("------------------------------------------------------------------");
        System.out.println("----POGODIO SERVIS---");

        WorkingHoursPharmacist workingHoursPharmacist = new WorkingHoursPharmacist();
            workingHoursPharmacist.setPharmacist(userRequest.getPharmacist());

            MondaySchedule mondaySchedule = new MondaySchedule();
            mondaySchedule.setStartTime(userRequest.getStartTimeMonday());
            mondaySchedule.setEndTime(userRequest.getEndTimeMonday());
            mondayScheduleRepository.save(mondaySchedule);
            workingHoursPharmacist.setMondaySchedule(mondaySchedule);

            ThursdaySchedule thursdaySchedule = new ThursdaySchedule();
            thursdaySchedule.setStartTime(userRequest.getStartTimeThursday());
            thursdaySchedule.setEndTime(userRequest.getEndTimeThursday());
            thursdayScheduleRepository.save(thursdaySchedule);
            workingHoursPharmacist.setThursdaySchedule(thursdaySchedule);

            WednesdaySchedule wednesdaySchedule = new WednesdaySchedule();
            wednesdaySchedule.setStartTime(userRequest.getStartTimeWednesday());
            wednesdaySchedule.setEndTime(userRequest.getEndTimeWednesday());
            wednesdayScheduleRepository.save(wednesdaySchedule);
            workingHoursPharmacist.setWednesdaySchedule(wednesdaySchedule);

            TuesdaySchedule tuesdaySchedule = new TuesdaySchedule();
            tuesdaySchedule.setStartTime(userRequest.getStartTimeTuesday());
            tuesdaySchedule.setEndTime(userRequest.getEndTimeTuesday());
            tuesdayScheduleRepository.save(tuesdaySchedule);
            workingHoursPharmacist.setTuesdaySchedule(tuesdaySchedule);

            FridaySchedule fridaySchedule = new FridaySchedule();
            fridaySchedule.setStartTime(userRequest.getStartTimeFriday());
            fridaySchedule.setEndTime(userRequest.getEndTimeFriday());
            fridayScheduleRepository.save(fridaySchedule);
            workingHoursPharmacist.setFridaySchedule(fridaySchedule);

            SundaySchedule sundaySchedule = new SundaySchedule();
            sundaySchedule.setStartTime(userRequest.getStartTimeSunday());
            sundaySchedule.setEndTime(userRequest.getEndTimeSunday());
            sundayScheduleRepository.save(sundaySchedule);
            workingHoursPharmacist.setSundaySchedule(sundaySchedule);

            SaturdaySchedule saturdaySchedule = new SaturdaySchedule();
            saturdaySchedule.setStartTime(userRequest.getStartTimeSaturday());
            saturdaySchedule.setEndTime(userRequest.getEndTimeSaturday());
            saturdayScheduleRepository.save(saturdaySchedule);
            workingHoursPharmacist.setSaturdaySchedule(saturdaySchedule);

            return workingHoursPharmacistRepository.save(workingHoursPharmacist);



    }



}
