package com.isaproject.isaproject.Integration;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.HelpModel.Complaint;
import com.isaproject.isaproject.Model.HelpModel.LoyaltyProgram;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Medicine.Specification;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Schedule.HolidaySchedulePharmacist;
import com.isaproject.isaproject.Model.Users.*;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import javax.persistence.EntityManager;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestRepository {
    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    protected PharmacyIdDTO pharmacyIdDTO;

    protected MarkDTO markDTO;

    protected MedicationDTO medicationDTO;

    protected PharmacistsConsultationDTO pharmacistsConsultationDTO;

    protected Integer patientId;

    protected Integer holidayId;

    protected Integer pharmacyId;
    protected PersonUserDTO personuserDTO;


    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }

    protected void SetTestData() {
        //SUPPLIER
        Supplier supplier = new Supplier();
        supplier.setSurname("Surname");
        supplier.setPassword(passwordEncoder.encode("supplierPassword"));
        supplier.setName("Name");
        supplier.setEmail("supplier@gmail.com");
        Address address = new Address();
        AddressDTO addressDTO= new AddressDTO("Town","Street",453,5435,"Country");
        address.setPostalCode(addressDTO.getPostalCode());
        address.setTown(addressDTO.getTown());
        address.setStreet(addressDTO.getStreet());
        address.setNumber(addressDTO.getNumber());
        addressDTO.setCountry(addressDTO.getCountry());
        supplier.setAddress(address);
        supplier.setPhoneNumber("5623");
        supplier.setEnabled(true);
        List<Authority> auth = new ArrayList<>();
        Authority authority = new Authority("ROLE_SUPPLIER");
        auth.add(authority);
        supplier.setAuthorities(auth);

        //LOYALTY PROGRAM
        LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
        loyaltyProgram.setConsultingPoints(90);
        loyaltyProgram.setExaminationPoints(5);
        loyaltyProgram.setSilverLimit(578);
        loyaltyProgram.setSilverDiscount(650);
        loyaltyProgram.setRegularLimit(300);
        loyaltyProgram.setRegularDiscount(20);
        loyaltyProgram.setGoldenDiscount(50);
        loyaltyProgram.setGoldLimit(750);

        //SYSTEM ADMIN
        SystemAdmin systemAdmin =  new SystemAdmin();
        systemAdmin.setName("FisrtName");
        systemAdmin.setSurname("Surname");

        Address address2 = new Address();
        AddressDTO addressDTO2= new AddressDTO("Town2","Street2",255,257,"Country2");
        address2.setPostalCode(addressDTO2.getPostalCode());
        address2.setTown(addressDTO2.getTown());
        address2.setStreet(addressDTO2.getStreet());
        address2.setNumber(addressDTO2.getNumber());
        addressDTO2.setCountry(addressDTO2.getCountry());
        systemAdmin.setAddress(address2);

        systemAdmin.setEmail("admin@gmail.com");
        systemAdmin.setPassword(passwordEncoder.encode("adminPassword"));
        systemAdmin.setFirstLogged(false);
        systemAdmin.setPhoneNumber("78456123");
        List<Authority> auth2 = new ArrayList<Authority>();
        Authority authority2 = new Authority("ROLE_SYSTEM_ADMIN");
        auth2.add(authority2);
        systemAdmin.setAuthorities(auth2);
        systemAdmin.setEnabled(true);

        //PATIENT
        Patient patient =  new Patient();
        patient.setName("FisrtName");
        patient.setSurname("Surname");

        Address address4 = new Address();
        AddressDTO addressDTO4= new AddressDTO("Town2","Street2",255,257,"Country2");
        address4.setPostalCode(addressDTO4.getPostalCode());
        address4.setTown(addressDTO4.getTown());
        address4.setStreet(addressDTO4.getStreet());
        address4.setNumber(addressDTO4.getNumber());
        addressDTO4.setCountry(addressDTO4.getCountry());
        patient.setAddress(address4);

        patient.setEmail("patient@gmail.com");
        patient.setPassword(passwordEncoder.encode("patientPassword"));
        patient.setFirstLogged(false);
        patient.setPhoneNumber("7456123");
        List<Authority> auth4 = new ArrayList<Authority>();
        Authority authority4 = new Authority("ROLE_PATIENT");
        auth4.add(authority4);
        patient.setAuthorities(auth4);
        patient.setEnabled(true);

        //PHARMACY
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setPharmacyName("PharmacyName");
        pharmacy.setDescription("PharmacyDescription");
        Address address5 = new Address();
        AddressDTO addressDTO5= new AddressDTO("Town2","Street2",255,257,"Country2");
        address5.setPostalCode(addressDTO5.getPostalCode());
        address5.setTown(addressDTO5.getTown());
        address5.setStreet(addressDTO5.getStreet());
        address5.setNumber(addressDTO5.getNumber());
        addressDTO5.setCountry(addressDTO5.getCountry());
        pharmacy.setAddress(address5);
        pharmacy.setConsultingPrice(100);




        //PharmacyMark
        MarkDTO markDTO1 = new MarkDTO();
        markDTO1.setMark(5);
        markDTO1.setPatient(patient.getId());
        markDTO1.setPharmacy(pharmacy);

        //Pharmacist
        Pharmacist pharmacist =  new Pharmacist();
        pharmacist.setName("FisrtName");
        pharmacist.setSurname("Surname");

        Address address6 = new Address();
        AddressDTO addressDTO6= new AddressDTO("Town2","Street2",255,257,"Country2");
        address6.setPostalCode(addressDTO6.getPostalCode());
        address6.setTown(addressDTO6.getTown());
        address6.setStreet(addressDTO6.getStreet());
        address6.setNumber(addressDTO6.getNumber());
        addressDTO6.setCountry(addressDTO6.getCountry());
        patient.setAddress(address6);

        pharmacist.setEmail("pharmacist@gmail.com");
        pharmacist.setPassword(passwordEncoder.encode("pharmacistPassword"));
        pharmacist.setFirstLogged(false);
        pharmacist.setPhoneNumber("7456123");
        List<Authority> auth5 = new ArrayList<Authority>();
        Authority authority5 = new Authority("ROLE_PHARMACIST");
        auth5.add(authority5);
        pharmacist.setPharmacy(pharmacy);
        pharmacist.setAuthorities(auth5);
        pharmacist.setEnabled(true);


        //ConsultingDTO
        PharmacistsConsultationDTO pharmacistsConsultationDTO1 = new PharmacistsConsultationDTO();
        pharmacistsConsultationDTO1.setDate(LocalDate.now().plusDays(2));
        pharmacistsConsultationDTO1.setPharmacist(pharmacist);
        pharmacistsConsultationDTO1.setTime(LocalTime.NOON);
        pharmacistsConsultationDTO1.setPatient(patient.getId());

        //Dermatologist
        Dermatologist dermatologist =  new Dermatologist();
        dermatologist.setName("FisrtName");
        dermatologist.setSurname("Surname");

        Address address10 = new Address();
        AddressDTO addressDTO10= new AddressDTO("Town2","Street2",255,257,"Country2");
        address10.setPostalCode(addressDTO10.getPostalCode());
        address10.setTown(addressDTO10.getTown());
        address10.setStreet(addressDTO10.getStreet());
        address10.setNumber(addressDTO10.getNumber());
        address10.setCountry(addressDTO10.getCountry());
        patient.setAddress(address10);

        dermatologist.setEmail("dermatologist@gmail.com");
        dermatologist.setPassword(passwordEncoder.encode("dermatologistPassword"));
        dermatologist.setFirstLogged(false);
        dermatologist.setPhoneNumber("7456123");
        List<Authority> auth10 = new ArrayList<Authority>();
        Authority authority10 = new Authority("ROLE_DERMATOLOGIST");
        auth10.add(authority10);
        dermatologist.setAuthorities(auth10);
        dermatologist.setEnabled(true);

        ExaminationSchedule examinationSchedule =  new ExaminationSchedule();
        examinationSchedule.setPrice(250);
        examinationSchedule.setFinished(false);
        examinationSchedule.setStartTime(LocalTime.now());
        examinationSchedule.setDermatologist(dermatologist);
        examinationSchedule.setDate(LocalDate.now());
        examinationSchedule.setPharmacy(pharmacy);
        examinationSchedule.setDuration(20l);
        //Consultation
       /* Consulting consulting = new Consulting();
        consulting.setCancelled(false);
        consulting.setDate(LocalDate.now());
        consulting.setDuration(20.0);
        consulting.setPrice(550);
        consulting.setStartTime(LocalTime.now());
        consulting.setPatient(patient);
        consulting.setShowedUp(false);
        consulting.setPharmacist(pharmacist);*/

        //PHARMACY ADMIN

        PharmacyAdmin pharmacyAdmin = new PharmacyAdmin();
        pharmacyAdmin.setSurname("Kokanovic");
        pharmacyAdmin.setPassword(passwordEncoder.encode("mladenka"));
        pharmacyAdmin.setName("Mladenka");
        pharmacyAdmin.setEmail("mladenka@gmail.com");
        Address addressMladenka = new Address();
        AddressDTO addressDTOMladenka= new AddressDTO("Bijeljina","Gavrila Principa",15,76328,"Bosna i Hercegovina");
        addressMladenka.setPostalCode(addressDTOMladenka.getPostalCode());
        addressMladenka.setTown(addressDTOMladenka.getTown());
        addressMladenka.setStreet(addressDTOMladenka.getStreet());
        addressMladenka.setNumber(addressDTOMladenka.getNumber());
        addressMladenka.setCountry(addressDTOMladenka.getCountry());
        pharmacyAdmin.setAddress(addressMladenka);
        pharmacyAdmin.setPhoneNumber("066492611");
        pharmacyAdmin.setEnabled(true);
        pharmacyAdmin.setPharmacy(pharmacy);
        List<Authority> authMladenka = new ArrayList<>();
        Authority authorityMladenka = new Authority("ROLE_PHARMACY_ADMIN");
        authMladenka.add(authorityMladenka);
        pharmacyAdmin.setAuthorities(authMladenka);


        MedicationDTO medicationDTO1 = new MedicationDTO();
        medicationDTO1.setCode(1234l);
        medicationDTO1.setName("MedicationName");
        medicationDTO1.setType("Type");
        medicationDTO1.setForm("Form");
        medicationDTO1.setMark(5);
        medicationDTO1.setLoyaltyPoints(3);
        medicationDTO1.setIssuanceRegime("Regime");
        SpecificationDTO specificationDTO = new SpecificationDTO();
        specificationDTO.setContraIndications("contraind");
        specificationDTO.setManufacturer("Manu");
        specificationDTO.setStructure("structure");
        specificationDTO.setRecommendedConsumption("consumption");
        medicationDTO1.setSpecification(specificationDTO);
        medicationDTO1.setWayOfSelling("way");


        Medication medication1 = new Medication();
        medication1.setCode(123434l);
        medication1.setName("MedicationName2");
        medication1.setType("Type");
        medication1.setForm("Form");
        medication1.setMark(5);
        medication1.setLoyaltyPoints(3);
        medication1.setIssuanceRegime("Regime");
        Specification specificationDTO1 = new Specification();
        specificationDTO1.setContraIndications("contraind");
        specificationDTO1.setManufacturer("Manu");
        specificationDTO1.setStructure("structure");
        specificationDTO1.setRecommendedConsumption("consumption");
        medication1.setSpecification(specificationDTO1);
        medication1.setWayOfSelling("way");


        HolidaySchedulePharmacist holidaySchedulePharmacist = new HolidaySchedulePharmacist();
        holidaySchedulePharmacist.setMassage("poruka");
        holidaySchedulePharmacist.setPharmacist(pharmacist);
        holidaySchedulePharmacist.setApproved("WAITING_FOR_RESPONSE");
        holidaySchedulePharmacist.setType("VACATION");
        holidaySchedulePharmacist.setStartDate(LocalDate.now());
        holidaySchedulePharmacist.setEndDate(LocalDate.now().plusDays(10));


        PersonUserDTO userDTO = new PersonUserDTO();
        userDTO.setSurname("Surname");
        userDTO.setPassword("password");
        userDTO.setRewritePassword("password");
        userDTO.setFirstname("Name");
        userDTO.setAddress(new AddressDTO("Town","Street",453,5435,"Country"));
        userDTO.setEmail("user22@gmail.com");
        userDTO.setPhonenumber("78912");


        entityManager.persist(address4);
        entityManager.persist(addressMladenka);
        entityManager.persist(authorityMladenka);
        entityManager.persist(address5);
        entityManager.persist(patient);
        entityManager.persist(authority4);
        entityManager.persist(pharmacy);
        entityManager.persist(loyaltyProgram);
        entityManager.persist(authority2);
        entityManager.persist(systemAdmin);
        entityManager.persist(supplier);
        entityManager.persist(authority);
        entityManager.persist(authority5);
        entityManager.persist(pharmacist);
        entityManager.persist(pharmacyAdmin);
        entityManager.persist(holidaySchedulePharmacist);
        entityManager.persist(medication1);
        entityManager.persist(address10);
        entityManager.persist(authority10);
        entityManager.persist(dermatologist);
        entityManager.persist(examinationSchedule);
        pharmacyIdDTO = new PharmacyIdDTO(pharmacy.getId());
        pharmacistsConsultationDTO1.setPharmacyId(pharmacyIdDTO.getPharmacyId());
        pharmacistsConsultationDTO = pharmacistsConsultationDTO1;
        markDTO = markDTO1;
        patientId = patient.getId();
        medicationDTO = medicationDTO1;
        holidayId = holidaySchedulePharmacist.getId();
        pharmacyId = pharmacy.getId();
        personuserDTO = userDTO;

        entityManager.flush();
    }
}