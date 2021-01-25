package com.isaproject.isaproject.Controller;



import ch.qos.logback.core.net.SyslogOutputStream;

import com.isaproject.isaproject.DTO.ActionsDTO;

import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Pharmacy.Actions;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Service.Implementations.ActionsService;

import com.isaproject.isaproject.Model.Users.*;
import com.isaproject.isaproject.Repository.ConfirmationTokenRepository;
import com.isaproject.isaproject.Repository.PatientRepository;
import com.isaproject.isaproject.Service.Implementations.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import javax.swing.*;
import javax.websocket.server.PathParam;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/patient")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PatientController {
    @Autowired
    PatientService patientService;


    @Autowired
    PatientRepository patientRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private Environment environment;

    @Autowired
    JavaMailSenderImpl mailSender;

    @PostMapping("/register")
    public ResponseEntity<String> registerPatient(@RequestBody PersonUserDTO person)
    {

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
            //mail.setFrom("pharmacyisa@gmail.com");
            mail.setText("To confirm your account, please click here : "
                    +"http://localhost:8082/api/patient/confirm-account/"+confirmationToken.getConfirmationToken());

            mailSender.send(mail);
            return ResponseEntity.ok("");
        }
    }

    @GetMapping(path = "/confirm-account/{token}")
    public Object confirmUserAccount(@PathVariable String token)
    {
        ConfirmationToken token2 = confirmationTokenRepository.findByConfirmationToken(token);

        if(token != null)
        {
            Patient user = patientService.findByEmail(token2.getPersonUser().getEmail());
            user.setEnabled(true);
            patientRepository.save(user);
            RedirectView redirect = new RedirectView();
            redirect.setUrl("http://localhost:8085/patientProfile");
            return redirect;
        }
        else
        {
            return "The link is invalid or broken!!";
        }

    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<Patient> getById(@PathVariable Integer id)
    {
        Integer idd = 1;
        Patient patient = patientService.findById(idd);
        return patient == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patient);
    }

    @GetMapping("/account")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<Patient> getMyAccount()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        Patient patient = patientService.findById(user.getId());
        return patient == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patient);
    }


    @GetMapping("/address")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<Address> getMyAddress()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();

        Patient patient = patientService.findById(user.getId());
        return patient.getAddress() == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patient.getAddress());
    }

    @GetMapping("")
    ResponseEntity<List<Patient>> getAll()
    {
        List<Patient> patients = patientService.findAll();
        return patients == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patients);
    }
    @GetMapping("/email/{id}")
    ResponseEntity<String> getEmail(@PathVariable Integer id)
    {
        String email = patientService.getEmail(id);
        return email == null || email.equals("") ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(email);
    }


    @PostMapping("/update")
    ResponseEntity<Patient> update(@RequestBody PersonUserDTO person)
    {
        Patient per = patientService.findByEmail(person.getEmail());
        Integer id = per.getId();
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+id);
        patientService.delete(per);
        Patient patient = patientService.update(person, id);
        return patient == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patient);
    }

}
