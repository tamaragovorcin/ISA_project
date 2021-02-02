package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Schedule.*;
import com.isaproject.isaproject.Model.Users.Pharmacist;
import com.isaproject.isaproject.Repository.*;
import com.isaproject.isaproject.Service.Implementations.ConsultingService;
import com.isaproject.isaproject.Service.Implementations.PharmacistService;
import com.isaproject.isaproject.Service.Implementations.PharmacyService;
import com.isaproject.isaproject.Service.Implementations.WorkingHoursPharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/consulting")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ConsultingController {
    @Autowired
    ConsultingService consultingService;


    @Autowired
    PharmacyService pharmacyService;

    @Autowired
    PharmacistService pharmacistService;

    @Autowired
    WorkingHoursPharmacistService workingHoursPharmacistService;

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




    @PostMapping("/add")
   // @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<String> addConsulting(@RequestBody ConsultingDTO consultingDTO) {

        System.out.println("-+++++++++++++++++++++++++++++++++++++++");
        Consulting consulting = consultingService.save(consultingDTO);
        return consulting == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Consulting is successfully added!", HttpStatus.CREATED);
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<String> update(@RequestBody Consulting consulting) {

        Consulting consulting1 = consultingService.update(consulting);
        return consulting1 == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Consulting is successfully updated!", HttpStatus.CREATED);
    }

    @GetMapping("")
    //@PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<Consulting> getAll() {

        List<Consulting> programs = consultingService.findAll();
        return programs == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(programs.get(0));
    }

    @PostMapping("/getPharmacies")
    public ResponseEntity<List<PharmacyFrontDTO>> getPharmacies(@RequestBody PharmacistConsultationTimeDTO dto) {

        LocalDate date = dto.getDate();
        LocalTime time = dto.getTime();
        time.plusSeconds(0);

        List<PharmacyFrontDTO> pharmacyFrontDTOS = new ArrayList<PharmacyFrontDTO>();
        List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
        List<Pharmacist> pharmacists = pharmacistService.findAll();
        List<WorkingHoursPharmacist> workingHoursPharmacists = workingHoursPharmacistService.findAll();
        LocalTime start;
        LocalTime end;
        Boolean found = false;

        List<MondaySchedule> mondaySchedules = mondayScheduleRepository.findAll();
        List<TuesdaySchedule> tuesdaySchedules = tuesdayScheduleRepository.findAll();
        List<WednesdaySchedule> wednesdaySchedules = wednesdayScheduleRepository.findAll();
        List<ThursdaySchedule> thursdaySchedules = thursdayScheduleRepository.findAll();
        List<FridaySchedule> fridaySchedules = fridayScheduleRepository.findAll();
        List<SaturdaySchedule> saturdaySchedules = saturdayScheduleRepository.findAll();
        List<SundaySchedule> sundaySchedules = sundayScheduleRepository.findAll();


        DayOfWeek day = date.getDayOfWeek();
            if(day==DayOfWeek.MONDAY) {
                for (WorkingHoursPharmacist workingHoursPharmacist: workingHoursPharmacists){
                    for(MondaySchedule mondaySchedule: mondaySchedules){
                        if(mondaySchedule.getId()==workingHoursPharmacist.getMondaySchedule().getId()){

                            start = mondaySchedule.getStartTime();
                            end = mondaySchedule.getEndTime();
                            start.plusSeconds(0);
                            end.plusSeconds(0);

                            if(start.isBefore(time) && end.isAfter(time)){
                                found = true;
                                pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                            }

                        }
                    }
                }
            }
        else if(day==DayOfWeek.TUESDAY) {

                for (WorkingHoursPharmacist workingHoursPharmacist: workingHoursPharmacists){
                    for(TuesdaySchedule mondaySchedule: tuesdaySchedules){
                        if(mondaySchedule.getId()==workingHoursPharmacist.getTuesdaySchedule().getId()){

                            start = mondaySchedule.getStartTime();
                            end = mondaySchedule.getEndTime();
                            start.plusSeconds(0);
                            end.plusSeconds(0);
                            if(start.isBefore(time) && end.isAfter(time)){
                                found = true;
                                pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                            }

                        }
                    }
                }

        } else if(day==DayOfWeek.WEDNESDAY) {
                for (WorkingHoursPharmacist workingHoursPharmacist: workingHoursPharmacists){
                    for(WednesdaySchedule mondaySchedule: wednesdaySchedules){
                        if(mondaySchedule.getId()==workingHoursPharmacist.getWednesdaySchedule().getId()){

                            start = mondaySchedule.getStartTime();
                            end = mondaySchedule.getEndTime();
                            start.plusSeconds(0);
                            end.plusSeconds(0);

                            if(start.isBefore(time) && end.isAfter(time)){
                                found = true;
                                pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                            }

                        }
                    }
                }

            } else if(day==DayOfWeek.THURSDAY) {
                for (WorkingHoursPharmacist workingHoursPharmacist: workingHoursPharmacists){
                    for(ThursdaySchedule mondaySchedule: thursdaySchedules){
                        if(mondaySchedule.getId()==workingHoursPharmacist.getThursdaySchedule().getId()){

                            start = mondaySchedule.getStartTime();
                            end = mondaySchedule.getEndTime();
                            start.plusSeconds(0);
                            end.plusSeconds(0);
                            if(start.isBefore(time) && end.isAfter(time)){
                                found = true;
                                pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                            }

                        }
                    }
                }

            } else if(day==DayOfWeek.FRIDAY) {
                for (WorkingHoursPharmacist workingHoursPharmacist: workingHoursPharmacists){
                    for(FridaySchedule mondaySchedule: fridaySchedules){
                        if(mondaySchedule.getId()==workingHoursPharmacist.getFridaySchedule().getId()){

                            start = mondaySchedule.getStartTime();
                            end = mondaySchedule.getEndTime();
                            start.plusSeconds(0);
                            end.plusSeconds(0);
                            if(start.isBefore(time) && end.isAfter(time)){
                                found = true;
                                pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                            }

                        }
                    }
                }

            } else if(day==DayOfWeek.SATURDAY) {
                for (WorkingHoursPharmacist workingHoursPharmacist: workingHoursPharmacists){
                    for(SaturdaySchedule mondaySchedule: saturdaySchedules){
                        if(mondaySchedule.getId()==workingHoursPharmacist.getSaturdaySchedule().getId()){

                            start = mondaySchedule.getStartTime();
                            end = mondaySchedule.getEndTime();
                            start.plusSeconds(0);
                            end.plusSeconds(0);
                            if(start.isBefore(time) && end.isAfter(time)){
                                found = true;
                                pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                            }

                        }
                    }
                }

            } else if(day==DayOfWeek.SUNDAY) {
                for (WorkingHoursPharmacist workingHoursPharmacist: workingHoursPharmacists){
                    for(SundaySchedule mondaySchedule: sundaySchedules){
                        if(mondaySchedule.getId()==workingHoursPharmacist.getSundaySchedule().getId()){

                            start = mondaySchedule.getStartTime();
                            end = mondaySchedule.getEndTime();
                            start.plusSeconds(0);
                            end.plusSeconds(0);
                            if(start.isBefore(time) && end.isAfter(time)){
                                found = true;
                                pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                            }

                        }
                    }
                }

            }


        if(found == false){
            return null;
        }

        for (Pharmacy ph : pharmacies){

            PharmacyFrontDTO pf = new PharmacyFrontDTO();
            pf.setId(ph.getId());
            pf.setCountry(ph.getAddress().getCountry());
            pf.setNumber(ph.getAddress().getNumber());
            pf.setPostalCode(ph.getAddress().getPostalCode());
            pf.setStreet(ph.getAddress().getStreet());
            pf.setPharmacyName(ph.getPharmacyName());
            pf.setMark(ph.getMark());
            pf.setCity(ph.getAddress().getTown());

            pharmacyFrontDTOS.add(pf);

        }
        return pharmacyFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyFrontDTOS);
    }




    @PostMapping("/getPharmacists")
    public ResponseEntity<List<PharmacistDTO>> getPharmacists(@RequestBody PharmacistsConsultationDTO dto) {

        LocalDate date = dto.getDate();
        LocalTime time = dto.getTime();
        time.plusSeconds(0);

        List<PharmacistDTO> pharmacistDTOS = new ArrayList<PharmacistDTO>();
        List<Pharmacist> pharmacistsMAIN = new ArrayList<Pharmacist>();
        List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
        Pharmacy pharmacy = new Pharmacy();

        for(Pharmacy p : pharmacies){
            if(dto.getPharmacyId() == p.getId()){
                pharmacy = p;
            }
        }


        List<Pharmacist> pharmacists = pharmacistService.findAll();
        List<WorkingHoursPharmacist> workingHoursPharmacists = workingHoursPharmacistService.findAll();
        LocalTime start;
        LocalTime end;
        Boolean found = false;

        List<MondaySchedule> mondaySchedules = mondayScheduleRepository.findAll();
        List<TuesdaySchedule> tuesdaySchedules = tuesdayScheduleRepository.findAll();
        List<WednesdaySchedule> wednesdaySchedules = wednesdayScheduleRepository.findAll();
        List<ThursdaySchedule> thursdaySchedules = thursdayScheduleRepository.findAll();
        List<FridaySchedule> fridaySchedules = fridayScheduleRepository.findAll();
        List<SaturdaySchedule> saturdaySchedules = saturdayScheduleRepository.findAll();
        List<SundaySchedule> sundaySchedules = sundayScheduleRepository.findAll();


        DayOfWeek day = date.getDayOfWeek();

        if(day==DayOfWeek.MONDAY) {
            for (WorkingHoursPharmacist workingHoursPharmacist: workingHoursPharmacists){
                for(MondaySchedule mondaySchedule: mondaySchedules){
                    if(mondaySchedule.getId()==workingHoursPharmacist.getMondaySchedule().getId()){

                        start = mondaySchedule.getStartTime();
                        end = mondaySchedule.getEndTime();
                        start.plusSeconds(0);
                        end.plusSeconds(0);

                        if(start.isBefore(time) && end.isAfter(time)){
                            found = true;
                            pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                        }

                    }
                }
            }
        }
        else if(day==DayOfWeek.TUESDAY) {

            for (WorkingHoursPharmacist workingHoursPharmacist: workingHoursPharmacists){
                for(TuesdaySchedule mondaySchedule: tuesdaySchedules){
                    if(mondaySchedule.getId()==workingHoursPharmacist.getTuesdaySchedule().getId()){

                        start = mondaySchedule.getStartTime();
                        end = mondaySchedule.getEndTime();
                        start.plusSeconds(0);
                        end.plusSeconds(0);
                        if(start.isBefore(time) && end.isAfter(time)){
                            found = true;
                            pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                        }

                    }
                }
            }

        } else if(day==DayOfWeek.WEDNESDAY) {
            for (WorkingHoursPharmacist workingHoursPharmacist: workingHoursPharmacists){
                for(WednesdaySchedule mondaySchedule: wednesdaySchedules){
                    if(mondaySchedule.getId()==workingHoursPharmacist.getWednesdaySchedule().getId()){

                        start = mondaySchedule.getStartTime();
                        end = mondaySchedule.getEndTime();
                        start.plusSeconds(0);
                        end.plusSeconds(0);

                        if(start.isBefore(time) && end.isAfter(time)){
                            found = true;
                            pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                        }

                    }
                }
            }

        } else if(day==DayOfWeek.THURSDAY) {
            for (WorkingHoursPharmacist workingHoursPharmacist: workingHoursPharmacists){
                for(ThursdaySchedule mondaySchedule: thursdaySchedules){
                    if(mondaySchedule.getId()==workingHoursPharmacist.getThursdaySchedule().getId()){

                        start = mondaySchedule.getStartTime();
                        end = mondaySchedule.getEndTime();
                        start.plusSeconds(0);
                        end.plusSeconds(0);
                        if(start.isBefore(time) && end.isAfter(time)){
                            found = true;
                            pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                        }

                    }
                }
            }

        } else if(day==DayOfWeek.FRIDAY) {
            for (WorkingHoursPharmacist workingHoursPharmacist: workingHoursPharmacists){
                for(FridaySchedule mondaySchedule: fridaySchedules){
                    if(mondaySchedule.getId()==workingHoursPharmacist.getFridaySchedule().getId()){

                        start = mondaySchedule.getStartTime();
                        end = mondaySchedule.getEndTime();
                        start.plusSeconds(0);
                        end.plusSeconds(0);
                        if(start.isBefore(time) && end.isAfter(time)){
                            found = true;
                            pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                        }

                    }
                }
            }

        } else if(day==DayOfWeek.SATURDAY) {
            for (WorkingHoursPharmacist workingHoursPharmacist: workingHoursPharmacists){
                for(SaturdaySchedule mondaySchedule: saturdaySchedules){
                    if(mondaySchedule.getId()==workingHoursPharmacist.getSaturdaySchedule().getId()){

                        start = mondaySchedule.getStartTime();
                        end = mondaySchedule.getEndTime();
                        start.plusSeconds(0);
                        end.plusSeconds(0);
                        if(start.isBefore(time) && end.isAfter(time)){
                            found = true;
                            pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                        }

                    }
                }
            }

        } else if(day==DayOfWeek.SUNDAY) {
            for (WorkingHoursPharmacist workingHoursPharmacist: workingHoursPharmacists){
                for(SundaySchedule mondaySchedule: sundaySchedules){
                    if(mondaySchedule.getId()==workingHoursPharmacist.getSundaySchedule().getId()){

                        start = mondaySchedule.getStartTime();
                        end = mondaySchedule.getEndTime();
                        start.plusSeconds(0);
                        end.plusSeconds(0);
                        if(start.isBefore(time) && end.isAfter(time)){
                            found = true;
                            pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                        }

                    }
                }
            }

        }


        if(found == false){
            return null;
        }


        Boolean available = false;

        for(Pharmacy pharmacy1 : pharmacies){
           if(pharmacy.getId() == pharmacy1.getId()){
               available = true;
           }
        }

        if(available){

            for(Pharmacist pharmacist: pharmacists){

                if(pharmacist.getPharmacy().getId() == pharmacy.getId()){

                    pharmacistsMAIN.add(pharmacist);
                }

            }
        }
        else{
            return null;
        }


        for (Pharmacist ph : pharmacistsMAIN){

            PharmacistDTO pf = new PharmacistDTO();
            pf.setId(ph.getId());
            pf.setFirstname(ph.getName());
            pf.setSurname(ph.getSurname());
            pf.setMark(ph.getMarkPharmacist());

            pharmacistDTOS.add(pf);

        }
        return pharmacistDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacistDTOS);
    }



}
