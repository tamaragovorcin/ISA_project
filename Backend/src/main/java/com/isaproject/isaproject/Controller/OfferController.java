package com.isaproject.isaproject.Controller;
import com.isaproject.isaproject.DTO.OfferDTO;
import com.isaproject.isaproject.DTO.OfferReviewDTO;
import com.isaproject.isaproject.Model.Orders.Offer;
import com.isaproject.isaproject.Model.Orders.Order;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.PharmacyAdmin;
import com.isaproject.isaproject.Repository.OfferRepository;
import com.isaproject.isaproject.Repository.OrderRepository;
import com.isaproject.isaproject.Service.Implementations.MedicationPriceService;
import com.isaproject.isaproject.Service.Implementations.OfferService;
import com.isaproject.isaproject.Service.Implementations.PharmacyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/offer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OfferController {
    @Autowired
    OfferService offerService;
    @Autowired
    PharmacyAdminService pharmacyAdminService;
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    private Environment environment;
    @Autowired
    JavaMailSenderImpl mailSender;
    @Autowired
    MedicationPriceService medicationPriceService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('SUPPLIER')")
    ResponseEntity<Offer> register(@RequestBody OfferDTO offerDTO)
    {
        Offer offer = offerService.save(offerDTO);
        return offer == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(offer);
    }
    @GetMapping("")
    ResponseEntity<List<Offer>> getAll()
    {
        List<Offer> offers = offerService.findAll();
        return offers == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(offers);
    }
    @GetMapping("{id}")
    ResponseEntity<List<Offer>> getByOrderId(@PathVariable Integer id)
    {
        List<Offer> offers = new ArrayList<Offer>();

        for(Offer offer : offerService.findAll()){
            if(offer.getOrder().getId() ==  id){
                offers.add(offer);
            }
        }
        return offers == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(offers);
    }
    @GetMapping("/data/{id}")
    ResponseEntity<OfferReviewDTO> getData(@PathVariable Integer id)
    {
        OfferReviewDTO offerReviewDTO = new OfferReviewDTO();

        for(Offer offer : offerService.findAll()){
            if(offer.getId() ==  id){
                offerReviewDTO.setOfferId(offer.getId());
                offerReviewDTO.setDateOfDelivery(offer.getDateOfDelivery());
                offerReviewDTO.setSummaryPrice(offer.getSummaryPrice());
                offerReviewDTO.setSupplierName(offer.getSupplier().getName());
                offerReviewDTO.setSupplierSurName(offer.getSupplier().getSurname());

            }
        }
        return offerReviewDTO == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(offerReviewDTO);
    }

    @GetMapping("/accept/{id}")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<String> acceptOffer(@PathVariable Integer id)
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        for(Offer offer : offerService.findAll()) {
            if (offer.getOrder().getPharmacyAdmin().getId() == pharmacyAdmin.getId()) {
                if (offer.getOrder().getId() == offerService.findById(id).getOrder().getId()) {
                    if (offer.getId() == id) {
                        offer.setStatus("ACCEPTED");
                        Order order = offer.getOrder();
                        order.setStatus("CLOSED");
                        this.offerRepository.save(offer);
                        this.orderRepository.save(order);
                        SimpleMailMessage mail = new SimpleMailMessage();
                        mail.setTo(offer.getSupplier().getEmail());
                        mail.setSubject("Tender in " + offer.getOrder().getPharmacyAdmin().getPharmacy().getPharmacyName());
                        mail.setFrom(environment.getProperty("spring.mail.username"));
                        mail.setText("Tender in" + offer.getOrder().getPharmacyAdmin().getPharmacy().getPharmacyName() + " is closed."
                                + " Congratulations, you won tender.");
                        mailSender.send(mail);
                        medicationPriceService.updateMedicineQuantityTender(offer.getOrder());
                    } else {
                        offer.setStatus("REFUSED");
                        SimpleMailMessage mail = new SimpleMailMessage();
                        mail.setTo(offer.getSupplier().getEmail());
                        mail.setSubject("Tender in " + offer.getOrder().getPharmacyAdmin().getPharmacy().getPharmacyName());
                        mail.setFrom(environment.getProperty("spring.mail.username"));
                        mail.setText("Tender in" + offer.getOrder().getPharmacyAdmin().getPharmacy().getPharmacyName() + " is closed."
                                + " We are sorry, but this time you didn't win tender.");
                        mailSender.send(mail);
                        this.offerRepository.save(offer);
                    }

                }
            }
        }
        if(offerService.findById(id).getStatus().equals("CREATED")){
            return new ResponseEntity<>("Sorry, you don't have permission to accept offer.", HttpStatus.ACCEPTED);

        }
        return new ResponseEntity<>("Accepted!", HttpStatus.ACCEPTED);

    }



}
