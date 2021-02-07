package com.isaproject.isaproject.Config;

import com.isaproject.isaproject.Authentification.RestAuthenticationEntryPoint;
import com.isaproject.isaproject.Authentification.TokenAuthenticationFilter;
import com.isaproject.isaproject.Authentification.TokenUtils;
import com.isaproject.isaproject.Service.Implementations.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
// Ukljucivanje podrske za anotacije "@Pre*" i "@Post*" koje ce aktivirati autorizacione provere za svaki pristup metodi
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // Implementacija PasswordEncoder-a koriscenjem BCrypt hashing funkcije.
    // BCrypt po defalt-u radi 10 rundi hesiranja prosledjene vrednosti.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Servis koji se koristi za citanje podataka o korisnicima aplikacije
    @Autowired
    private CustomUserDetailsService jwtUserDetailsService;

    // Handler za vracanje 401 kada klijent sa neodogovarajucim korisnickim imenom i lozinkom pokusa da pristupi resursu
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    // Registrujemo authentication manager koji ce da uradi autentifikaciju korisnika za nas
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // Definisemo uputstvo za authentication managera koji servis da koristi da izvuce podatke o korisniku koji zeli da se autentifikuje,
    //kao i kroz koji enkoder da provuce lozinku koju je dobio od klijenta u zahtevu da bi adekvatan hash koji dobije kao rezultat bcrypt algoritma uporedio sa onim koji se nalazi u bazi (posto se u bazi ne cuva plain lozinka)
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    // Injektujemo implementaciju iz TokenUtils klase kako bismo mogli da koristimo njene metode za rad sa JWT u TokenAuthenticationFilteru
    @Autowired
    private TokenUtils tokenUtils;

    // Definisemo prava pristupa odredjenim URL-ovima
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // komunikacija izmedju klijenta i servera je stateless posto je u pitanju REST aplikacija
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                // sve neautentifikovane zahteve obradi uniformno i posalji 401 gresku
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()

                // svim korisnicima dopusti da pristupe putanjama /auth/**, (/h2-console/** ako se koristi H2 baza) i /api/foo
                .authorizeRequests().antMatchers("/api/**").permitAll().antMatchers("/h2-console/**")
                .permitAll().antMatchers("/api/foo").permitAll()
                //.antMatchers("/api/patient/register") .permitAll()
              //  .antMatchers("/api/patient").permitAll()
            //    .antMatchers("/api/patient/email/{id}").permitAll()
              //  .antMatchers("/api/medication/add").permitAll()
               // .antMatchers("/api/medication").permitAll()
                .antMatchers("/api/pharmacy/addActions").permitAll()
                .antMatchers("/api/pharmacy/add").permitAll()
                .antMatchers("/api/pharmacy/actions/{id}").permitAll()
               // .antMatchers("/api/patient/confirm-account").permitAll()
               // .antMatchers("/api/systemAdmin/register") .permitAll()

                //.antMatchers("/api/supplier/register") .permitAll()
                .antMatchers("/api/pharmacist/register") .permitAll()
               // .antMatchers("/api/dermatologist/register") .permitAll()
                .antMatchers("/api/medicationReservation/add") .permitAll()
              //  .antMatchers("/api/pharmacyAdmin/register") .permitAll()
                .antMatchers("/api/pharmacy/addExamination") .permitAll()
                .antMatchers("/api/pharmacy/{id}") .permitAll()
                .antMatchers("/api/order/add") .permitAll()
                .antMatchers("/api/pharmacy/dermatologists/{id}") .permitAll()
                .antMatchers("/api/pharmacy/pharmacists/{id}") .permitAll()

                .antMatchers("/api/consulting/add") .permitAll()
                .antMatchers("/api/dermatologist/examination") .permitAll()
                .antMatchers("/api/pharmacy/medication/{id}") .permitAll()
                .antMatchers("/api/pharmacy/freeExaminationTerms/{id}") .permitAll()
                .antMatchers("/api/medication/priceInPharmacy") .permitAll()

                .antMatchers("/api/consulting/getPharmacies") .permitAll()
               // .antMatchers("/api/pharmacyAdmin/medication") .permitAll()
                .antMatchers("/api/erecipes/availability/pharmacy") .permitAll()
                .antMatchers("/api/erecipes/file/noAuthetication") .permitAll()

                // .antMatchers("/api/pharmacyAdmin/medication") .permitAll()


                .antMatchers("/api/patient/penals") .permitAll()
               //.antMatchers("/api/prescription/add") .permitAll()
                // za svaki drugi zahtev korisnik mora biti autentifikovan
                .anyRequest().authenticated().and()
                .cors().and()

                // umetni custom filter TokenAuthenticationFilter kako bi se vrsila provera JWT tokena umesto cistih korisnickog imena i lozinke (koje radi BasicAuthenticationFilter)
                .addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService),
                        BasicAuthenticationFilter.class);
        // zbog jednostavnosti primera
        http.csrf().disable();
    }

    // Generalna bezbednost aplikacije
    @Override
    public void configure(WebSecurity web) throws Exception {
        // TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
      //  web.ignoring().antMatchers(HttpMethod.POST, "/api/login");
       // web.ignoring().antMatchers(HttpMethod.POST, "/api/patient/register");
      //  web.ignoring().antMatchers(HttpMethod.POST, "/api/supplier/register");
      //  web.ignoring().antMatchers(HttpMethod.GET, "/api/patient");
      //  web.ignoring().antMatchers(HttpMethod.GET, "/api/patient/email/{id}");
      //  web.ignoring().antMatchers(HttpMethod.POST, "/api/medication/add");
     //   web.ignoring().antMatchers(HttpMethod.GET, "/api/medication");
        web.ignoring().antMatchers(HttpMethod.GET, "/api/pharmacy/addActions");
     //   web.ignoring().antMatchers(HttpMethod.GET, "/api/pharmacy/add");
        web.ignoring().antMatchers(HttpMethod.GET, "/api/pharmacy/actions/{id}");
     //   web.ignoring().antMatchers(HttpMethod.POST, "/api/patient/confirm-account");
     //   web.ignoring().antMatchers(HttpMethod.POST, "/api/systemAdmin/register");
        web.ignoring().antMatchers(HttpMethod.POST, "/api/medicationReservation/add");
   //     web.ignoring().antMatchers(HttpMethod.POST, "/api/dermatologist/register");
        web.ignoring().antMatchers(HttpMethod.POST, "/api/pharmacist/register");

     //   web.ignoring().antMatchers(HttpMethod.POST, "/api/pharmacyAdmin/register");
        web.ignoring().antMatchers(HttpMethod.POST, "/api/pharmacy/addExamination");
        web.ignoring().antMatchers(HttpMethod.GET, "/api/pharmacy/{id}");
        web.ignoring().antMatchers(HttpMethod.POST, "/api/order/add");
        web.ignoring().antMatchers(HttpMethod.GET, "/api/pharmacy/pharmacists/{id}");
        web.ignoring().antMatchers(HttpMethod.GET, "/api/pharmacy/dermatologists/{id}");
        web.ignoring().antMatchers(HttpMethod.POST, "/api/erecipes/availability/pharmacy");
        web.ignoring().antMatchers(HttpMethod.POST, "/api/erecipes/file/noAuthetication");
        web.ignoring().antMatchers(HttpMethod.GET, "/api/pharmacy/medication/{id}");
        web.ignoring().antMatchers(HttpMethod.POST, "/api/medication/priceInPharmacy");
        web.ignoring().antMatchers(HttpMethod.POST, "/api/pharmacy/freeExaminationTerms/{id}");

        web.ignoring().antMatchers(HttpMethod.POST, "/api/consulting/getPharmacies");

        //  web.ignoring().antMatchers(HttpMethod.GET, "/api/pharmacyAdmin/medication");

        web.ignoring().antMatchers(HttpMethod.GET, "/api/patient/penals");
        web.ignoring().antMatchers(HttpMethod.GET, "/api/dermatologist/examination");
        //web.ignoring().antMatchers(HttpMethod.POST, "/api/prescription/add");


        web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
                "/**/*.css", "/**/*.js");
    }

}