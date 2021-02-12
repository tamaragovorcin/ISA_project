package com.isaproject.isaproject.Controller;
import com.isaproject.isaproject.DTO.MedicationsInOrderReviewDTO;
import com.isaproject.isaproject.DTO.OfferDTO;
import com.isaproject.isaproject.DTO.OfferReviewDTO;
import com.isaproject.isaproject.Model.Orders.Offer;
import com.isaproject.isaproject.Model.Orders.Order;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.PharmacyAdmin;
import com.isaproject.isaproject.Repository.OfferRepository;
import com.isaproject.isaproject.Repository.OrderRepository;
import com.isaproject.isaproject.Service.Implementations.MedicationPriceService;
import com.isaproject.isaproject.Service.Implementations.OfferService;
import com.isaproject.isaproject.Service.Implementations.PharmacyAdminService;
import com.isaproject.isaproject.DTO.OfferUpdateDTO;
import com.isaproject.isaproject.DTO.SupplierOffersInfoDTO;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Medicine.SupplierMedications;
import com.isaproject.isaproject.Model.Orders.MedicationInOrder;
import com.isaproject.isaproject.Model.Users.Supplier;
import com.isaproject.isaproject.Service.Implementations.OrderService;
import com.isaproject.isaproject.Service.Implementations.SupplierMedicationService;
import com.isaproject.isaproject.Service.Implementations.SupplierService;
import com.isaproject.isaproject.Validation.CommonValidatior;
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
import java.time.LocalDate;
import java.util.Set;

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
    @Autowired
    SupplierService supplierService;
    @Autowired
    OrderService orderService;
    @Autowired
    SupplierMedicationService supplierMedicationService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('SUPPLIER')")
    ResponseEntity<Offer> addOffer(@RequestBody OfferDTO offerDTO)
    {
        CommonValidatior commonVlidatior = new CommonValidatior();
        if(!commonVlidatior.checkValidationOffer(offerDTO)) {
            throw new IllegalArgumentException("Please fill in all the fields correctly!");
        }
        Offer offer = offerService.proccedOffer(offerDTO);
        return offer == null  ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(offer);
    }



    @PostMapping("/update")
    @PreAuthorize("hasRole('SUPPLIER')")
    ResponseEntity<Offer> updateOffer(@RequestBody OfferUpdateDTO offerUpdateDto)
    {
        if(offerService.offerCanBeUpdated(offerUpdateDto)) {
            Offer offer = offerService.update(offerUpdateDto);
            return offer == null ?
                    new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                    ResponseEntity.ok(offer);
        }
        return null;
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
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
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
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
                    if (offer.getId() == id && !offer.getOrder().getStatus().equals("CLOSED")) {
                        medicationPriceService.updateMedicineQuantityTender(offer.getOrder());
                        offer.setStatus("ACCEPTED");
                        Order order = offer.getOrder();
                        order.setStatus("CLOSED");
                        this.offerRepository.save(offer);
                        this.orderRepository.save(order);
                        SimpleMailMessage mail = new SimpleMailMessage();
                        mail.setTo(offer.getSupplier().getEmail());
                        mail.setSubject("Tender in " + offer.getOrder().getPharmacyAdmin().getPharmacy().getPharmacyName());
                        mail.setFrom(environment.getProperty("spring.mail.username"));
                        mail.setText("Tender in " + offer.getOrder().getPharmacyAdmin().getPharmacy().getPharmacyName() + " is closed."
                                + " Congratulations, you won tender.");
                        mailSender.send(mail);

                        Set<SupplierMedications> supplierMedications = offer.getSupplier().getSupplierMedications();
                        Set<MedicationInOrder> medicationInOrders = offer.getOrder().getMedicationInOrders();
                        supplierMedicationService.updateMedicineQuantityTenderWon(supplierMedications, medicationInOrders);

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
                        Set<SupplierMedications> supplierMedications = offer.getSupplier().getSupplierMedications();
                        Set<MedicationInOrder> medicationInOrders = offer.getOrder().getMedicationInOrders();
                        supplierMedicationService.updateMedicineQuantityTenderLost(supplierMedications, medicationInOrders);

                    }

                }
            }else{

            }
        }
        if(offerService.findById(id).getStatus().equals("CREATED")){
            return new ResponseEntity<>("Sorry, you don't have permission to accept offer.", HttpStatus.ACCEPTED);

        }
        return new ResponseEntity<>("Accepted!", HttpStatus.ACCEPTED);

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
                if(order.getDate().isAfter(LocalDate.now()) && !order.getStatus().equals("CLOSED"))
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
            if(order.getDate().isAfter(LocalDate.now()) && !order.getStatus().equals("CLOSED") && order.getDate().isAfter(LocalDate.now()))
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