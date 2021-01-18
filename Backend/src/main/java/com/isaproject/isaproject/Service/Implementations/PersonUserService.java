package com.isaproject.isaproject.Service.Implementations;
import java.util.List;

import com.isaproject.isaproject.DTO.AddressDTO;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Users.Address;
import com.isaproject.isaproject.Model.Users.Authority;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Repository.PersonUserRepository;
import com.isaproject.isaproject.Service.IServices.IAuthorityService;
import com.isaproject.isaproject.Service.IServices.IPersonUserService;
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

}