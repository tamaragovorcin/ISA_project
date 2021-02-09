package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.ConsultingDTO;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Schedule.WorkingHoursPharmacist;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.Pharmacist;
import com.isaproject.isaproject.Repository.ConsultingRepository;
import com.isaproject.isaproject.Repository.PatientRepository;
import com.isaproject.isaproject.Repository.PharmacistRepository;
import com.isaproject.isaproject.Repository.PharmacyRepository;
import com.isaproject.isaproject.Service.IServices.IConsultingService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Service
public class ConsultingService implements IConsultingService {

    @Autowired
    ConsultingRepository consultingRepository;
    @Autowired
    PatientRepository patientRepository;


    @Autowired
    PharmacistRepository pharmacistRepository;
    @Autowired
    WorkingHoursPharmacistService workingHoursPharmacistService;
    @Autowired
    HolidayScheduleDermatologistService holidayScheduleDermatologistService;



    @Override
    public Consulting findById(Integer id) {
        return consultingRepository.findById(id).get();
    }

    @Override
    public List<Consulting> findAll() {
        return consultingRepository.findAll();
    }

    @Override
    public Consulting save(ConsultingDTO consultingDTO) {



        Pharmacist pharmacist = pharmacistRepository.findById(consultingDTO.getPharmacist().getId()).get();



        for(WorkingHoursPharmacist workingHoursPharmacist : workingHoursPharmacistService.findAll()){
            if(workingHoursPharmacist.getPharmacist().getId() == consultingDTO.getPharmacist().getId()){
                if(consultingDTO.getDate().getDayOfWeek() == DayOfWeek.MONDAY){
                    LocalTime shiftStarts = workingHoursPharmacist.getMondaySchedule().getStartTime();
                    LocalTime shiftEnds = workingHoursPharmacist.getMondaySchedule().getEndTime();
                    return checkSchedule(consultingDTO,shiftStarts,shiftEnds);

                }else if(consultingDTO.getDate().getDayOfWeek() == DayOfWeek.TUESDAY){
                    LocalTime shiftStarts = workingHoursPharmacist.getTuesdaySchedule().getStartTime();
                    LocalTime shiftEnds = workingHoursPharmacist.getTuesdaySchedule().getEndTime();
                    return checkSchedule(consultingDTO,shiftStarts,shiftEnds);

                }else if(consultingDTO.getDate().getDayOfWeek() == DayOfWeek.WEDNESDAY){
                    LocalTime shiftStarts = workingHoursPharmacist.getWednesdaySchedule().getStartTime();
                    LocalTime shiftEnds = workingHoursPharmacist.getWednesdaySchedule().getEndTime();
                    return checkSchedule(consultingDTO,shiftStarts,shiftEnds);

                }else if(consultingDTO.getDate().getDayOfWeek() == DayOfWeek.THURSDAY){
                    LocalTime shiftStarts = workingHoursPharmacist.getThursdaySchedule().getStartTime();
                    LocalTime shiftEnds = workingHoursPharmacist.getThursdaySchedule().getEndTime();
                    return checkSchedule(consultingDTO,shiftStarts,shiftEnds);


                }else if(consultingDTO.getDate().getDayOfWeek() == DayOfWeek.FRIDAY){
                    LocalTime shiftStarts = workingHoursPharmacist.getTuesdaySchedule().getStartTime();
                    LocalTime shiftEnds = workingHoursPharmacist.getTuesdaySchedule().getEndTime();
                    return checkSchedule(consultingDTO,shiftStarts,shiftEnds);

                }else if(consultingDTO.getDate().getDayOfWeek() == DayOfWeek.SATURDAY){
                    LocalTime shiftStarts = workingHoursPharmacist.getSaturdaySchedule().getStartTime();
                    LocalTime shiftEnds = workingHoursPharmacist.getSaturdaySchedule().getEndTime();
                    return checkSchedule(consultingDTO,shiftStarts,shiftEnds);

                }else if(consultingDTO.getDate().getDayOfWeek() == DayOfWeek.SUNDAY){
                    System.out.println("USAO U SUNDAY");

                    LocalTime shiftStarts = workingHoursPharmacist.getSundaySchedule().getStartTime();
                    LocalTime shiftEnds = workingHoursPharmacist.getSundaySchedule().getEndTime();
                    return checkSchedule(consultingDTO,shiftStarts,shiftEnds);
                }
            }
        }

      /*  Consulting consulting = new Consulting();
        consulting.setPharmacist(consultingDTO.getPharmacist());
        consulting.setPatient(consultingDTO.getPatient());
        consulting.setDate(consultingDTO.getDate());
        consulting.setStartTime(consultingDTO.getStartTime());
        consulting.setDuration(consultingDTO.getDuration());
        consulting.setPrice(consultingDTO.getPrice());
        consulting.setCancelled(false);
        consulting.setShowedUp(false);
        consulting.setInformation("");*/
        return  null;
    }
    private  Boolean isPharmacistAvailable( ConsultingDTO dto){
        for(Consulting consulting : consultingRepository.findAll()){
            if(consulting.getPharmacist().getId() == dto.getPharmacist().getId()) {
                if (dto.getDate().equals(consulting.getDate()) && dto.getStartTime().isAfter(consulting.getStartTime()) && dto.getStartTime().isBefore(consulting.getStartTime().plusMinutes((long) consulting.getDuration()))) {
                    return false;
                }
            }
        }
        return true;
    }
    private  Boolean isPatientAvailable( ConsultingDTO dto){
        for(Consulting consulting : consultingRepository.findAll()){
            if(consulting.getPatient().getId() == dto.getPatient().getId()) {
                if (dto.getDate().equals(consulting.getDate()) && dto.getStartTime().isAfter(consulting.getStartTime()) && dto.getStartTime().isBefore(consulting.getStartTime().plusMinutes((long) consulting.getDuration()))) {
                    return false;
                }
            }
        }
        return true;
    }
    private Consulting createNewExaminationTerm(ConsultingDTO consultingDTO){
        System.out.println("USAO U CREATE");

        Consulting consulting = new Consulting();
        consulting.setPharmacist(consultingDTO.getPharmacist());
        consulting.setPatient(consultingDTO.getPatient());
        consulting.setDate(consultingDTO.getDate());
        consulting.setStartTime(consultingDTO.getStartTime());
        consulting.setDuration(consultingDTO.getDuration());
        consulting.setPrice(consultingDTO.getPrice());
        consulting.setCancelled(false);
        consulting.setShowedUp(false);
        consulting.setInformation("");
        return this.consultingRepository.save(consulting);

    }
    private Consulting checkSchedule(ConsultingDTO consultingDTO, LocalTime shiftStarts, LocalTime shiftEnds){
        if(consultingDTO.getStartTime().isAfter(shiftStarts) && consultingDTO.getStartTime().isBefore(shiftEnds)
                &&  consultingDTO.getStartTime().plusMinutes((long) consultingDTO.getDuration()).isBefore(shiftEnds))
        {
            System.out.println("USAO U CHECK SCHEDYLE");

            if(isPharmacistAvailable(consultingDTO) && isPatientAvailable(consultingDTO)){
                System.out.println("USAO U 2. IF");

                return createNewExaminationTerm(consultingDTO);
            }

        }
        return null;
    }

    @Override
    public Consulting update(Consulting loyaltyProgram) {
        return this.consultingRepository.save(loyaltyProgram);
    }

    @Override
    public void delete(Consulting consulting) {
        consultingRepository.delete(consulting);
    }

    @Override
    public Consulting updateIgnored(Consulting loyaltyProgram) {
       Consulting cons = new Consulting();

       cons.setInformation("Patient was ignored this consulting.");
       cons.setShowedUp(false);
       cons.setPrice(0);
       cons.setDuration(0);
       cons.setStartTime(loyaltyProgram.getStartTime());
       cons.setDate(loyaltyProgram.getDate());
       cons.setDuration(loyaltyProgram.getDuration());
       return  null;

    }



    public Boolean checkIfPatientHasConsulting(Integer pharmacyId) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        Patient patient = patientRepository.findById(user.getId()).get();

        Set<Consulting> consultings = patient.getConsulting();
        for (Consulting consulting : consultings) {
            if(consulting.getShowedUp()==true && consulting.getPharmacist().getPharmacy().getId()==pharmacyId) {
                return true;
            }
        }
        return false;
    }

    public boolean canMakeComplaintPharmacist(Integer pharmacistId) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        Patient patient = patientRepository.findById(user.getId()).get();

        Boolean able = false;

        List<Consulting> consultings = findAll();

        for(Consulting consulting: consultings){
            if(consulting.getPharmacist().getId() == pharmacistId && consulting.getPatient().getId()== patient.getId()){
                if(consulting.getShowedUp()) {
                    able = true;
                }
            }
        }
        return able;
    }
}
