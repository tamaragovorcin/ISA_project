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
    public void testGetDermatologistAppointments() throws Exception {
        JwtAuthenticationRequest loginDTO = new JwtAuthenticationRequest();
        loginDTO.setEmail("patient@gmail.com");
        loginDTO.setPassword("patientPassword");

        String input = mapToJson(loginDTO);
        String uri = "/api/login";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(input)).andReturn();

        UserTokenState userTokenState = mapFromJson(result.getResponse().getContentAsString(), UserTokenState.class);

        String uri2 = "/api/pharmacy/ExaminationSchedule";

        mockMvc.perform(MockMvcRequestBuilders.get(uri2).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(input))
                .andExpect(status().is(200));
    }


    @Test
    @Transactional
    public void testGetPatientsAlergies() throws Exception {
        JwtAuthenticationRequest loginDTO = new JwtAuthenticationRequest();
        loginDTO.setEmail("patient@gmail.com");
        loginDTO.setPassword("patientPassword");

        String input = mapToJson(loginDTO);
        String uri = "/api/login";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(input)).andReturn();

        UserTokenState userTokenState = mapFromJson(result.getResponse().getContentAsString(), UserTokenState.class);


        String uri2 = "/api/patient/getAlergies/" + patientId;

        mockMvc.perform(MockMvcRequestBuilders.get(uri2).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }



    @Test
    @Transactional
    public void testSuccessfullyAddConsultation() throws Exception {

        JwtAuthenticationRequest loginDTO = new JwtAuthenticationRequest();
        loginDTO.setEmail("patient@gmail.com");
        loginDTO.setPassword("patientPassword");

        String input = mapToJson(loginDTO);
        String uri = "/api/login";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(input)).andReturn();

        UserTokenState userTokenState = mapFromJson(result.getResponse().getContentAsString(), UserTokenState.class);

        String input2 = mapToJson(pharmacistsConsultationDTO);
        System.out.println(pharmacistsConsultationDTO);
        String uri2 = "/api/consulting/reserveConsultation";

        mockMvc.perform(MockMvcRequestBuilders.post(uri2).header("token",  userTokenState.getAccessToken())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(input2)
                );
    }

    @Test
    @Transactional
    public void testGetPatientInfoForUpdate() throws Exception {

        JwtAuthenticationRequest loginDTO = new JwtAuthenticationRequest();
        loginDTO.setEmail("patient@gmail.com");
        loginDTO.setPassword("patientPassword");

        String input = mapToJson(loginDTO);
        String uri = "/api/login";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(input)).andReturn();

        UserTokenState userTokenState = mapFromJson(result.getResponse().getContentAsString(), UserTokenState.class);

        String uri2 = "/api/patient/account";

        mockMvc.perform(MockMvcRequestBuilders.get(uri2).header("token",  userTokenState.getAccessToken()).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", notNullValue()))
                .andExpect(status().is(200));
    }


}