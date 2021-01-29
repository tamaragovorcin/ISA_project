package com.isaproject.isaproject.Controller;
import com.isaproject.isaproject.DTO.MedicationsInOrderReviewDTO;
import com.isaproject.isaproject.DTO.OfferDTO;
import com.isaproject.isaproject.DTO.OfferUpdateDTO;
import com.isaproject.isaproject.DTO.SupplierOffersInfoDTO;
import com.isaproject.isaproject.Model.Orders.MedicationInOrder;
import com.isaproject.isaproject.Model.Orders.Offer;
import com.isaproject.isaproject.Model.Orders.Order;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.Supplier;
import com.isaproject.isaproject.Service.Implementations.OfferService;
import com.isaproject.isaproject.Service.Implementations.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/offer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OfferController {
    @Autowired
    OfferService offerService;
    @Autowired
    SupplierService supplierService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('SUPPLIER')")
    ResponseEntity<Offer> register(@RequestBody OfferDTO offerDTO)
    {
        Offer offer = offerService.save(offerDTO);
        return offer == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(offer);
    }
    @PostMapping("/update")
    @PreAuthorize("hasRole('SUPPLIER')")
    ResponseEntity<Offer> updateOffer(@RequestBody OfferUpdateDTO offerUpdateDto)
    {
        Offer offer = offerService.update(offerUpdateDto);
        return offer == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(offer);
    }

    @GetMapping("/myActiveOffers")
    @PreAuthorize("hasRole('SUPPLIER')")
    ResponseEntity<List<SupplierOffersInfoDTO>> getMyActiveOffers()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        Supplier supplier = supplierService.findById(user.getId());
        List<SupplierOffersInfoDTO> supplierOffersDto = getSupplierOffersInfoDTOS(supplier);

        return supplierOffersDto == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(supplierOffersDto);
    }

    @GetMapping("/myActiveOffers/{offerStatus}")
    @PreAuthorize("hasRole('SUPPLIER')")
    ResponseEntity<List<SupplierOffersInfoDTO>> getMyActiveOffersByStatus(@PathVariable String offerStatus)
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        Supplier supplier = supplierService.findById(user.getId());
        List<SupplierOffersInfoDTO> supplierOffersDto = getSupplierOffersInfoDTOSByStatus(supplier,offerStatus);

        return supplierOffersDto == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(supplierOffersDto);
    }

    private List<SupplierOffersInfoDTO> getSupplierOffersInfoDTOSByStatus(Supplier supplier, String offerStatus) {
        List<SupplierOffersInfoDTO> supplierOffersDto = new ArrayList<>();
        Set<Offer> offers =  supplier.getOffer();
        for (Offer offer: offers) {
            if(offer.getStatus().equals(offerStatus)) {
                Order order = offer.getOrder();
                if(order.getDate().isAfter(LocalDate.now()))
                {
                    supplierOffersDto.add(new SupplierOffersInfoDTO(offer.getId(), order.getId(), offer.getDateOfDelivery(), offer.getSummaryPrice(),
                            order.getDate(), getMedicationsInOrder(order.getMedicationInOrders()), order.getPharmacyAdmin().getPharmacy().getPharmacyName(),true));
                }
                else {
                    supplierOffersDto.add(new SupplierOffersInfoDTO(offer.getId(), order.getId(), offer.getDateOfDelivery(), offer.getSummaryPrice(),
                            order.getDate(), getMedicationsInOrder(order.getMedicationInOrders()), order.getPharmacyAdmin().getPharmacy().getPharmacyName(),false));
                }
            }

        }
        return supplierOffersDto;
    }
    private List<SupplierOffersInfoDTO> getSupplierOffersInfoDTOS(Supplier supplier) {
        List<SupplierOffersInfoDTO> supplierOffersDto = new ArrayList<>();
        Set<Offer> offers =  supplier.getOffer();
        for (Offer offer: offers) {
            Order order = offer.getOrder();
            if(order.getDate().isAfter(LocalDate.now()))
            {
                supplierOffersDto.add(new SupplierOffersInfoDTO(offer.getId(), order.getId(), offer.getDateOfDelivery(), offer.getSummaryPrice(),
                        order.getDate(), getMedicationsInOrder(order.getMedicationInOrders()), order.getPharmacyAdmin().getPharmacy().getPharmacyName(),true));
            }
            else {
                supplierOffersDto.add(new SupplierOffersInfoDTO(offer.getId(), order.getId(), offer.getDateOfDelivery(), offer.getSummaryPrice(),
                        order.getDate(), getMedicationsInOrder(order.getMedicationInOrders()), order.getPharmacyAdmin().getPharmacy().getPharmacyName(),false));
            }

        }
        return supplierOffersDto;
    }

    private List<MedicationsInOrderReviewDTO> getMedicationsInOrder(Set<MedicationInOrder> medicationInOrders) {
        List<MedicationsInOrderReviewDTO> medications = new ArrayList<>();
        for (MedicationInOrder medicationInOrder: medicationInOrders) {
            medications.add(new MedicationsInOrderReviewDTO(medicationInOrder.getId(), medicationInOrder.getMedicine().getName(),
                    medicationInOrder.getMedicine().getCode(),medicationInOrder.getMedicine().getForm(),
                    medicationInOrder.getMedicine().getType(),medicationInOrder.getQuantity()));
        }
        return medications;
    }


}
