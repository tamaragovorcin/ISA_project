package com.isaproject.isaproject.Controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Service.IServices.IPersonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonUserController {

    @Autowired
    private IPersonUserService userService;

    // Za pristup ovoj metodi neophodno je da ulogovani korisnik ima ADMIN ulogu
    // Ukoliko nema, server ce vratiti gresku 403 Forbidden
    // Korisnik jeste autentifikovan, ali nije autorizovan da pristupi resursu
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public PersonUser loadById(@PathVariable Integer userId) {
        return this.userService.findById(userId);
    }

    @GetMapping("/user/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<PersonUser> loadAll() {
        return this.userService.findAll();
    }

    @GetMapping("/whoami")
    @PreAuthorize("hasRole('USER')")
    public PersonUser user(Principal user) {
        return this.userService.findByEmail(user.getName());
    }

    @GetMapping("/foo")
    public Map<String, String> getFoo() {
        Map<String, String> fooObj = new HashMap<>();
        fooObj.put("foo", "bar");
        return fooObj;
    }
}