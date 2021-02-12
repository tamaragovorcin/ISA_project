package com.isaproject.isaproject.Controller;
import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Examinations.Examination;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Schedule.*;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.Pharmacist;
import com.isaproject.isaproject.Repository.*;
import com.isaproject.isaproject.Service.Implementations.*;
import com.isaproject.isaproject.DTO.ConsultingDTO;
import com.isaproject.isaproject.DTO.ConsultingForBackDTO;
import com.isaproject.isaproject.Repository.ConsultingRepository;
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
import java.util.*;
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
    public ResponseEntity<String> addConsulting(@RequestBody NewConsultingDTO newConsultingDTO) {
        Boolean free = true;
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser) currentUser.getPrincipal();
        Patient patient = patientService.findById(newConsultingDTO.getPatientId());
        Pharmacist logPharmacist = pharmacistService.findById(user.getId());


            ConsultingDTO consultingDTO = new ConsultingDTO();
            consultingDTO.setPatient(patient);
            consultingDTO.setPharmacist(logPharmacist);
            consultingDTO.setDate(newConsultingDTO.getDate());
            consultingDTO.setStartTime(newConsultingDTO.getStartTime());
            consultingDTO.setDuration(10);
            consultingDTO.setShowedUp(false);
            consultingDTO.setPrice(logPharmacist.getPharmacy().getConsultingPrice());

        Consulting consulting = consultingService.save(consultingDTO);
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(consultingDTO.getPatient().getEmail());
        mail.setSubject("Successfuly reserved pharmacist consultation!");
        mail.setFrom(environment.getProperty("spring.mail.username"));
        mail.setText("You have successfully reserved an appointment on : "
                + consulting.getDate() + " at " + consulting.getStartTime() + ". Your doctor is " + consulting.getPharmacist().getName() + " " + consulting.getPharmacist().getSurname());



            mailSender.send(mail);


        if(consulting == null) {
            return new ResponseEntity<>("Termin is not available at required time! Try in another time.", HttpStatus.CREATED);

        }else{
            return new ResponseEntity<>("Term is successfully reserved.", HttpStatus.CREATED);

        }
        //}else{
           // return   new ResponseEntity<>("This period is not aveilable!", HttpStatus.CREATED);
       // }
    }
    @PostMapping("/scheduleFromPharmacyProfile")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<String> scheduleConsultingFromPharmacyProfile(@RequestBody ScheduleConsultingDTO consultingDTO) {
        if(consultingDTO.getDate() ==  null || consultingDTO.getStartTime() == null){
           return  new ResponseEntity<>("You have to define date and time!", HttpStatus.CREATED);

        }
        if(consultingDTO.getDate().isBefore(LocalDate.now())){
          return   new ResponseEntity<>("You can not schedule consulting in past!", HttpStatus.CREATED);
        }
        Patient patient = patientService.findById(consultingDTO.getPatient());
        Pharmacist pharmacist = pharmacistService.findById(consultingDTO.getPharmacist());
        Patient patient1 = patientService.findById(consultingDTO.getPatient());
        ConsultingDTO consultingDTO1 = new ConsultingDTO();
        consultingDTO1.setPharmacist(pharmacist);
        consultingDTO1.setPatient(patient);
        consultingDTO1.setDate(consultingDTO.getDate());
        consultingDTO1.setStartTime(consultingDTO.getStartTime());
        consultingDTO1.setDuration(15);
        consultingDTO1.setInformation("");
        consultingDTO1.setCancelled(false);
        consultingDTO1.setShowedUp(false);
        Consulting consulting = consultingService.save(consultingDTO1);
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(patient.getEmail());
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
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<String>  cancel(@PathVariable Integer id) {
        Consulting consulting = consultingService.findById(id);
        LocalDate date = LocalDate.now().plusDays(2);

        Boolean able = true;
        LocalTime time = consulting.getStartTime();

        if(consulting.getDate().isBefore(date) && LocalTime.now().isBefore(time)) {
            able = false;
        }else{
            consultingService.delete(consulting);
        }

        return able == true ?
                new ResponseEntity<>("You have successfully cancelled an appointment!", HttpStatus.CREATED) :
                new ResponseEntity<>("You are not able to cancel the appointment because it is in the next 24 hours!", HttpStatus.CREATED);


    }

    @GetMapping("/getAll/{id}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<ConsultingsFrontDTO>> getAllConsultings(@PathVariable Integer id) {

        if(id == null){
            throw new IllegalArgumentException("We are unable to find your consultations.");
        }


        List<Consulting> programs = consultingService.findAll();
        List<ConsultingsFrontDTO> consultingsFrontDTOS = new ArrayList<ConsultingsFrontDTO>();
        if(programs.size() == 0){
            throw new IllegalArgumentException("You do not have any consultations reserved yet.");
        }
        for(Consulting consulting: programs){
            if(consulting.getPatient().getId()==id) {
                ConsultingsFrontDTO consultingsFrontDTO = new ConsultingsFrontDTO();
                consultingsFrontDTO.setId(consulting.getId());
                consultingsFrontDTO.setDate(consulting.getDate());
                consultingsFrontDTO.setDuration(consulting.getDuration());
                consultingsFrontDTO.setName(consulting.getPharmacist().getName());
                consultingsFrontDTO.setSurname(consulting.getPharmacist().getSurname());
                consultingsFrontDTO.setTime(consulting.getStartTime());

                LocalDate d = LocalDate.now();
                if (consulting.getDate().isBefore(d)) {
                    consultingsFrontDTO.setFinished(true);
                } else {
                    consultingsFrontDTO.setFinished(false);
                }

                consultingsFrontDTOS.add(consultingsFrontDTO);
            }


        }
        return consultingsFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(consultingsFrontDTOS);
    }
    @GetMapping("/sortpricelowest")
    //@PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<List<ConsultingsFrontDTO>> sortpricelowest() {

        List<Consulting> programs = consultingService.findAll();
        List<ConsultingsFrontDTO> consultingsFrontDTOS = new ArrayList<ConsultingsFrontDTO>();

        Collections.sort(programs, new Comparator<Consulting>() {
            @Override
            public int compare(Consulting c1, Consulting c2) {
                return Double.compare(c1.getPrice(), c2.getPrice());

            }
        });

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
        return ResponseEntity.ok(consultingsFrontDTOS);
    }


    @GetMapping("/sortpricehighest")
    //@PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<List<ConsultingsFrontDTO>> sortpricehighest() {

        List<Consulting> programs = consultingService.findAll();
        List<ConsultingsFrontDTO> consultingsFrontDTOS = new ArrayList<ConsultingsFrontDTO>();

        Collections.sort(programs, new Comparator<Consulting>() {
            @Override
            public int compare(Consulting c1, Consulting c2) {
                return Double.compare(c1.getPrice(), c2.getPrice());

            }
        });
        Collections.reverse(programs);
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
        return ResponseEntity.ok(consultingsFrontDTOS);
    }


    @GetMapping("/namefromatoz")
    //@PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<List<ConsultingsFrontDTO>> namefromatoz() {

        List<Consulting> programs = consultingService.findAll();
        List<ConsultingsFrontDTO> consultingsFrontDTOS = new ArrayList<ConsultingsFrontDTO>();

        Collections.sort(programs, new Comparator<Consulting>() {
            @Override
            public int compare(Consulting c1, Consulting c2) {
                return Double.compare(c1.getDuration(), c2.getDuration());

            }
        });
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
        return ResponseEntity.ok(consultingsFrontDTOS);
    }


    @GetMapping("/namefromztoa")
    //@PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<List<ConsultingsFrontDTO>> namefromztoa() {

        List<Consulting> programs = consultingService.findAll();
        List<ConsultingsFrontDTO> consultingsFrontDTOS = new ArrayList<ConsultingsFrontDTO>();

        Collections.sort(programs, new Comparator<Consulting>() {
            @Override
            public int compare(Consulting c1, Consulting c2) {
                return Double.compare(c1.getDuration(), c2.getDuration());

            }
        });
        Collections.reverse(programs);
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
        return ResponseEntity.ok(consultingsFrontDTOS);
    }

    @GetMapping("/dateClosest")
    //@PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<List<ConsultingsFrontDTO>> dateClosest() {

        List<Consulting> programs = consultingService.findAll();
        List<ConsultingsFrontDTO> consultingsFrontDTOS = new ArrayList<ConsultingsFrontDTO>();

        Collections.sort(programs, new Comparator<Consulting>() {
            public int compare(Consulting o1, Consulting o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

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
        return  ResponseEntity.ok(consultingsFrontDTOS);
    }

    @GetMapping("/dateFurthest")
    //@PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<ConsultingsFrontDTO>> dateFurthest() {

        List<Consulting> programs = consultingService.findAll();
        List<ConsultingsFrontDTO> consultingsFrontDTOS = new ArrayList<ConsultingsFrontDTO>();

        Collections.sort(programs, new Comparator<Consulting>() {
            public int compare(Consulting o1, Consulting o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

        Collections.reverse(programs);
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
        return ResponseEntity.ok(consultingsFrontDTOS);
    }

    @GetMapping("/durationshort")
    //@PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<ConsultingsFrontDTO>> durationshort() {

        List<Consulting> programs = consultingService.findAll();
        List<ConsultingsFrontDTO> consultingsFrontDTOS = new ArrayList<ConsultingsFrontDTO>();

        Collections.sort(programs, new Comparator<Consulting>() {
            @Override
            public int compare(Consulting c1, Consulting c2) {
                return Double.compare(c1.getDuration(), c2.getDuration());

            }
        });

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
        return ResponseEntity.ok(consultingsFrontDTOS);
    }

    @GetMapping("/durationlong")
    //@PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<ConsultingsFrontDTO>> durationlong() {

        List<Consulting> programs = consultingService.findAll();
        List<ConsultingsFrontDTO> consultingsFrontDTOS = new ArrayList<ConsultingsFrontDTO>();

        Collections.sort(programs, new Comparator<Consulting>() {
            @Override
            public int compare(Consulting c1, Consulting c2) {
                return Double.compare(c1.getDuration(), c2.getDuration());

            }
        });

        Collections.reverse(programs);
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
        return ResponseEntity.ok(consultingsFrontDTOS);
    }
    @PostMapping("/marklowest")
    //@PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<List<PharmacyFrontDTO>> marklowest(@RequestBody PharmacistConsultationTimeDTO dto) {


        if(dto.getDate() == null || dto.getTime()==null){
            throw new IllegalArgumentException("Unable to sort right now, please try again later.");
        }

        LocalDate date = dto.getDate();
        LocalTime time = dto.getTime();
        time = time.plusSeconds(0);

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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);

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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);

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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
        Collections.sort(pharmacies, new Comparator<Pharmacy>() {
            @Override
            public int compare(Pharmacy c1, Pharmacy c2) {
                return Double.compare(c1.getMark(), c2.getMark());

            }
        });

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
            pf.setPrice(ph.getConsultingPrice());

            pharmacyFrontDTOS.add(pf);

        }
        return pharmacyFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyFrontDTOS);
    }

    @PostMapping("/markhighest")
    //@PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<List<PharmacyFrontDTO>> markhighest(@RequestBody PharmacistConsultationTimeDTO dto) {
        if(dto.getDate() == null || dto.getTime()==null){
            throw new IllegalArgumentException("Unable to sort right now, please try again later.");
        }
        LocalDate date = dto.getDate();
        LocalTime time = dto.getTime();
        time= time.plusSeconds(0);

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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);

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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);

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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
        Collections.sort(pharmacies, new Comparator<Pharmacy>() {
            @Override
            public int compare(Pharmacy c1, Pharmacy c2) {
                return Double.compare(c1.getMark(), c2.getMark());

            }
        });
    Collections.reverse(pharmacies);
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
            pf.setPrice(ph.getConsultingPrice());

            pharmacyFrontDTOS.add(pf);

        }
        return pharmacyFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyFrontDTOS);
    }

    @PostMapping("/pricelowest")
    //@PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<List<PharmacyFrontDTO>> pricelowest(@RequestBody PharmacistConsultationTimeDTO dto) {
        if(dto.getDate() == null || dto.getTime()==null){
            throw new IllegalArgumentException("Unable to sort right now, please try again later.");
        }
        LocalDate date = dto.getDate();
        LocalTime time = dto.getTime();
        time = time.plusSeconds(0);

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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);

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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);

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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
        Collections.sort(pharmacies, new Comparator<Pharmacy>() {
            @Override
            public int compare(Pharmacy c1, Pharmacy c2) {
                return Double.compare(c1.getConsultingPrice(), c2.getConsultingPrice());

            }
        });

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
            pf.setPrice(ph.getConsultingPrice());

            pharmacyFrontDTOS.add(pf);

        }
        return pharmacyFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyFrontDTOS);
    }

    @PostMapping("/pricehighest")
    //@PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<List<PharmacyFrontDTO>> pricehighest(@RequestBody PharmacistConsultationTimeDTO dto) {
        if(dto.getDate() == null || dto.getTime()==null){
            throw new IllegalArgumentException("Unable to sort right now, please try again later.");
        }
        LocalDate date = dto.getDate();
        LocalTime time = dto.getTime();
        time = time.plusSeconds(0);

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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);

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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);

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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
        Collections.sort(pharmacies, new Comparator<Pharmacy>() {
            @Override
            public int compare(Pharmacy c1, Pharmacy c2) {
                return Double.compare(c1.getConsultingPrice(), c2.getConsultingPrice());

            }
        });
        Collections.reverse(pharmacies);
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
            pf.setPrice(ph.getConsultingPrice());

            pharmacyFrontDTOS.add(pf);

        }
        return pharmacyFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyFrontDTOS);
    }

    @PostMapping("/marklowestpharmacist")
    //@PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<List<PharmacistDTO>> marklowestpharmacist(@RequestBody PharmacistsConsultationDTO dto) {

        if(dto.getDate() == null || dto.getTime()==null){
            throw new IllegalArgumentException("Unable to sort right now, please try again later.");
        }
        LocalDate date = dto.getDate();
        LocalTime time = dto.getTime();
        time = time.plusSeconds(0);

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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);

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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);

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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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

            Collections.sort(pharmacistsMAIN, new Comparator<Pharmacist>() {
                @Override
                public int compare(Pharmacist c1, Pharmacist c2) {
                    return Double.compare(c1.getMarkPharmacist(), c2.getMarkPharmacist());

                }
            });

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


    @PostMapping("/markhighestpharmacist")
    //@PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<List<PharmacistDTO>> markhighestpharmacist(@RequestBody PharmacistsConsultationDTO dto) {

        if(dto.getDate() == null || dto.getTime()==null){
            throw new IllegalArgumentException("Unable to sort right now, please try again later.");
        }
        LocalDate date = dto.getDate();
        LocalTime time = dto.getTime();
        time = time.plusSeconds(0);

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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);

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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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

            Collections.sort(pharmacistsMAIN, new Comparator<Pharmacist>() {
                @Override
                public int compare(Pharmacist c1, Pharmacist c2) {
                    return Double.compare(c1.getMarkPharmacist(), c2.getMarkPharmacist());

                }
            });
            Collections.reverse(pharmacistsMAIN);
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




    @PostMapping("/getPharmacies")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<PharmacyFrontDTO>> getPharmacies(@RequestBody PharmacistConsultationTimeDTO dto) {

        if(dto.getDate() == null || dto.getTime()==null){
            throw new IllegalArgumentException("Please insert both date and time.");
        }

        LocalDate date = dto.getDate();
        LocalTime time = dto.getTime();
        time=time.plusSeconds(0);

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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);

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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);

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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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

                throw new IllegalArgumentException("There are no available consutations in this period.");

        }

        if(pharmacies.size() == 0){
            throw new IllegalArgumentException("There are no available consutations in this period.");
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
            pf.setPrice(ph.getConsultingPrice());

            pharmacyFrontDTOS.add(pf);

        }
        return pharmacyFrontDTOS == null ?
                new ResponseEntity<>( HttpStatus.valueOf("There are no available terms on this date and time.")) :
                ResponseEntity.ok(pharmacyFrontDTOS);
    }


    @PostMapping("/getPharmacists")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<PharmacistDTO>> getPharmacists(@RequestBody PharmacistsConsultationDTO dto) {

        if( dto.getTime()==null  || dto.getDate()==null || dto.getPharmacyId()==null){
            throw new IllegalArgumentException("Sorry, we are missing some information, please try again.");
        }

        LocalDate date = dto.getDate();
        LocalTime time = dto.getTime();
        time= time.plusSeconds(0);

        List<PharmacistDTO> pharmacistDTOS = new ArrayList<PharmacistDTO>();
        List<Pharmacist> pharmacistsMAIN = new ArrayList<Pharmacist>();
        List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
        Pharmacy pharmacy1 = pharmacyService.findById(dto.getPharmacyId());




        List<Pharmacist> pharmacists = pharmacistService.findAll();
        List<WorkingHoursPharmacist> workingHoursPharmacists = workingHoursPharmacistService.findAll();
        LocalTime start = null;
        LocalTime end = null;
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);

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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
                        start = start.plusSeconds(0);
                        end = end.plusSeconds(0);
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
            throw new IllegalArgumentException("There are no pharmacists available.");
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
                System.out.println(start);
                pf.setLocalTime(start);


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
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<String> reserveConsultation(@RequestBody PharmacistsConsultationDTO dto) {

        if(dto.getPatient() == null || dto.getTime()==null || dto.getPharmacist()==null || dto.getDate()==null || dto.getPharmacyId()==null){
            throw new IllegalArgumentException("Sorry, we are missing some information, please try again.");
        }

        List<Consulting> consultings = consultingService.findAll();

        Boolean able = true;

        for(Consulting consulting: consultings){
            Double s = consulting.getDuration();
            String st = s.toString();
            if(consulting.getPharmacist().getId() == dto.getPharmacist().getId() && consulting.getDate().equals(dto.getDate()) && dto.getTime().equals(consulting.getStartTime()) ){

               able=false;
                throw new IllegalArgumentException("Sorry, this term is already reserved.");

            }
        }

        Patient patient = patientService.findById(dto.getPatient());


        if(patient.getPenalties() > 3){
            able = false;
            throw new IllegalArgumentException("You are not able to reserve a medication because you have 3 or more penalties.");
        }
        if(able) {
            ConsultingDTO consulting = new ConsultingDTO();
            Pharmacy pharmacy = pharmacyService.findById(dto.getPharmacyId());


            consulting.setCancelled(false);
            consulting.setCancelled(false);
            consulting.setDate(dto.getDate());
            consulting.setStartTime(dto.getTime());
            consulting.setPharmacist(dto.getPharmacist());
            Patient patient1 = patientService.findById(dto.getPatient());
            consulting.setPatient(patient1);
            consulting.setDuration(20.0);
            consulting.setPrice(pharmacy.getConsultingPrice());

            Consulting consulting1 = consultingService.save(consulting);


                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setTo(patient1.getEmail());
                mail.setSubject("Successfuly reserved consultation with pharmacist!");
                mail.setFrom(environment.getProperty("spring.mail.username"));
                //mail.setFrom("pharmacyisa@gmail.com");
                mail.setText("You have successfully reserved an appointment on : "
                        + consulting1.getDate() + " at " + consulting1.getStartTime() + "\n" +
                        ". Your doctor is " + consulting1.getPharmacist().getName() + " " + consulting1.getPharmacist().getSurname() + ".\n" +
                        "Pharmacy where the consultation will be held is " + consulting1.getPharmacist().getName() + "."
                );

                mailSender.send(mail);

        }
        return able == true ?
                new ResponseEntity<>("Consulting is successfully reserved! You will soon receive a confirmation email", HttpStatus.CREATED) :
                new ResponseEntity<>("You are unable to reserve a consultation right now, please try again later!", HttpStatus.CREATED);

}

    @PostMapping("/reserveConsultationPharmacyProfile")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<String> reserveConsultation(@RequestBody PharmacistConsFrontDTO dto) {

        if(dto.getPatient() == null || dto.getTime()==null || dto.getPharmacist()==null || dto.getDate()==null){
            throw new IllegalArgumentException("Sorry, we are missing some information, please try again.");
        }

        List<Consulting> consultings = consultingService.findAll();

        Boolean able = true;

        for(Consulting consulting: consultings){
            Double s = consulting.getDuration();
            String st = s.toString();
            if(consulting.getPharmacist().getId() == dto.getPharmacist() && consulting.getDate().equals(dto.getDate()) && dto.getTime().equals(consulting.getStartTime()) ){

                able=false;
                throw new IllegalArgumentException("Sorry, this term is already reserved.");

            }
        }

        Patient patient = patientService.findById(dto.getPatient());


        if(patient.getPenalties() > 3){
            able = false;
            throw new IllegalArgumentException("You are not able to reserve a medication because you have 3 or more penalties.");
        }
        if(able) {
            ConsultingDTO consulting = new ConsultingDTO();
            Pharmacist pharmacist = pharmacistService.findById(dto.getPharmacist());
            Pharmacy pharmacy = pharmacyService.findById(pharmacist.getPharmacy().getId());
            consulting.setCancelled(false);
            consulting.setCancelled(false);
            consulting.setDate(dto.getDate());
            consulting.setStartTime(dto.getTime());
            consulting.setPharmacist(pharmacist);
            Patient patient1 = patientService.findById(dto.getPatient());
            consulting.setPatient(patient1);
            consulting.setDuration(20.0);
            consulting.setPrice(pharmacy.getConsultingPrice());

            Consulting consulting1 = consultingService.save(consulting);


            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(patient1.getEmail());
            mail.setSubject("Successfuly reserved consultation with pharmacist!");
            mail.setFrom(environment.getProperty("spring.mail.username"));
            //mail.setFrom("pharmacyisa@gmail.com");
            mail.setText("You have successfully reserved an appointment on : "
                    + consulting1.getDate() + " at " + consulting1.getStartTime() + "\n" +
                    ". Your doctor is " + consulting1.getPharmacist().getName() + " " + consulting1.getPharmacist().getSurname() + ".\n" +
                    "Pharmacy where the consultation will be held is " + consulting1.getPharmacist().getName() + "."
            );

            mailSender.send(mail);

        }
        return able == true ?
                new ResponseEntity<>("Consulting is successfully reserved! You will soon receive a confirmation email", HttpStatus.CREATED) :
                new ResponseEntity<>("You are unable to reserve a consultation right now, please try again later!", HttpStatus.CREATED);

    }



}
