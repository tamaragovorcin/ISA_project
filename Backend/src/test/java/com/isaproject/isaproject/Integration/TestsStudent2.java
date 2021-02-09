package com.isaproject.isaproject.Integration;

import com.isaproject.isaproject.Authentification.JwtAuthenticationRequest;
import com.isaproject.isaproject.DTO.AddressDTO;
import com.isaproject.isaproject.DTO.PersonUserDTO;
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
public class TestsStudent2 extends  TestRepository{
    @Before
    public void setUp() { super.setUp(); }
    @Before
    public void CreateEntities() { super.SetTestData(); }

    @Test
    @Transactional
    public void testSuccessfulPharmacyAdminLogin() throws Exception {
        JwtAuthenticationRequest loginDTO = new JwtAuthenticationRequest();
        loginDTO.setEmail("pharmacyAdmin@gmail.com");
        loginDTO.setPassword("pharmacyAdminPassword");

        String input = mapToJson(loginDTO);
        String uri = "/api/login";
        mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(input))
                .andExpect(status().is(200));
    }
    @Test
    @Transactional
    public void testSuccessfulGetAllDermatologists() throws Exception {
        JwtAuthenticationRequest loginDTO = new JwtAuthenticationRequest();
        loginDTO.setEmail("admin@gmail.com");
        loginDTO.setPassword("adminPassword");

        String input = mapToJson(loginDTO);
        String uri = "/api/login";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(input)).andReturn();

        UserTokenState userTokenState = mapFromJson(result.getResponse().getContentAsString(), UserTokenState.class);

        String uri2 = "/api/dermatologist";

        mockMvc.perform(MockMvcRequestBuilders.get(uri2).header("token",  userTokenState.getAccessToken()).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.*", notNullValue()))
                .andExpect(jsonPath("$", isA(LinkedHashMap.class)));
    }
    @Test
    @Transactional
    public void testGetEmployedPharmacists() throws Exception {
        JwtAuthenticationRequest loginDTO = new JwtAuthenticationRequest();
        loginDTO.setEmail("pharmacyAdmin@gmail.com");
        loginDTO.setPassword("pharmacyAdminPassword");

        String input = mapToJson(loginDTO);
        String uri = "/api/login";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(input)).andReturn();

        UserTokenState userTokenState = mapFromJson(result.getResponse().getContentAsString(), UserTokenState.class);

        String uri2 = "/api/pharmacyAdmin/pharmacists";

        mockMvc.perform(MockMvcRequestBuilders.get(uri2).header("token",  userTokenState.getAccessToken()).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.*", notNullValue()))
                .andExpect(jsonPath("$", isA(LinkedHashMap.class)));
    }




}
