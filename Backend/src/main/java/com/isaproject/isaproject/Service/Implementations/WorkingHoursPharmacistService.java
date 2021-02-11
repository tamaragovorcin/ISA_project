package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.WorkingHoursPharmacistDTO;
import com.isaproject.isaproject.DTO.WorkingScheduleDTO;
import com.isaproject.isaproject.Model.Schedule.*;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.Pharmacist;
import com.isaproject.isaproject.Repository.*;
import com.isaproject.isaproject.Service.IServices.IWorkingHoursPharmacist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
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
    @Autowired
    PharmacistService pharmacistService;



    @Override
    public WorkingHoursPharmacist findById(Integer id) {
        return workingHoursPharmacistRepository.getOne(id);
    }


    @Override
    public List<WorkingHoursPharmacist> findAll() {
        return workingHoursPharmacistRepository.findAll();
    }

    @Override
    public WorkingHoursPharmacist save(WorkingScheduleDTO userRequest) {
        System.out.println("------------------------------------------------------------------");
        System.out.println("----POGODIO SERVIS---");
        Pharmacist pharmacist = pharmacistService.findById(userRequest.getPharmacistId());
        if(userRequest.getStartTimeMonday()==null){
            userRequest.setStartTimeMonday(LocalTime.MIDNIGHT);
        }
        if(userRequest.getEndTimeMonday()==null){
            userRequest.setEndTimeMonday(LocalTime.MIDNIGHT);
        }
        if(userRequest.getStartTimeTuesday()==null){
            userRequest.setStartTimeTuesday(LocalTime.MIDNIGHT);
        }
        if(userRequest.getEndTimeTuesday()==null){
            userRequest.setEndTimeTuesday(LocalTime.MIDNIGHT);
        }
        if(userRequest.getStartTimeWednesday()==null){
            userRequest.setStartTimeWednesday(LocalTime.MIDNIGHT);
        }
        if(userRequest.getEndTimeWednesday()==null){
            userRequest.setEndTimeWednesday(LocalTime.MIDNIGHT);
        }
        if(userRequest.getStartTimeThursday()==null){
            userRequest.setStartTimeThursday(LocalTime.MIDNIGHT);
        }
        if(userRequest.getEndTimeThursday()==null){
            userRequest.setEndTimeThursday(LocalTime.MIDNIGHT);
        }
        if(userRequest.getStartTimeFriday()==null){
            userRequest.setStartTimeFriday(LocalTime.MIDNIGHT);
        }
        if(userRequest.getEndTimeFriday()==null){
            userRequest.setEndTimeFriday(LocalTime.MIDNIGHT);
        }
        if(userRequest.getStartTimeSaturday()==null){
            userRequest.setStartTimeSaturday(LocalTime.MIDNIGHT);
        }
        if(userRequest.getEndTimeSaturday()==null){
            userRequest.setEndTimeSaturday(LocalTime.MIDNIGHT);
        }
        if(userRequest.getStartTimeSunday()==null){
            userRequest.setStartTimeSunday(LocalTime.MIDNIGHT);
        }
        if(userRequest.getEndTimeSunday()==null){
            userRequest.setEndTimeSunday(LocalTime.MIDNIGHT);
        }
        if(getByPharmacist(pharmacist)== null){
            System.out.println("---------------------PRAVI NOVI--------------------------------");

            WorkingHoursPharmacist workingHoursPharmacist = new WorkingHoursPharmacist();
            workingHoursPharmacist.setPharmacist(pharmacist);

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


        }else{
            return updateWorkingHours(userRequest);
        }
    }
    private WorkingHoursPharmacist updateWorkingHours(WorkingScheduleDTO userRequest){
        Pharmacist pharmacist = pharmacistService.findById(userRequest.getPharmacistId());
        WorkingHoursPharmacist workingHoursPharmacist = getByPharmacist(pharmacist);
        System.out.println("---------------------USAO U UPDATE--------------------------------");
        mondayScheduleRepository.delete(workingHoursPharmacist.getMondaySchedule());
        MondaySchedule mondaySchedule = new MondaySchedule();
        mondaySchedule.setStartTime(userRequest.getStartTimeMonday());
        mondaySchedule.setEndTime(userRequest.getEndTimeMonday());
        mondayScheduleRepository.save(mondaySchedule);
        workingHoursPharmacist.setMondaySchedule(mondaySchedule);

        tuesdayScheduleRepository.delete(workingHoursPharmacist.getTuesdaySchedule());
        ThursdaySchedule thursdaySchedule = new ThursdaySchedule();
        thursdaySchedule.setStartTime(userRequest.getStartTimeThursday());
        thursdaySchedule.setEndTime(userRequest.getEndTimeThursday());
        thursdayScheduleRepository.save(thursdaySchedule);
        workingHoursPharmacist.setThursdaySchedule(thursdaySchedule);

        wednesdayScheduleRepository.delete(workingHoursPharmacist.getWednesdaySchedule());
        WednesdaySchedule wednesdaySchedule = new WednesdaySchedule();
        wednesdaySchedule.setStartTime(userRequest.getStartTimeWednesday());
        wednesdaySchedule.setEndTime(userRequest.getEndTimeWednesday());
        wednesdayScheduleRepository.save(wednesdaySchedule);
        workingHoursPharmacist.setWednesdaySchedule(wednesdaySchedule);

        tuesdayScheduleRepository.delete(workingHoursPharmacist.getTuesdaySchedule());
        TuesdaySchedule tuesdaySchedule = new TuesdaySchedule();
        tuesdaySchedule.setStartTime(userRequest.getStartTimeTuesday());
        tuesdaySchedule.setEndTime(userRequest.getEndTimeTuesday());
        tuesdayScheduleRepository.save(tuesdaySchedule);
        workingHoursPharmacist.setTuesdaySchedule(tuesdaySchedule);

        fridayScheduleRepository.delete(workingHoursPharmacist.getFridaySchedule());
        FridaySchedule fridaySchedule = new FridaySchedule();
        fridaySchedule.setStartTime(userRequest.getStartTimeFriday());
        fridaySchedule.setEndTime(userRequest.getEndTimeFriday());
        fridayScheduleRepository.save(fridaySchedule);
        workingHoursPharmacist.setFridaySchedule(fridaySchedule);

        sundayScheduleRepository.delete(workingHoursPharmacist.getSundaySchedule());
        SundaySchedule sundaySchedule = new SundaySchedule();
        sundaySchedule.setStartTime(userRequest.getStartTimeSunday());
        sundaySchedule.setEndTime(userRequest.getEndTimeSunday());
        sundayScheduleRepository.save(sundaySchedule);
        workingHoursPharmacist.setSundaySchedule(sundaySchedule);

        saturdayScheduleRepository.delete(workingHoursPharmacist.getSaturdaySchedule());
        SaturdaySchedule saturdaySchedule = new SaturdaySchedule();
        saturdaySchedule.setStartTime(userRequest.getStartTimeSaturday());
        saturdaySchedule.setEndTime(userRequest.getEndTimeSaturday());
        saturdayScheduleRepository.save(saturdaySchedule);
        workingHoursPharmacist.setSaturdaySchedule(saturdaySchedule);

        return this.workingHoursPharmacistRepository.save(workingHoursPharmacist);


    }
    private WorkingHoursPharmacist getByPharmacist(Pharmacist pharmacist){
        for(WorkingHoursPharmacist workingHoursPharmacist : workingHoursPharmacistRepository.findAll()){
            if(workingHoursPharmacist.getPharmacist().getId() == pharmacist.getId()){
                return workingHoursPharmacist;
            }
        }
        return null;
    }


}
