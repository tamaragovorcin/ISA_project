package com.isaproject.isaproject.Service.Implementations;
import java.util.ArrayList;
import java.util.List;

import com.isaproject.isaproject.DTO.AddressDTO;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Users.*;
import com.isaproject.isaproject.Repository.ConfirmationTokenRepository;
import com.isaproject.isaproject.Repository.PersonUserRepository;
import com.isaproject.isaproject.Service.IServices.IAuthorityService;
import com.isaproject.isaproject.Service.IServices.IPersonUserService;
import net.schmizz.sshj.ConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class PersonUserService implements IPersonUserService {

    @Autowired
    private PersonUserRepository personUserRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IAuthorityService authService;


    @Override
    public PersonUser findByEmail(String email) throws UsernameNotFoundException {
        PersonUser u = personUserRepository.findByEmail(email);
        return u;
    }
    @Override
    public PersonUser findById(Integer id) throws AccessDeniedException {
        PersonUser u = personUserRepository.findById(id).orElseGet(null);
        return u;
    }

    public List<PersonUser> findAll() throws AccessDeniedException {
        List<PersonUser> result = personUserRepository.findAll();
        return result;
    }

    @Override
    public PersonUser save(PersonUserDTO userRequest) {
        PersonUser u = new PersonUser();
        u.setEmail(userRequest.getEmail());
        // pre nego sto postavimo lozinku u atribut hesiramo je
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setName(userRequest.getFirstname());
        u.setSurname(userRequest.getSurname());
        u.setEnabled(true);
        Address address = new Address(userRequest.getAddress().getTown(), userRequest.getAddress().getStreet(), userRequest.getAddress().getNumber(), userRequest.getAddress().getPostalCode(), userRequest.getAddress().getCountry());
        u.setAddress(address);
        u.setFirstLogged(true);
        u.setPhoneNumber(userRequest.getPhonenumber());

        Authority auth = authService.findByname("ROLE_USER");
        // u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
        //u.setAuthorities(auth);

        u = this.personUserRepository.save(u);
        return u;
    }

    @Override
    public void delete(PersonUser userRequest) {
        List<ConfirmationToken> confirmationTokens = confirmationTokenRepository.findAll();
        ConfirmationToken confirmationToken = new ConfirmationToken();

        for(ConfirmationToken confirmationToken1 : confirmationTokens){

            if (confirmationToken1.getPersonUser().getId()==userRequest.getId()){
                confirmationTokenRepository.delete(confirmationToken1);
            }

        }

        personUserRepository.delete(userRequest);
    }

    @Override
    public PersonUser update(PersonUserDTO userRequest, Integer id) {
        PersonUser personUser =  new PersonUser();
        personUser.setId(id);
        personUser.setName(userRequest.getFirstname());
        personUser.setSurname(userRequest.getSurname());
        AddressDTO addressDTO = userRequest.getAddress();
        Address address = new Address(addressDTO.getTown(),addressDTO.getStreet(),addressDTO.getNumber(),addressDTO.getPostalCode(),addressDTO.getCountry());
        personUser.setAddress(address);
        personUser.setEmail(userRequest.getEmail());
        personUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        personUser.setFirstLogged(true);

        Authority auth = authService.findByname("ROLE_USER");

        System.out.println("bbbbbbbbbbbbbbbbBBBBBBBBBBBBBBBBBBBBBBBBB " + personUser.getId());
        personUser = this.personUserRepository.save(personUser);
        return personUser;
    }

}