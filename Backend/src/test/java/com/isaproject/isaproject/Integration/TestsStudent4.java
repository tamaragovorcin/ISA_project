package com.isaproject.isaproject.Integration;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.LinkedHashMap;
import com.isaproject.isaproject.Authentification.JwtAuthenticationRequest;
import com.isaproject.isaproject.DTO.AddressDTO;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Users.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestsStudent4 extends TestRepository {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Before
    public void setUp() { super.setUp(); }
    @Before
    public void CreateEntities() { super.SetTestData(); }

    @Test
    @Transactional
    public void testSuccesfulSupplierLogin() throws Exception {
        JwtAuthenticationRequest loginDTO = new JwtAuthenticationRequest();
        loginDTO.setEmail("supplier@gmail.com");
        loginDTO.setPassword("supplierPassword");

        String input = mapToJson(loginDTO);
        String uri = "/api/login";
        mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(input))
                .andExpect(status().is(200));
    }

    @Test
    @Transactional
    public void testSuccesfullPatientRegistration() throws Exception {
        PersonUserDTO userDTO = new PersonUserDTO();
        userDTO.setSurname("Surname");
        userDTO.setPassword("password");
        userDTO.setFirstname("Name");
        userDTO.setAddress(new AddressDTO("Town","Street",453,5435,"Country"));
        userDTO.setEmail("user@gmail.com");
        userDTO.setPhonenumber("78912");

        String input = mapToJson(userDTO);
        String uri = "/api/patient/register";
        mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(input))
                .andExpect(status().is(200));
    }

    @Test
    @Transactional
    public void testGetSupplierProfileInformation() throws Exception {

        JwtAuthenticationRequest loginDTO = new JwtAuthenticationRequest();
        loginDTO.setEmail("supplier@gmail.com");
        loginDTO.setPassword("supplierPassword");

        String input = mapToJson(loginDTO);
        String uri = "/api/login";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(input)).andReturn();

        UserTokenState userTokenState = mapFromJson(result.getResponse().getContentAsString(), UserTokenState.class);

        String uri2 = "/api/supplier/account";

        mockMvc.perform(MockMvcRequestBuilders.get(uri2).header("token",  userTokenState.getAccessToken()).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", notNullValue()))
                .andExpect(status().is(200));
    }

    @Test
    @Transactional
    public void testSuccesfulGetLoyaltyProgram() throws Exception {
        JwtAuthenticationRequest loginDTO = new JwtAuthenticationRequest();
        loginDTO.setEmail("admin@gmail.com");
        loginDTO.setPassword("adminPassword");

        String input = mapToJson(loginDTO);
        String uri = "/api/login";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(input)).andReturn();

        UserTokenState userTokenState = mapFromJson(result.getResponse().getContentAsString(), UserTokenState.class);

        String uri2 = "/api/loyaltyProgram";

        mockMvc.perform(MockMvcRequestBuilders.get(uri2).header("token",  userTokenState.getAccessToken()).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.*", notNullValue()))
                .andExpect(jsonPath("$", isA(LinkedHashMap.class)));
    }


    @Test
    @Transactional
    public void testSuccesfulSubscriptionToPharmacy() throws Exception {

        JwtAuthenticationRequest loginDTO = new JwtAuthenticationRequest();
        loginDTO.setEmail("patient@gmail.com");
        loginDTO.setPassword("patientPassword");

        String input = mapToJson(loginDTO);
        String uri = "/api/login";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(input)).andReturn();

        UserTokenState userTokenState = mapFromJson(result.getResponse().getContentAsString(), UserTokenState.class);

        String input2 = mapToJson(pharmacyIdDTO);
        String uri2 = "/api/patient/subscribeToPharmacy";

        mockMvc.perform(MockMvcRequestBuilders.post(uri2).header("token",  userTokenState.getAccessToken())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(input2))
                .andExpect(status().is(200));
    }
}
