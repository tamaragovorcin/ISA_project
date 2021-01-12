package com.isaproject.isaproject.Model;


import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Examinations.Examination;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.Pharmacy.Actions;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Address;
import com.isaproject.isaproject.Model.Users.Dermatologist;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.Pharmacist;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class proba {
    public static void main(String[] args) throws Exception {
        Logger.getLogger("").setLevel(Level.OFF);
        final EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("isadatabase");

        EntityManager manager = factory.createEntityManager();

        Actions actions = new Actions();
        actions.setDescription("opisssss");
        actions.setExpiryDate(LocalDate.now());
        actions.setDescription("opis drugi neki");


        Address address = new Address();
        address.setCountry("Serbia");
        address.setNumber(2);
        address.setStreet("Pavla Simica");
        address.setTown("Novi Sad");
        address.setPostalCode(21000);



        Patient patient = new Patient();
        patient.setAddress(address);
        patient.setDiscount(30.0);
        patient.setPenalties(0);
        patient.setPoints(30);
        patient.setLoyaltyCategory("GOLD");
        patient.setFirstLogged(true);
        patient.setName("Luna");
        patient.setPassword("luna");
        patient.setEmail("lunaz@gmail.com");
        patient.setPhoneNumber("0652610775");
        patient.setSurname("Zivkovic");

        Pharmacist pharmacist = new Pharmacist();
        pharmacist.setAddress(address);
        pharmacist.setFirstLogged(true);
        pharmacist.setName("Luna");
        pharmacist.setPassword("luna");
        pharmacist.setEmail("lunci@gmail.com");
        pharmacist.setPhoneNumber("0652610775");
        pharmacist.setSurname("Zivkovic");
        pharmacist.setMarkPharmacist(5);


        Consulting consulting = new Consulting();
        consulting.setCancelled(false);
        consulting.setDate(LocalDate.now());
        consulting.setDuration(30.0);
        consulting.setInformation("blabla");
        consulting.setPrice(25.0);
        consulting.setShowedUp(true);
        consulting.setStartTime(LocalTime.now());
        consulting.setPatient(patient);
        consulting.setPharmacist(pharmacist);

        Dermatologist dermatologist = new Dermatologist();
        dermatologist.setAddress(address);
        dermatologist.setFirstLogged(true);
        dermatologist.setName("Luna");
        dermatologist.setPassword("luna");
        dermatologist.setEmail("luuuuu@gmail.com");
        dermatologist.setPhoneNumber("0652610775");
        dermatologist.setSurname("Zivkovic");
        dermatologist.setMarkDermatologist(5);


        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setPharmacyName("Apoteka");
        pharmacy.setAddress(address);
        pharmacy.setConsultingPrice(50);
        pharmacy.setMark(5);

        ExaminationSchedule examinationSchedule = new ExaminationSchedule();
        examinationSchedule.setDermatologist(dermatologist);
        examinationSchedule.setDate(LocalDate.now());
        examinationSchedule.setDuration(30.0);
        examinationSchedule.setPrice(150.0);
        examinationSchedule.setStartTime(LocalTime.now());
        examinationSchedule.setPharmacy(pharmacy);

        Examination examination = new Examination();
        examination.setPatient(patient);
        examination.setCancelled(false);
        examination.setShowedUp(true);
        examination.setInformation("bjsyfsje");
        examination.setExaminationSchedule(examinationSchedule);






        manager.getTransaction().begin();
        manager.persist(address);
        manager.persist(patient);
        manager.persist(pharmacist);
        manager.persist(dermatologist);
        manager.persist(consulting);
        manager.persist(pharmacy);
        manager.persist(examinationSchedule);
        manager.persist(examination);
        manager.persist(actions);
        manager.getTransaction().commit();

        manager.close();
    }


}
