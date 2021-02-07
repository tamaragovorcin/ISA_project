package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.ExaminationScheduleDTO;
import com.isaproject.isaproject.Model.Examinations.Examination;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Schedule.HolidayScheduleDermatologist;
import com.isaproject.isaproject.Model.Schedule.WorkingHoursDermatologist;
import com.isaproject.isaproject.Model.Users.Authority;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.Supplier;
import com.isaproject.isaproject.Repository.DermatologistRepository;
import com.isaproject.isaproject.Repository.ExaminationScheduleRepository;
import com.isaproject.isaproject.Repository.PharmacistRepository;
import com.isaproject.isaproject.Repository.PharmacyRepository;
import com.isaproject.isaproject.Service.IServices.IExaminationScheduleService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExaminationScheduleService implements IExaminationScheduleService {
    @Autowired
    ExaminationScheduleRepository examinationScheduleRepository;
    @Autowired
    PharmacyRepository pharmacyRepository;
    @Autowired
    DermatologistRepository dermatologistRepository;
    @Autowired
    WorkingHoursDermatologistService workingHoursDermatologistService;
    @Autowired
    HolidayScheduleDermatologistService holidayScheduleDermatologistService;


    @Override
    public ExaminationSchedule findById(Integer id) {
        return examinationScheduleRepository.findById(id).get();
    }

    @Override
    public List<ExaminationSchedule> findAll() {
        return examinationScheduleRepository.findAll();
    }

    @Override
    public ExaminationSchedule save(ExaminationScheduleDTO examinationScheduleDTO) {
        Dermatologist dermatologist = dermatologistRepository.findById(examinationScheduleDTO.getDermatologist()).get();
        Pharmacy pharmacy = pharmacyRepository.findById(examinationScheduleDTO.getPharmacy()).get();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(examinationScheduleDTO.getDate().getDayOfWeek());
        for(WorkingHoursDermatologist workingHoursDermatologist : workingHoursDermatologistService.findAll()){
            if(workingHoursDermatologist.getDermatologist().getId() == examinationScheduleDTO.getDermatologist() && workingHoursDermatologist.getPharmacy().getId() == examinationScheduleDTO.getPharmacy()){
                if(examinationScheduleDTO.getDate().getDayOfWeek() == DayOfWeek.MONDAY){
                    LocalTime shiftStarts = workingHoursDermatologist.getMondaySchedule().getStartTime();
                    LocalTime shiftEnds = workingHoursDermatologist.getMondaySchedule().getEndTime();
                    return checkSchedule(examinationScheduleDTO,shiftStarts,shiftEnds);

                }else if(examinationScheduleDTO.getDate().getDayOfWeek() == DayOfWeek.TUESDAY){
                    LocalTime shiftStarts = workingHoursDermatologist.getTuesdaySchedule().getStartTime();
                    LocalTime shiftEnds = workingHoursDermatologist.getTuesdaySchedule().getEndTime();
                    return checkSchedule(examinationScheduleDTO,shiftStarts,shiftEnds);

                }else if(examinationScheduleDTO.getDate().getDayOfWeek() == DayOfWeek.WEDNESDAY){
                    LocalTime shiftStarts = workingHoursDermatologist.getWednesdaySchedule().getStartTime();
                    LocalTime shiftEnds = workingHoursDermatologist.getWednesdaySchedule().getEndTime();
                    return checkSchedule(examinationScheduleDTO,shiftStarts,shiftEnds);

                }else if(examinationScheduleDTO.getDate().getDayOfWeek() == DayOfWeek.THURSDAY){
                    LocalTime shiftStarts = workingHoursDermatologist.getThursdaySchedule().getStartTime();
                    LocalTime shiftEnds = workingHoursDermatologist.getThursdaySchedule().getEndTime();
                    return checkSchedule(examinationScheduleDTO,shiftStarts,shiftEnds);


                }else if(examinationScheduleDTO.getDate().getDayOfWeek() == DayOfWeek.FRIDAY){
                    LocalTime shiftStarts = workingHoursDermatologist.getTuesdaySchedule().getStartTime();
                    LocalTime shiftEnds = workingHoursDermatologist.getTuesdaySchedule().getEndTime();
                    return checkSchedule(examinationScheduleDTO,shiftStarts,shiftEnds);

                }else if(examinationScheduleDTO.getDate().getDayOfWeek() == DayOfWeek.SATURDAY){
                    LocalTime shiftStarts = workingHoursDermatologist.getSaturdaySchedule().getStartTime();
                    LocalTime shiftEnds = workingHoursDermatologist.getSaturdaySchedule().getEndTime();
                    return checkSchedule(examinationScheduleDTO,shiftStarts,shiftEnds);

                }else if(examinationScheduleDTO.getDate().getDayOfWeek() == DayOfWeek.SUNDAY){
                    System.out.println("USAO U SUNDAY");

                    LocalTime shiftStarts = workingHoursDermatologist.getSundaySchedule().getStartTime();
                    LocalTime shiftEnds = workingHoursDermatologist.getSundaySchedule().getEndTime();
                    return checkSchedule(examinationScheduleDTO,shiftStarts,shiftEnds);
                }
            }
        }

      /*  ExaminationSchedule examinationSchedule = new ExaminationSchedule();
        examinationSchedule.setPharmacy(pharmacy);
        examinationSchedule.setDate(examinationScheduleDTO.getDate());
        examinationSchedule.setDermatologist(dermatologist);
        examinationSchedule.setDuration(examinationScheduleDTO.getDuration());
        examinationSchedule.setPrice(examinationSchedule.getPrice());
        examinationSchedule.setStartTime(examinationScheduleDTO.getStartTime());
        examinationSchedule.setFinished(false);*/
        return  null;
    }

    private ExaminationSchedule createNewExaminationTerm(ExaminationScheduleDTO examinationScheduleDTO){
        System.out.println("USAO U CREATE");

        ExaminationSchedule examinationSchedule = new ExaminationSchedule();
        examinationSchedule.setPharmacy(pharmacyRepository.getOne(examinationScheduleDTO.getPharmacy()));
        examinationSchedule.setDermatologist(dermatologistRepository.getOne(examinationScheduleDTO.getDermatologist()));
        examinationSchedule.setDate(examinationScheduleDTO.getDate());
        examinationSchedule.setDuration(examinationScheduleDTO.getDuration());
        examinationSchedule.setPrice(examinationScheduleDTO.getPrice());
        examinationSchedule.setStartTime(examinationScheduleDTO.getStartTime());
        examinationSchedule.setFinished(false);


        return this.examinationScheduleRepository.save(examinationSchedule);
    }
    private ExaminationSchedule checkSchedule(ExaminationScheduleDTO examinationScheduleDTO,LocalTime shiftStarts,LocalTime shiftEnds){
        if(examinationScheduleDTO.getStartTime().isAfter(shiftStarts) && examinationScheduleDTO.getStartTime().isBefore(shiftEnds)
                &&  examinationScheduleDTO.getStartTime().plusMinutes(examinationScheduleDTO.getDuration()).isBefore(shiftEnds))
        {
            System.out.println("USAO U CHECK SCHEDYLE");

            if(isDermatologistAvailable(examinationScheduleDTO) && !isDermatologistOnHoliday(examinationScheduleDTO)){
                System.out.println("USAO U 2. IF");

                return createNewExaminationTerm(examinationScheduleDTO);
            }

        }
        return null;
    }
    private  Boolean isDermatologistAvailable( ExaminationScheduleDTO dto){
        for(ExaminationSchedule examinationSchedule : examinationScheduleRepository.findAll()){
            if(examinationSchedule.getDermatologist().getId() == dto.getDermatologist()) {
                if (dto.getDate().equals(examinationSchedule.getDate()) && dto.getStartTime().isAfter(examinationSchedule.getStartTime()) && dto.getStartTime().isBefore(examinationSchedule.getStartTime().plusMinutes(examinationSchedule.getDuration()))) {
                    return false;
                }
            }
        }
        return true;
    }
    private Boolean isDermatologistOnHoliday(ExaminationScheduleDTO dto){
        for(HolidayScheduleDermatologist holiday : holidayScheduleDermatologistService.findAll()){
            if(holiday.getDermatologist().getId() == dto.getDermatologist() &&
                    dto.getDate().isAfter(holiday.getStartDate()) && dto.getDate().isBefore(holiday.getEndDate())){
                return true;
            }
        }
        return false;
    }
    @Override
    public ExaminationSchedule update(ExaminationSchedule examinationSchedule, Boolean tf) {


        ExaminationSchedule examinationSchedule1= findById(examinationSchedule.getId());
        examinationSchedule1.setFinished(tf);

        return this.examinationScheduleRepository.save(examinationSchedule1);
    }

    @Override
    public void delete(ExaminationSchedule medication) {
        examinationScheduleRepository.delete(medication);
    }
}
