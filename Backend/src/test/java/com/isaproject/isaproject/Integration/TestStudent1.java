package com.isaproject.isaproject.Integration;

import com.isaproject.isaproject.Authentification.JwtAuthenticationRequest;
import com.isaproject.isaproject.DTO.AddressDTO;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.UserTokenState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;

import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestStudent1 extends TestRepository {

    @Before
    public void setUp() { super.setUp(); }
    @Before
    public void CreateEntities() { super.SetTestData(); }

    @Test
    @Transactional
    public void testSuccesfulPatientLogin() throws Exception {
        JwtAuthenticationRequest loginDTO = new JwtAuthenticationRequest();
        loginDTO.setEmail("patient@gmail.com");
        loginDTO.setPassword("patientPassword");

        String input = mapToJson(loginDTO);
        String uri = "/api/login";
        mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(input))
                .andExpect(status().is(200));
    }

    @Test
    @Transactional
    public void testUpdateProfile() throws Exception {
        Patient patient = new Patient();
        patient.setId(1);
        patient.setSurname("Surname");
        patient.setPassword("password");
        patient.setName("Name");
        patient.setEmail("user@gmail.com");
        patient.setPhoneNumber("78912545");

        String input = mapToJson(patient);
        String uri = "/api/patient/update";
        mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(input))
                .andExpect(status().is(200));
    }


}