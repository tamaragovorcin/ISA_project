package com.isaproject.isaproject.Controller;

import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.DTO.AlergiesDTO;
import com.isaproject.isaproject.DTO.AlergiesFrontDTO;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.HelpModel.PatientsMedicationAlergy;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Patient;

import com.isaproject.isaproject.Service.Implementations.*;


import com.isaproject.isaproject.Model.Users.*;
import com.isaproject.isaproject.Repository.ConfirmationTokenRepository;
import com.isaproject.isaproject.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.servlet.view.RedirectView;
import java.util.*;

@RestController
@RequestMapping("/api/patient")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PatientController {
    @Autowired
    PatientService patientService;

    @Autowired
    AlergiesService alergiesService;
    @Autowired
    PharmacyService pharmacyService;

    @Autowired
    ConsultingService consultingService;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private Environment environment;

    @Autowired
    JavaMailSenderImpl mailSender;

    @PostMapping("/register")
    public ResponseEntity<String> registerPatient(@RequestBody PersonUserDTO person) {

        Patient existingUser = patientService.findByEmail(person.getEmail());

        if(existingUser != null)
        {
            return ResponseEntity.ok("This email already exists!");
        }
        else
        {
            Patient patient = patientService.save(person);
            ConfirmationToken confirmationToken = new ConfirmationToken(patient);
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(person.getEmail());
            mail.setSubject("Complete Registration!");
            mail.setFrom(environment.getProperty("spring.mail.username"));
            mail.setText("To confirm your account, please click here : "
                    + "http://localhost:8082/api/patient/confirm-account/" + confirmationToken.getConfirmationToken());

            mailSender.send(mail);
            return ResponseEntity.ok("");
        }
    }

    @GetMapping(path = "/confirm-account/{token}")
    public Object confirmUserAccount(@PathVariable String token) {
        ConfirmationToken token2 = confirmationTokenRepository.findByConfirmationToken(token);

        if (token != null) {
            Patient user = patientService.findByEmail(token2.getPersonUser().getEmail());
            user.setEnabled(true);
            patientRepository.save(user);
            RedirectView redirect = new RedirectView();
            redirect.setUrl("http://localhost:8085/patientProfile");
            return redirect;
        } else {
            return "The link is invalid or broken!!";
        }
    }

    @GetMapping("/{id}")
        //@PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<Patient> getById(@PathVariable Integer id) {
        Integer idd = 1;
        Patient patient = patientService.findById(idd);
        return patient == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patient);
    }

    @GetMapping("/account")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<Patient> getMyAccount() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser) currentUser.getPrincipal();
        Patient patient = patientService.findById(user.getId());
        return patient == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patient);
    }


    @GetMapping("/address")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<Address> getMyAddress() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser) currentUser.getPrincipal();

        Patient patient = patientService.findById(user.getId());
        return patient.getAddress() == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patient.getAddress());
    }

    @GetMapping("")
    public ResponseEntity<List<Patient>> getAll() {
        List<Patient> patients = patientService.findAll();
        return patients == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patients);
    }

    @GetMapping("/email/{id}")
    public ResponseEntity<String> getEmail(@PathVariable Integer id) {
        String email = patientService.getEmail(id);
        return email == null || email.equals("") ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(email);
    }

    @GetMapping("penals/{id}")
    //@PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<Patient> getByPatientId(@PathVariable Integer id)
    {
        Patient patient = patientService.findById(id);
        patient.setPenalties(patient.getPenalties()+1);
        this.patientRepository.save(patient);

        return patient == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patient);
    }

    @GetMapping("history")
    //@PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity <HashSet<ConsultingNoteDTO>> getByPatient(@PathVariable Integer id)
    {
        Consulting cons = consultingService.findById(id);
        Integer idP = cons.getPatient().getId();

        HashSet<ConsultingNoteDTO> newC = new HashSet<>();
        for (Consulting c : consultingService.findAll()) {
            if(c.getPatient().getId() == idP)
                newC.add(new ConsultingNoteDTO(c.getId(), c.getPatient().getId(), c.getPatient().getName(), c.getPatient().getSurname(), c.getDate(), c.getStartTime()));

        }

        return newC == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(newC);
    }

    @PostMapping("/update")
    public ResponseEntity<Patient> update(@RequestBody PersonUserDTO person) {
        Patient patient = patientService.update(person);

        return patient == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patient);
    }

    @PostMapping("/addAlergies")
    public ResponseEntity<PatientsMedicationAlergy> addAlergies(@RequestBody AlergiesDTO al) {

        List<PatientsMedicationAlergy> alergy = new ArrayList<PatientsMedicationAlergy>();
        alergy = alergiesService.findAll();
        for(PatientsMedicationAlergy pa : alergy){
            if(pa.getPatient().getId()==al.getPatient().getId()){
                alergiesService.delete(pa);
            }
        }
        PatientsMedicationAlergy patientsMedicationAlergy = alergiesService.save(al);
        return patientsMedicationAlergy == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patientsMedicationAlergy);
    }

    @GetMapping("/getAlergies/{id}")
    public ResponseEntity<List<AlergiesFrontDTO>> getAlergies(@PathVariable Integer id) {
        List<PatientsMedicationAlergy> alergies = alergiesService.findAll();
        Patient patient = patientService.findById(id);
        List<AlergiesFrontDTO> patientsAlergies = new ArrayList<AlergiesFrontDTO>();
        for(PatientsMedicationAlergy patientsMedicationAlergy: alergies){
            if(patientsMedicationAlergy.getPatient().getId()==id){
                AlergiesFrontDTO alergiesFrontDTO = new AlergiesFrontDTO();
                alergiesFrontDTO.setId(patientsMedicationAlergy.getId());
                alergiesFrontDTO.setPatient_id(patientsMedicationAlergy.getPatient().getId());
                alergiesFrontDTO.setName(patientsMedicationAlergy.getMedication().getName());
                patientsAlergies.add(alergiesFrontDTO);
            }
        }
        return patientsAlergies == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patientsAlergies);
    }

    @GetMapping("/deleteAlergies/{id}")
    public void deleteAlergies(@PathVariable Integer id) {
        List<PatientsMedicationAlergy> alergies = alergiesService.findAll();
       for(PatientsMedicationAlergy patientsMedicationAlergy : alergies){
           if(id==patientsMedicationAlergy.getId()){
               alergiesService.delete(patientsMedicationAlergy);
           }
       }
    }

    @GetMapping("/mySubscriptions")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<Integer>> getMySubscriptions()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();

        Patient patient = patientService.findById(user.getId());
        Set<Pharmacy> pharmacies = patient.getSubscribedToPharmacies();
        List<Integer>pharmaciesNames =  new ArrayList<>();
        for (Pharmacy pharmacy: pharmacies)
            pharmaciesNames.add(pharmacy.getId());{
    }
        return pharmaciesNames == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmaciesNames);
    }

    @PostMapping("/subscribeToPharmacy")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<String> subsribe(@RequestBody PharmacyIdDTO pharmacyId)
    {

        Pharmacy pharmacy =pharmacyService.findById(pharmacyId.getPharmacyId());
        return patientService.subsribeToPharmacy(pharmacy) == false ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok("Patient is now subscribed to pharmacy   " + pharmacy.getPharmacyName());
    }

    @PostMapping("/unsubscribeToPharmacy")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<String> unsubsribe(@RequestBody PharmacyIdDTO pharmacyId)
    {
        Pharmacy pharmacy =pharmacyService.findById(pharmacyId.getPharmacyId());

        return patientService.unsubsribeToPharmacy(pharmacy) == false ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok("Patient is now subscribed to pharmacy   " + pharmacy.getPharmacyName());
    }



    @PreAuthorize("hasRole('PHARMACIST')")
    @GetMapping("searchForm/{patientName}")
    public ResponseEntity<List<PatientSearchDTO>> getAllByForm(@PathVariable String patientName)
    {
        List<Patient> medications=  patientService.findAllByName(patientName);
        List<PatientSearchDTO> medicationsForFront = new ArrayList<>();
        for (Patient medication: medications) {
            PatientSearchDTO medicationSearchDTO = getMedicationSearchDTO(medication);
            medicationsForFront.add(medicationSearchDTO);
        }
        return medicationsForFront == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medicationsForFront);
    }

    private PatientSearchDTO getMedicationSearchDTO(Patient medication) {
        //Specification specification = medication.getSpecification();
       // SpecificationDTO specificationDTO = new SpecificationDTO(specification.getContraIndications(),
               // specification.getStructure(), specification.getRecommendedConsumption(), specification.getManufacturer());
        return new PatientSearchDTO(medication.getName(), medication.getSurname());
    }
    @PostMapping("/searchName")
    @PreAuthorize("hasAnyRole('DERMATOLOGIST', 'PHARMACIST')")
    ResponseEntity<List<PatientForFrontDTO>> getAllByName(@RequestBody PatientSearchDTO dto)
    {
        List<Patient> patients= patientService.findByName(dto.getName(),dto.getSurname());
        List<PatientForFrontDTO> patientForFrontDTOS = new ArrayList<>();
        for (Patient patient: patients) {
            PatientForFrontDTO patientForFrontDTO = new PatientForFrontDTO(patient.getId(), patient.getEmail(), patient.getName(),patient.getSurname(), patient.getPhoneNumber());
            patientForFrontDTOS.add(patientForFrontDTO);
        }


        return  ResponseEntity.ok(patientForFrontDTOS);
    }



}
