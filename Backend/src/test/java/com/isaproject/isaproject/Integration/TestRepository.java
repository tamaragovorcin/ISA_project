package com.isaproject.isaproject.Integration;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isaproject.isaproject.DTO.AddressDTO;
import com.isaproject.isaproject.DTO.PharmacyIdDTO;
import com.isaproject.isaproject.DTO.PharmacyNameDTO;
import com.isaproject.isaproject.Model.HelpModel.Complaint;
import com.isaproject.isaproject.Model.HelpModel.LoyaltyProgram;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestRepository {
    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    protected PharmacyIdDTO pharmacyIdDTO;

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
        pharmacist.setAuthorities(auth5);
        pharmacist.setEnabled(true);
        pharmacist.setPharmacy(pharmacy);

        //PHARMACY ADMIN
        PharmacyAdmin pharmacyAdmin = new PharmacyAdmin();
        pharmacyAdmin.setSurname("Surname");
        pharmacyAdmin.setPassword(passwordEncoder.encode("pharmacyAdminPassword"));
        pharmacyAdmin.setName("Name");
        pharmacyAdmin.setEmail("pharmacyAdmin@gmail.com");
        Address addressPharmacyAdmin = new Address();
        AddressDTO addressDTOPharmacyAdmin= new AddressDTO("Town","Street",125,76328,"Country");
        addressPharmacyAdmin.setPostalCode(addressDTOPharmacyAdmin.getPostalCode());
        addressPharmacyAdmin.setTown(addressDTOPharmacyAdmin.getTown());
        addressPharmacyAdmin.setStreet(addressDTOPharmacyAdmin.getStreet());
        addressPharmacyAdmin.setNumber(addressDTOPharmacyAdmin.getNumber());
        addressPharmacyAdmin.setCountry(addressDTOPharmacyAdmin.getCountry());
        pharmacyAdmin.setAddress(address);
        pharmacyAdmin.setPhoneNumber("5623");
        pharmacyAdmin.setEnabled(true);
        pharmacyAdmin.setPharmacy(pharmacy);
        List<Authority> authPharmacyAdmin = new ArrayList<>();
        Authority authorityPharmacyAdmin = new Authority("ROLE_PHARMACY_ADMIN");
        authPharmacyAdmin.add(authorityPharmacyAdmin);
        pharmacyAdmin.setAuthorities(authPharmacyAdmin);

        //Dermatologist
        Dermatologist dermatologist =  new Dermatologist();
        dermatologist.setName("FirstName");
        dermatologist.setSurname("Surname");

        Address addressDermatologist= new Address();
        AddressDTO addressDTODermatologist= new AddressDTO("Town2","Street2",123,123456,"Country2");
        addressDermatologist.setPostalCode(addressDTODermatologist.getPostalCode());
        addressDermatologist.setTown(addressDTODermatologist.getTown());
        addressDermatologist.setStreet(addressDTODermatologist.getStreet());
        addressDermatologist.setNumber(addressDTODermatologist.getNumber());
        addressDermatologist.setCountry(addressDTODermatologist.getCountry());
        dermatologist.setAddress(addressDermatologist);

        dermatologist.setEmail("dermatologist@gmail.com");
        dermatologist.setPassword(passwordEncoder.encode("dermatologistPassword"));
        dermatologist.setFirstLogged(false);
        dermatologist.setPhoneNumber("063789123");
        List<Authority> authDermatologist = new ArrayList<Authority>();
        Authority authorityDermatologist = new Authority("ROLE_DERMATOLOGIST");
        authDermatologist.add(authorityDermatologist);
        dermatologist.setAuthorities(authDermatologist);
        dermatologist.setEnabled(true);

        entityManager.persist(patient);
        entityManager.persist(authority4);
        entityManager.persist(pharmacy);
        entityManager.persist(pharmacist);
        entityManager.persist(loyaltyProgram);
        entityManager.persist(authority2);
        entityManager.persist(systemAdmin);
        entityManager.persist(pharmacyAdmin);
        entityManager.persist(dermatologist);
        entityManager.persist(supplier);
        entityManager.persist(authority);
        pharmacyIdDTO = new PharmacyIdDTO(pharmacy.getId());

        entityManager.flush();
    }
}
