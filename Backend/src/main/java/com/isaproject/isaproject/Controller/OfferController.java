package com.isaproject.isaproject.Controller;
import com.isaproject.isaproject.DTO.OfferDTO;
import com.isaproject.isaproject.Model.Orders.Offer;
import com.isaproject.isaproject.Service.Implementations.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/offer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OfferController {
    @Autowired
    OfferService offerService;


    @PostMapping("/add")
    @PreAuthorize("hasRole('SUPPLIER')")
    ResponseEntity<Offer> register(@RequestBody OfferDTO offerDTO)
    {
        Offer offer = offerService.save(offerDTO);
        return offer == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(offer);
    }



}
