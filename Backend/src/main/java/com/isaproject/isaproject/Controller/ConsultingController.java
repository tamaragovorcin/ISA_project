package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Schedule.*;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.Pharmacist;
import com.isaproject.isaproject.Repository.*;
import com.isaproject.isaproject.Service.Implementations.*;
import com.isaproject.isaproject.DTO.ConsultingDTO;
import com.isaproject.isaproject.DTO.ConsultingForBackDTO;
import com.isaproject.isaproject.DTO.ConsultingNoteDTO;
import com.isaproject.isaproject.DTO.OfferReviewDTO;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Orders.Offer;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.Pharmacist;
import com.isaproject.isaproject.Repository.ConsultingRepository;
import com.isaproject.isaproject.Repository.PatientRepository;
import com.isaproject.isaproject.Service.Implementations.ConsultingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Autowired
    PatientService patientService;

    @Autowired
    JavaMailSenderImpl mailSender;


    @Autowired
    Environment environment;
    @Autowired
    ConsultingRepository consultingRepository;

    @PostMapping("/add")
    // @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<String> addConsulting(@RequestBody ConsultingDTO consultingDTO) {


        Consulting consulting = consultingService.save(consultingDTO);
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(consultingDTO.getPatient().getEmail());
        mail.setSubject("Successfuly reserved pharmacist consultation!");
        mail.setFrom(environment.getProperty("spring.mail.username"));
        mail.setText("You have successfully reserved an appointment on : "
                + consulting.getDate() + " at " + consulting.getStartTime() + ". Your doctor is " + consulting.getPharmacist().getName() + " " + consulting.getPharmacist().getSurname());

        mailSender.send(mail);
        return consulting == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>("Consulting is successfully added!", HttpStatus.CREATED);
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<String> update(@RequestBody ConsultingForBackDTO consulting) {

        Consulting consulting1 = consultingService.findById(consulting.getConsultingId());
        consulting1.setDuration(consulting.getDuration());
        consulting1.setInformation(consulting.getInformation());
        consulting1.setShowedUp(true);
        this.consultingRepository.save(consulting1);
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

    @GetMapping("/cancel/{id}")
    //@PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<String>  cancel(@PathVariable Integer id) {
        Consulting consulting = consultingService.findById(id);
        LocalDate date = LocalDate.now().plusDays(1);

        Boolean able = false;
        LocalTime time = consulting.getStartTime();

        if(consulting.getDate().isBefore(date) && LocalTime.now().isBefore(time)) {
            able = true;
            consultingService.delete(consulting);
        }

        return able == true ?
                new ResponseEntity<>("You have successfully cancelled an appointment!", HttpStatus.CREATED) :
                new ResponseEntity<>("You are not able to cancel the appointment because it is in the next 24 hours!", HttpStatus.CREATED);


    }

    @GetMapping("/getAll")
    //@PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<List<ConsultingsFrontDTO>> getAllConsultings() {

        List<Consulting> programs = consultingService.findAll();
        List<ConsultingsFrontDTO> consultingsFrontDTOS = new ArrayList<ConsultingsFrontDTO>();

        for(Consulting consulting: programs){

            ConsultingsFrontDTO consultingsFrontDTO = new ConsultingsFrontDTO();
            consultingsFrontDTO.setId(consulting.getId());
            consultingsFrontDTO.setDate(consulting.getDate());
            consultingsFrontDTO.setDuration(consulting.getDuration());
            consultingsFrontDTO.setName(consulting.getPharmacist().getName());
            consultingsFrontDTO.setSurname(consulting.getPharmacist().getSurname());
            consultingsFrontDTO.setTime(consulting.getStartTime());

            LocalDate d = LocalDate.now();
            if(consulting.getDate().isBefore(d)){
                consultingsFrontDTO.setFinished(true);
            }
            else{
                consultingsFrontDTO.setFinished(false);
            }

            consultingsFrontDTOS.add(consultingsFrontDTO);



        }
        return consultingsFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(consultingsFrontDTOS);
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
        if (day == DayOfWeek.MONDAY) {
            for (WorkingHoursPharmacist workingHoursPharmacist : workingHoursPharmacists) {
                for (MondaySchedule mondaySchedule : mondaySchedules) {
                    if (mondaySchedule.getId() == workingHoursPharmacist.getMondaySchedule().getId()) {

                        start = mondaySchedule.getStartTime();
                        end = mondaySchedule.getEndTime();
                        start.plusSeconds(0);
                        end.plusSeconds(0);

                        if (start.isBefore(time) && end.isAfter(time)) {
                            found = true;
                            if (pharmacies.size() == 0) {
                                pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                            } else {
                                for (Pharmacy pharmacy : pharmacies) {
                                    if (pharmacy.getId() != workingHoursPharmacist.getPharmacist().getPharmacy().getId()) {
                                        pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                                    }
                                }
                            }
                        }

                    }
                }
            }
        } else if (day == DayOfWeek.TUESDAY) {

            for (WorkingHoursPharmacist workingHoursPharmacist : workingHoursPharmacists) {
                for (TuesdaySchedule mondaySchedule : tuesdaySchedules) {
                    if (mondaySchedule.getId() == workingHoursPharmacist.getTuesdaySchedule().getId()) {

                        start = mondaySchedule.getStartTime();
                        end = mondaySchedule.getEndTime();
                        start.plusSeconds(0);
                        end.plusSeconds(0);
                        if (start.isBefore(time) && end.isAfter(time)) {
                            found = true;
                            if (pharmacies.size() == 0) {
                                pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                            } else {
                                for (Pharmacy pharmacy : pharmacies) {
                                    if (pharmacy.getId() != workingHoursPharmacist.getPharmacist().getPharmacy().getId()) {
                                        pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } else if (day == DayOfWeek.WEDNESDAY) {
            for (WorkingHoursPharmacist workingHoursPharmacist : workingHoursPharmacists) {
                for (WednesdaySchedule mondaySchedule : wednesdaySchedules) {
                    if (mondaySchedule.getId() == workingHoursPharmacist.getWednesdaySchedule().getId()) {

                        start = mondaySchedule.getStartTime();
                        end = mondaySchedule.getEndTime();
                        start.plusSeconds(0);
                        end.plusSeconds(0);

                        if (start.isBefore(time) && end.isAfter(time)) {
                            found = true;
                            if (pharmacies.size() == 0) {
                                pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                            } else {
                                for (Pharmacy pharmacy : pharmacies) {
                                    if (pharmacy.getId() != workingHoursPharmacist.getPharmacist().getPharmacy().getId()) {
                                        pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } else if (day == DayOfWeek.THURSDAY) {
            for (WorkingHoursPharmacist workingHoursPharmacist : workingHoursPharmacists) {
                for (ThursdaySchedule mondaySchedule : thursdaySchedules) {
                    if (mondaySchedule.getId() == workingHoursPharmacist.getThursdaySchedule().getId()) {

                        start = mondaySchedule.getStartTime();
                        end = mondaySchedule.getEndTime();
                        start.plusSeconds(0);
                        end.plusSeconds(0);
                        if (start.isBefore(time) && end.isAfter(time)) {
                            found = true;
                            if (pharmacies.size() == 0) {
                                pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                            } else {
                                for (Pharmacy pharmacy : pharmacies) {
                                    if (pharmacy.getId() != workingHoursPharmacist.getPharmacist().getPharmacy().getId()) {
                                        pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } else if (day == DayOfWeek.FRIDAY) {
            for (WorkingHoursPharmacist workingHoursPharmacist : workingHoursPharmacists) {
                for (FridaySchedule mondaySchedule : fridaySchedules) {
                    if (mondaySchedule.getId() == workingHoursPharmacist.getFridaySchedule().getId()) {

                        start = mondaySchedule.getStartTime();
                        end = mondaySchedule.getEndTime();
                        start.plusSeconds(0);
                        end.plusSeconds(0);
                        if (start.isBefore(time) && end.isAfter(time)) {
                            found = true;
                            if (pharmacies.size() == 0) {
                                pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                            } else {
                                for (Pharmacy pharmacy : pharmacies) {
                                    if (pharmacy.getId() != workingHoursPharmacist.getPharmacist().getPharmacy().getId()) {
                                        pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } else if (day == DayOfWeek.SATURDAY) {
            for (WorkingHoursPharmacist workingHoursPharmacist : workingHoursPharmacists) {
                for (SaturdaySchedule mondaySchedule : saturdaySchedules) {
                    if (mondaySchedule.getId() == workingHoursPharmacist.getSaturdaySchedule().getId()) {

                        start = mondaySchedule.getStartTime();
                        end = mondaySchedule.getEndTime();
                        start.plusSeconds(0);
                        end.plusSeconds(0);
                        if (start.isBefore(time) && end.isAfter(time)) {
                            found = true;
                            if (pharmacies.size() == 0) {
                                pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                            } else {
                                for (Pharmacy pharmacy : pharmacies) {
                                    if (pharmacy.getId() != workingHoursPharmacist.getPharmacist().getPharmacy().getId()) {
                                        pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } else if (day == DayOfWeek.SUNDAY) {
            for (WorkingHoursPharmacist workingHoursPharmacist : workingHoursPharmacists) {
                for (SundaySchedule mondaySchedule : sundaySchedules) {
                    if (mondaySchedule.getId() == workingHoursPharmacist.getSundaySchedule().getId()) {

                        start = mondaySchedule.getStartTime();
                        end = mondaySchedule.getEndTime();
                        start.plusSeconds(0);
                        end.plusSeconds(0);
                        if (start.isBefore(time) && end.isAfter(time)) {
                            found = true;
                            if (pharmacies.size() == 0) {
                                pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                            } else {
                                for (Pharmacy pharmacy : pharmacies) {
                                    if (pharmacy.getId() != workingHoursPharmacist.getPharmacist().getPharmacy().getId()) {
                                        pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }


        if (found == false) {
            return null;
        }


        for (Pharmacy ph : pharmacies) {

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
        Pharmacy pharmacy1 = pharmacyService.findById(dto.getPharmacyId());




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

        if (day == DayOfWeek.MONDAY) {
            for (WorkingHoursPharmacist workingHoursPharmacist : workingHoursPharmacists) {
                for (MondaySchedule mondaySchedule : mondaySchedules) {
                    if (mondaySchedule.getId() == workingHoursPharmacist.getMondaySchedule().getId()) {

                        start = mondaySchedule.getStartTime();
                        end = mondaySchedule.getEndTime();
                        start.plusSeconds(0);
                        end.plusSeconds(0);

                        if (start.isBefore(time) && end.isAfter(time)) {
                            found = true;
                            if (pharmacies.size() == 0) {
                                pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                            } else {
                                for (Pharmacy pharmacy : pharmacies) {
                                    if (pharmacy.getId() != workingHoursPharmacist.getPharmacist().getPharmacy().getId()) {
                                        pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                                    }
                                }
                            }
                        }

                    }
                }
            }
        } else if (day == DayOfWeek.TUESDAY) {

            for (WorkingHoursPharmacist workingHoursPharmacist : workingHoursPharmacists) {
                for (TuesdaySchedule mondaySchedule : tuesdaySchedules) {
                    if (mondaySchedule.getId() == workingHoursPharmacist.getTuesdaySchedule().getId()) {

                        start = mondaySchedule.getStartTime();
                        end = mondaySchedule.getEndTime();
                        start.plusSeconds(0);
                        end.plusSeconds(0);
                        if (start.isBefore(time) && end.isAfter(time)) {
                            found = true;
                            if (pharmacies.size() == 0) {
                                pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                            } else {
                                for (Pharmacy pharmacy : pharmacies) {
                                    if (pharmacy.getId() != workingHoursPharmacist.getPharmacist().getPharmacy().getId()) {
                                        pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } else if (day == DayOfWeek.WEDNESDAY) {
            for (WorkingHoursPharmacist workingHoursPharmacist : workingHoursPharmacists) {
                for (WednesdaySchedule mondaySchedule : wednesdaySchedules) {
                    if (mondaySchedule.getId() == workingHoursPharmacist.getWednesdaySchedule().getId()) {

                        start = mondaySchedule.getStartTime();
                        end = mondaySchedule.getEndTime();
                        start.plusSeconds(0);
                        end.plusSeconds(0);

                        if (start.isBefore(time) && end.isAfter(time)) {
                            found = true;
                            if (pharmacies.size() == 0) {
                                pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                            } else {
                                for (Pharmacy pharmacy : pharmacies) {
                                    if (pharmacy.getId() != workingHoursPharmacist.getPharmacist().getPharmacy().getId()) {
                                        pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } else if (day == DayOfWeek.THURSDAY) {
            for (WorkingHoursPharmacist workingHoursPharmacist : workingHoursPharmacists) {
                for (ThursdaySchedule mondaySchedule : thursdaySchedules) {
                    if (mondaySchedule.getId() == workingHoursPharmacist.getThursdaySchedule().getId()) {

                        start = mondaySchedule.getStartTime();
                        end = mondaySchedule.getEndTime();
                        start.plusSeconds(0);
                        end.plusSeconds(0);
                        if (start.isBefore(time) && end.isAfter(time)) {
                            found = true;
                            if (pharmacies.size() == 0) {
                                pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                            } else {
                                for (Pharmacy pharmacy : pharmacies) {
                                    if (pharmacy.getId() != workingHoursPharmacist.getPharmacist().getPharmacy().getId()) {
                                        pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } else if (day == DayOfWeek.FRIDAY) {
            for (WorkingHoursPharmacist workingHoursPharmacist : workingHoursPharmacists) {
                for (FridaySchedule mondaySchedule : fridaySchedules) {
                    if (mondaySchedule.getId() == workingHoursPharmacist.getFridaySchedule().getId()) {

                        start = mondaySchedule.getStartTime();
                        end = mondaySchedule.getEndTime();
                        start.plusSeconds(0);
                        end.plusSeconds(0);
                        if (start.isBefore(time) && end.isAfter(time)) {
                            found = true;
                            if (pharmacies.size() == 0) {
                                pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                            } else {
                                for (Pharmacy pharmacy : pharmacies) {
                                    if (pharmacy.getId() != workingHoursPharmacist.getPharmacist().getPharmacy().getId()) {
                                        pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } else if (day == DayOfWeek.SATURDAY) {
            for (WorkingHoursPharmacist workingHoursPharmacist : workingHoursPharmacists) {
                for (SaturdaySchedule mondaySchedule : saturdaySchedules) {
                    if (mondaySchedule.getId() == workingHoursPharmacist.getSaturdaySchedule().getId()) {

                        start = mondaySchedule.getStartTime();
                        end = mondaySchedule.getEndTime();
                        start.plusSeconds(0);
                        end.plusSeconds(0);
                        if (start.isBefore(time) && end.isAfter(time)) {
                            found = true;
                            if (pharmacies.size() == 0) {
                                pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                            } else {
                                for (Pharmacy pharmacy : pharmacies) {
                                    if (pharmacy.getId() != workingHoursPharmacist.getPharmacist().getPharmacy().getId()) {
                                        pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } else if (day == DayOfWeek.SUNDAY) {
            for (WorkingHoursPharmacist workingHoursPharmacist : workingHoursPharmacists) {
                for (SundaySchedule mondaySchedule : sundaySchedules) {
                    if (mondaySchedule.getId() == workingHoursPharmacist.getSundaySchedule().getId()) {

                        start = mondaySchedule.getStartTime();
                        end = mondaySchedule.getEndTime();
                        start.plusSeconds(0);
                        end.plusSeconds(0);
                        if (start.isBefore(time) && end.isAfter(time)) {
                            found = true;
                            if (pharmacies.size() == 0) {
                                pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                            } else {
                                for (Pharmacy pharmacy : pharmacies) {
                                    if (pharmacy.getId() != workingHoursPharmacist.getPharmacist().getPharmacy().getId()) {
                                        pharmacies.add(workingHoursPharmacist.getPharmacist().getPharmacy());
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }


        if (found == false) {
            return null;
        }


        Boolean available = false;

        for (Pharmacy pha : pharmacies) {
            if (pha.getId() == pharmacy1.getId()) {
                available = true;
            }
        }

        if (available) {

            for (Pharmacist pharmacist : pharmacists) {

                if (pharmacist.getPharmacy().getId() == pharmacy1.getId()) {

                    pharmacistsMAIN.add(pharmacist);
                }

            }


            for (Pharmacist ph : pharmacistsMAIN) {

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

        } else {
            return null;
        }


    }

    @PostMapping("/reserveConsultation")
    public ResponseEntity<String> reserveConsultation(@RequestBody PharmacistsConsultationDTO dto) {

        Patient patient = patientService.findById(dto.getPatient().getId());
        Boolean able = true;
        if(patient.getPenalties() > 3){
            able = false;
        }
        ConsultingDTO consulting = new ConsultingDTO();
        Pharmacy pharmacy = pharmacyService.findById(dto.getPharmacyId());


        consulting.setCancelled(false);
        consulting.setCancelled(false);
        consulting.setDate(dto.getDate());
        consulting.setStartTime(dto.getTime());
        consulting.setPharmacist(dto.getPharmacist());
        consulting.setPatient(dto.getPatient());
        consulting.setDuration(20.0);
        consulting.setPrice(pharmacy.getConsultingPrice());

        Consulting consulting1 = consultingService.save(consulting);

        if(able) {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(dto.getPatient().getEmail());
            mail.setSubject("Successfuly reserved pharmacist consultation!");
            mail.setFrom(environment.getProperty("spring.mail.username"));
            //mail.setFrom("pharmacyisa@gmail.com");
            mail.setText("You have successfully reserved an appointment on : "
                    + consulting1.getDate() + " at " + consulting1.getStartTime() + ". Your doctor is " + consulting1.getPharmacist().getName() + " " + consulting1.getPharmacist().getSurname());

            mailSender.send(mail);
        }
        return able == true ?
                new ResponseEntity<>("Consulting is successfully reserved!", HttpStatus.CREATED) :
                new ResponseEntity<>("You are not able to reserve a medication because you have 3 or more penalties!", HttpStatus.CREATED);
    }



}
