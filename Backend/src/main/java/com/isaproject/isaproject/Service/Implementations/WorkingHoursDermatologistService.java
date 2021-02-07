package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.WorkingHoursDermatologistDTO;
import com.isaproject.isaproject.DTO.WorkingHoursPharmacistDTO;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Schedule.*;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.Pharmacist;
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
    @Autowired
    PharmacyService pharmacyService;
    @Autowired
    DermatologistService dermatologistService;


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
        Dermatologist dermatologist = dermatologistService.findById(userRequest.getDermatologistId());
        Pharmacy pharmacy = pharmacyService.findById(userRequest.getPharmacyId());

        if(getByDermatologist(dermatologist,pharmacy) == null){
            System.out.println("---------------------PRAVI NOVI--------------------------------");
            WorkingHoursDermatologist workingHoursDermatologist = new WorkingHoursDermatologist();
            workingHoursDermatologist.setDermatologist(dermatologist);
            workingHoursDermatologist.setPharmacy(pharmacyService.findById(userRequest.getPharmacyId()));

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

        }else{
            return updateWorkingHours(userRequest);
        }




    }

    private WorkingHoursDermatologist updateWorkingHours(WorkingHoursDermatologistDTO userRequest){
        System.out.println("---------------------USAO U UPDATE--------------------------------");

        Dermatologist dermatologist = dermatologistService.findById(userRequest.getDermatologistId());
        Pharmacy pharmacy = pharmacyService.findById(userRequest.getPharmacyId());
        WorkingHoursDermatologist workingHoursDermatologist = getByDermatologist(dermatologist,pharmacy);

        mondayScheduleRepository.delete(workingHoursDermatologist.getMondaySchedule());
        MondaySchedule mondaySchedule = new MondaySchedule();
        mondaySchedule.setStartTime(userRequest.getStartTimeMonday());
        mondaySchedule.setEndTime(userRequest.getEndTimeMonday());
        mondayScheduleRepository.save(mondaySchedule);
        workingHoursDermatologist.setMondaySchedule(mondaySchedule);

        tuesdayScheduleRepository.delete(workingHoursDermatologist.getTuesdaySchedule());
        ThursdaySchedule thursdaySchedule = new ThursdaySchedule();
        thursdaySchedule.setStartTime(userRequest.getStartTimeThursday());
        thursdaySchedule.setEndTime(userRequest.getEndTimeThursday());
        thursdayScheduleRepository.save(thursdaySchedule);
        workingHoursDermatologist.setThursdaySchedule(thursdaySchedule);

        wednesdayScheduleRepository.delete(workingHoursDermatologist.getWednesdaySchedule());
        WednesdaySchedule wednesdaySchedule = new WednesdaySchedule();
        wednesdaySchedule.setStartTime(userRequest.getStartTimeWednesday());
        wednesdaySchedule.setEndTime(userRequest.getEndTimeWednesday());
        wednesdayScheduleRepository.save(wednesdaySchedule);
        workingHoursDermatologist.setWednesdaySchedule(wednesdaySchedule);

        tuesdayScheduleRepository.delete(workingHoursDermatologist.getTuesdaySchedule());
        TuesdaySchedule tuesdaySchedule = new TuesdaySchedule();
        tuesdaySchedule.setStartTime(userRequest.getStartTimeTuesday());
        tuesdaySchedule.setEndTime(userRequest.getEndTimeTuesday());
        tuesdayScheduleRepository.save(tuesdaySchedule);
        workingHoursDermatologist.setTuesdaySchedule(tuesdaySchedule);

        fridayScheduleRepository.delete(workingHoursDermatologist.getFridaySchedule());
        FridaySchedule fridaySchedule = new FridaySchedule();
        fridaySchedule.setStartTime(userRequest.getStartTimeFriday());
        fridaySchedule.setEndTime(userRequest.getEndTimeFriday());
        fridayScheduleRepository.save(fridaySchedule);
        workingHoursDermatologist.setFridaySchedule(fridaySchedule);

        sundayScheduleRepository.delete(workingHoursDermatologist.getSundaySchedule());
        SundaySchedule sundaySchedule = new SundaySchedule();
        sundaySchedule.setStartTime(userRequest.getStartTimeSunday());
        sundaySchedule.setEndTime(userRequest.getEndTimeSunday());
        sundayScheduleRepository.save(sundaySchedule);
        workingHoursDermatologist.setSundaySchedule(sundaySchedule);

        saturdayScheduleRepository.delete(workingHoursDermatologist.getSaturdaySchedule());
        SaturdaySchedule saturdaySchedule = new SaturdaySchedule();
        saturdaySchedule.setStartTime(userRequest.getStartTimeSaturday());
        saturdaySchedule.setEndTime(userRequest.getEndTimeSaturday());
        saturdayScheduleRepository.save(saturdaySchedule);
        workingHoursDermatologist.setSaturdaySchedule(saturdaySchedule);

        return this.workingHoursDermatologistRepository.save(workingHoursDermatologist);

    }

    @Override
    public void delete(WorkingHoursDermatologist workingHoursDermatologist) {

    }

    private WorkingHoursDermatologist getByDermatologist(Dermatologist dermatologist, Pharmacy pharmacy){
        for(WorkingHoursDermatologist workingHoursDermatologist : workingHoursDermatologistRepository.findAll()){
            if(workingHoursDermatologist.getDermatologist().getId() == dermatologist.getId() && workingHoursDermatologist.getPharmacy().getId() == pharmacy.getId()){
                return workingHoursDermatologist;
            }
        }
        return null;
    }

    public Boolean isAvailabilable(WorkingHoursDermatologistDTO dto){
        for(WorkingHoursDermatologist workingHoursDermatologist : workingHoursDermatologistRepository.findAll()){
            if(workingHoursDermatologist.getDermatologist().getId() == dto.getDermatologistId()){
                System.out.println("MONDAAAAAAAAAAAAY");
                System.out.println(dto.getStartTimeMonday());
                if(dto.getStartTimeMonday().equals("")){
                    return true;
                }
                if((dto.getStartTimeMonday().isAfter(workingHoursDermatologist.getMondaySchedule().getStartTime()) && dto.getEndTimeMonday().isBefore(workingHoursDermatologist.getMondaySchedule().getEndTime())
                ||(dto.getEndTimeMonday().isAfter(workingHoursDermatologist.getMondaySchedule().getStartTime()) && dto.getEndTimeMonday().isBefore(workingHoursDermatologist.getMondaySchedule().getEndTime())))){
                    return false;
                }
            }
            if(workingHoursDermatologist.getDermatologist().getId() == dto.getDermatologistId()){
                if(dto.getStartTimeTuesday().equals("")){
                    return true;
                }
                if((dto.getStartTimeTuesday().isAfter(workingHoursDermatologist.getTuesdaySchedule().getStartTime()) && dto.getEndTimeTuesday().isBefore(workingHoursDermatologist.getTuesdaySchedule().getEndTime())
                        ||(dto.getEndTimeTuesday().isAfter(workingHoursDermatologist.getTuesdaySchedule().getStartTime()) && dto.getEndTimeTuesday().isBefore(workingHoursDermatologist.getTuesdaySchedule().getEndTime())))){
                    return false;
                }
            }
            if(workingHoursDermatologist.getDermatologist().getId() == dto.getDermatologistId()){
                if(dto.getStartTimeWednesday().equals("")){
                    return true;
                }
                if((dto.getStartTimeWednesday().isAfter(workingHoursDermatologist.getWednesdaySchedule().getStartTime()) && dto.getEndTimeWednesday().isBefore(workingHoursDermatologist.getWednesdaySchedule().getEndTime())
                        ||(dto.getEndTimeWednesday().isAfter(workingHoursDermatologist.getWednesdaySchedule().getStartTime()) && dto.getEndTimeWednesday().isBefore(workingHoursDermatologist.getWednesdaySchedule().getEndTime())))){
                    return false;
                }
            }
            if(workingHoursDermatologist.getDermatologist().getId() == dto.getDermatologistId()){
                if(dto.getStartTimeThursday().equals("")){
                    return true;
                }
                if((dto.getStartTimeThursday().isAfter(workingHoursDermatologist.getThursdaySchedule().getStartTime()) && dto.getEndTimeThursday().isBefore(workingHoursDermatologist.getThursdaySchedule().getEndTime())
                        ||(dto.getEndTimeThursday().isAfter(workingHoursDermatologist.getThursdaySchedule().getStartTime()) && dto.getEndTimeThursday().isBefore(workingHoursDermatologist.getThursdaySchedule().getEndTime())))){
                    return false;
                }
            } if(workingHoursDermatologist.getDermatologist().getId() == dto.getDermatologistId()){
                if(dto.getStartTimeFriday().equals("")){
                    return true;
                }
                if((dto.getStartTimeFriday().isAfter(workingHoursDermatologist.getFridaySchedule().getStartTime()) && dto.getEndTimeFriday().isBefore(workingHoursDermatologist.getFridaySchedule().getEndTime())
                        ||(dto.getEndTimeFriday().isAfter(workingHoursDermatologist.getFridaySchedule().getStartTime()) && dto.getEndTimeFriday().isBefore(workingHoursDermatologist.getFridaySchedule().getEndTime())))){
                    return false;
                }
            } if(workingHoursDermatologist.getDermatologist().getId() == dto.getDermatologistId()){
                if(dto.getStartTimeSaturday().equals("")){
                    return true;
                }
                if((dto.getStartTimeSaturday().isAfter(workingHoursDermatologist.getSaturdaySchedule().getStartTime()) && dto.getEndTimeSaturday().isBefore(workingHoursDermatologist.getSaturdaySchedule().getEndTime())
                        ||(dto.getEndTimeSaturday().isAfter(workingHoursDermatologist.getSaturdaySchedule().getStartTime()) && dto.getEndTimeMonday().isBefore(workingHoursDermatologist.getSaturdaySchedule().getEndTime())))){
                    return false;
                }
            } if(workingHoursDermatologist.getDermatologist().getId() == dto.getDermatologistId()){
                if(dto.getStartTimeSunday().equals("")){
                    return true;
                }
                if((dto.getStartTimeSunday().isAfter(workingHoursDermatologist.getSundaySchedule().getStartTime()) && dto.getEndTimeSunday().isBefore(workingHoursDermatologist.getSundaySchedule().getEndTime())
                        ||(dto.getEndTimeSunday().isAfter(workingHoursDermatologist.getSundaySchedule().getStartTime()) && dto.getEndTimeSunday().isBefore(workingHoursDermatologist.getSundaySchedule().getEndTime())))){
                    return false;
                }
            }

        }
        return true;
    }
}
