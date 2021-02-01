package com.isaproject.isaproject.Model;


import com.isaproject.isaproject.Model.Examinations.*;
import com.isaproject.isaproject.Model.HelpModel.*;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Medicine.Specification;
import com.isaproject.isaproject.Model.Orders.Offer;
import com.isaproject.isaproject.Model.Orders.Order;
import com.isaproject.isaproject.Model.Pharmacy.Actions;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Schedule.HolidayScheduleDermatologist;
import com.isaproject.isaproject.Model.Schedule.HolidaySchedulePharmacist;
import com.isaproject.isaproject.Model.Schedule.WorkingHoursDermatologist;
import com.isaproject.isaproject.Model.Schedule.WorkingHoursPharmacist;
import com.isaproject.isaproject.Model.Users.*;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

        Address address = new Address();
        address.setCountry("Serbia");
        address.setNumber(2);
        address.setStreet("Pavla Simica");
        address.setTown("Novi Sad");
        address.setPostalCode(21000);

        Address address2 = new Address();
        address2.setCountry("Serbia");
        address2.setNumber(2);
        address2.setStreet("Pavla Simica");
        address2.setTown("Beograd");
        address2.setPostalCode(21000);

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


        Grading grading = new Grading();
        grading.setPatient(patient);
        grading.setGrade(5);
        grading.setType("pharmacy");
        grading.setGradedId(4);


        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setPharmacyName("Apoteka");
        pharmacy.setAddress(address);
        pharmacy.setConsultingPrice(50);
        pharmacy.setMark(5);


        Pharmacy pharmacy2 = new Pharmacy();
        pharmacy2.setPharmacyName("Apoteka   2");
        pharmacy2.setAddress(address2);
        pharmacy2.setConsultingPrice(50);
        pharmacy2.setMark(5);

        Actions action1 = new Actions();
        action1.setDescription("Akcija");
        //action1.setExpiryDate(LocalDate.now());
        action1.setDescription("Akcija za panadol");
        action1.setPharmacy(pharmacy);


        Actions action2 = new Actions();
        action2.setDescription("Akcija 22");
       // action2.setExpiryDate(LocalDate.now());
        action2.setDescription("Akcija za brufen");
        action2.setPharmacy(pharmacy2);

        Pharmacist pharmacist = new Pharmacist();
        pharmacist.setAddress(address);
        pharmacist.setFirstLogged(true);
        pharmacist.setName("Luna");
        pharmacist.setPassword("luna");
        pharmacist.setEmail("lunci@gmail.com");
        pharmacist.setPhoneNumber("0652610775");
        pharmacist.setSurname("Zivkovic");



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

        Set<Pharmacy> pharmacies = new HashSet<Pharmacy>();
        pharmacies.add(pharmacy);
        pharmacies.add(pharmacy2);
        dermatologist.setPharmacies(pharmacies);


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

        Complaint complaint = new Complaint();
        complaint.setAnswer("Answer");
        complaint.setPatient(patient);
        complaint.setMassage("Message");
        complaint.setSubject("pharmacist");
       // complaint.setSubjectId(1);
        complaint.setAnswered(true);

        Specification specification = new Specification();
        specification.setContraIndications("Bol");
        specification.setManufacturer("Galenika");
        specification.setStructure("Tableta");
        specification.setRecommendedConsumption("2 puta dnevno");

        Medication medication = new Medication();
        medication.setName("Brufen");
        medication.setCode(854625689);
        medication.setForm("Tableta");
        medication.setMark(5);
        medication.setType("type1");
        medication.setIssuanceRegime("Rezim");
        medication.setLoyaltyPoints(54);
        medication.setSpecification(specification);

        Specification specification2 = new Specification();
        specification2.setContraIndications("Svrab");
        specification2.setManufacturer("Proizvodjac");
        specification2.setStructure("Pilula");
        specification2.setRecommendedConsumption("2 puta nedeljno");

        Medication medication2 = new Medication();
        medication2.setName("Andol");
        medication2.setCode(89465);
        medication2.setForm("Tableta");
        medication2.setMark(5);
        medication2.setType("type2");
        medication2.setIssuanceRegime("Rezim2");
        medication2.setLoyaltyPoints(100);
        medication2.setSpecification(specification2);


        Specification specification3 = new Specification();
        specification3.setContraIndications("Glavobolja");
        specification3.setManufacturer("Proizvodjac");
        specification3.setStructure("Sprej");
        specification3.setRecommendedConsumption("2 puta mesecno");

        Medication medication3 = new Medication();
        medication3.setName("Aspirin");
        medication3.setCode(4234324);
        medication3.setForm("Pilula");
        medication3.setMark(4);
        medication3.setType("type2");
        medication3.setIssuanceRegime("Rezim3");
        medication3.setLoyaltyPoints(100);
        medication3.setSpecification(specification3);

        Set<Medication> medicationsAlternatives = new HashSet<Medication>();
        medicationsAlternatives.add(medication);
        medicationsAlternatives.add(medication2);

        medication3.setMedications(medicationsAlternatives);

        Set<Medication> medications = new HashSet<Medication>();
        medications.add(medication);
        medications.add(medication2);


        Set<Medication> medications2 = new HashSet<Medication>();
        medications2.add(medication);
        medications2.add(medication3);


        //APOTEKA


        EPrescription eprescription = new EPrescription();
        eprescription.setPatient(patient);
        eprescription.setDate(LocalDate.now());
        eprescription.setCode(78465231);
        eprescription.setStatus("zavrsen");
        eprescription.setMedications(medications);

        Prescription prescription = new Prescription();
        prescription.setMedications(medications);
        prescription.setPatient(patient);
        prescription.setTaken(true);
        prescription.setDurationOfTherapy(5);
        prescription.setDate(LocalDate.now());
        prescription.setInformation("Information");
        prescription.setPharmacy(pharmacy);




        PatientsMedicationAlergy patientsMedicationAlergy = new PatientsMedicationAlergy();
        patientsMedicationAlergy.setPatient(patient);
        patientsMedicationAlergy.setMedication(medication);
        patientsMedicationAlergy.setDescription("Pain");


        PharmacyAdmin pharmacyAdmin = new PharmacyAdmin();
        pharmacyAdmin.setAddress(address);
        pharmacyAdmin.setFirstLogged(true);
        pharmacyAdmin.setName("Pera");
        pharmacyAdmin.setPassword("pera");
        pharmacyAdmin.setEmail("pera@gmail.com");
        pharmacyAdmin.setPhoneNumber("43435");
        pharmacyAdmin.setSurname("Peric");
        pharmacyAdmin.setPharmacy(pharmacy);


        PharmacyAdmin pharmacyAdmin2 = new PharmacyAdmin();
        pharmacyAdmin2.setAddress(address);
        pharmacyAdmin2.setFirstLogged(true);
        pharmacyAdmin2.setName("Zika");
        pharmacyAdmin2.setPassword("zika");
        pharmacyAdmin2.setEmail("zika@gmail.com");
        pharmacyAdmin2.setPhoneNumber("43435");
        pharmacyAdmin2.setSurname("Zikic");
        pharmacyAdmin2.setPharmacy(pharmacy);

        MedicationPrice medicationPrice = new MedicationPrice();
        medicationPrice.setPrice(452.0);
        medicationPrice.setDate(LocalDate.now());
        medicationPrice.setPharmacy(pharmacy);

        MedicationReservation medicationReservation =  new MedicationReservation();
        medicationReservation.setPatient(patient);
        medicationReservation.setDateOfReservation(LocalDate.now());
        medicationReservation.setCancelled(false);
        medicationReservation.setCollected(false);
        medicationReservation.setMedicine(medication);
        medicationReservation.setPharmacy(pharmacy);
        medicationReservation.setDateOfTakeOver(LocalDate.now());

        Set<Offer> offers = new HashSet<Offer>();
        Set<Order> orders = new HashSet<Order>();

        Set<Consulting> consultings = new HashSet<Consulting>();

        Order order = new Order();
        order.setPharmacyAdmin(pharmacyAdmin);

        order.setDate(LocalDate.now());
        order.setStatus("closed");
        order.setOffer(offers);



        HolidayScheduleDermatologist holidayScheduleDermatologist = new HolidayScheduleDermatologist();
        holidayScheduleDermatologist.setDermatologist(dermatologist);
        holidayScheduleDermatologist.setApproved("");
        holidayScheduleDermatologist.setMassage("porukica neka");
        holidayScheduleDermatologist.setEndDate(LocalDate.now());

        HolidaySchedulePharmacist holidaySchedulePharmacist = new HolidaySchedulePharmacist();
        holidaySchedulePharmacist.setPharmacist(pharmacist);
        holidaySchedulePharmacist.setMassage("neka poruka farmaceuta");
        holidaySchedulePharmacist.setApproved("true");
        holidaySchedulePharmacist.setEndDate(LocalDate.now());

        WorkingHoursPharmacist workingHoursPharmacist = new WorkingHoursPharmacist();

        WorkingHoursDermatologist workingHoursDermatologist = new WorkingHoursDermatologist();




        manager.getTransaction().begin();
        manager.persist(address);
        manager.persist(address2);

        manager.persist(pharmacy);
        manager.persist(pharmacy2);

        manager.persist(action1);
        manager.persist(action2);
        manager.persist(patient);
        manager.persist(grading);

        manager.persist(pharmacist);
        manager.persist(dermatologist);
        manager.persist(consulting);

        manager.persist(examinationSchedule);
        manager.persist(examination);
        manager.persist(complaint);
        manager.persist(specification);
        manager.persist(specification2);
        manager.persist(specification3);
        manager.persist(medication);
        manager.persist(medication2);
        manager.persist(medication3);
        manager.persist(eprescription);
        manager.persist(prescription);


        manager.persist(patientsMedicationAlergy);
        manager.persist(pharmacyAdmin);
        manager.persist(pharmacyAdmin2);
        manager.persist(medicationPrice);
        manager.persist(medicationReservation);
        manager.persist(order);
        manager.persist(holidayScheduleDermatologist);
        manager.persist(holidaySchedulePharmacist);
        manager.persist(workingHoursPharmacist);
        manager.persist(workingHoursDermatologist);


        manager.getTransaction().commit();

        manager.close();
    }


}
