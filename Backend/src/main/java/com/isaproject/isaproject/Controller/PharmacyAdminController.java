package com.isaproject.isaproject.Controller;
import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.HelpModel.MedicationPrice;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Orders.MedicationInOrder;
import com.isaproject.isaproject.Model.Orders.Order;
import com.isaproject.isaproject.Model.Pharmacy.Actions;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Schedule.HolidaySchedulePharmacist;
import com.isaproject.isaproject.Model.Users.*;
import com.isaproject.isaproject.Service.Implementations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/pharmacyAdmin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PharmacyAdminController {
    @Autowired
    PharmacyAdminService pharmacyAdminService;
    @Autowired
    MedicationPriceService medicationPriceService;
    @Autowired
    OrderService orderService;
    @Autowired
    HolidaySchedulePharmacistService pharmacistHolidayService;

    @PostMapping("/register")
   // @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<String> addUser(@RequestBody PharmacyAdminDTO userRequest) {
        System.out.println(userRequest.getPharmacy().getPharmacyName());

        PersonUser existUser = pharmacyAdminService.findByEmail(userRequest.getEmail());
        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getEmail(), "Email already exists");
        }
        PersonUser user = pharmacyAdminService.save(userRequest);
        return new ResponseEntity<>("Supplier is successfully registred!", HttpStatus.CREATED);
    }

    @GetMapping("/account")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<PharmacyAdmin> getMyAccount()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        return pharmacyAdmin == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyAdmin);
    }
    @PostMapping("/update")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<PharmacyAdmin> update(@RequestBody PharmacyAdminDTO person)
    {
        PharmacyAdmin per = pharmacyAdminService.findByEmail(person.getEmail());
        Integer id = per.getId();
        pharmacyAdminService.delete(per);
        PharmacyAdmin patient = pharmacyAdminService.save(person);
        return patient == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(patient);
    }
    @GetMapping("/myPharmacy")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<Pharmacy> getMyPharmacy()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        return pharmacyAdmin.getPharmacy() == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyAdmin.getPharmacy());
    }
    @GetMapping("/dermatologists")
    //@PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<Set<Dermatologist>> getOurDermatologists()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        return pharmacyAdmin.getPharmacy().getDermatologists() == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyAdmin.getPharmacy().getDermatologists());
    }


    @PostMapping("/dermatologist/remove")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<String> removeDermatologistFromPharmacy(@RequestBody Dermatologist dermatologist)
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        pharmacyAdminService.removeDermatologistFromPharmacy(pharmacyAdmin.getPharmacy().getId(),dermatologist);
        return new ResponseEntity<>("Dermatologist successfully removed from pharmacy!", HttpStatus.ACCEPTED);    }

    @GetMapping("/actions")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<Set<Actions>> getActions()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        return pharmacyAdmin.getPharmacy().getActions() == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyAdmin.getPharmacy().getActions());
    }
    @GetMapping("/pharmacists")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<Set<Pharmacist>> getPharmacists()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        return pharmacyAdmin.getPharmacy().getPharmacists() == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacyAdmin.getPharmacy().getPharmacists());
    }
    @GetMapping("/holidayRequests")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<List<HolidaySchedulePharmacistFrontDTO>> getAllFront()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        List<HolidaySchedulePharmacistFrontDTO> schedulePharmacistFrontDTOS = new ArrayList<HolidaySchedulePharmacistFrontDTO>();
        for(HolidaySchedulePharmacist holiday :  pharmacistHolidayService.findAll()){
            if(holiday.getPharmacist().getPharmacy().getId() == pharmacyAdmin.getPharmacy().getId() && holiday.getApproved().equals("WAIT_FOR_RESPONSE")) {
                HolidaySchedulePharmacistFrontDTO holidaySchedulePharmacistFrontDTO = new HolidaySchedulePharmacistFrontDTO();
                holidaySchedulePharmacistFrontDTO.setPharmacist(holiday.getPharmacist().getName() + " " + holiday.getPharmacist().getSurname());
                holidaySchedulePharmacistFrontDTO.setScheduleId(holiday.getId());
                holidaySchedulePharmacistFrontDTO.setApproved(holiday.getApproved());
                holidaySchedulePharmacistFrontDTO.setStartDate(holiday.getStartDate());
                holidaySchedulePharmacistFrontDTO.setEmail(holiday.getPharmacist().getEmail());
                holidaySchedulePharmacistFrontDTO.setEndDate(holiday.getEndDate());
                holidaySchedulePharmacistFrontDTO.setType(holiday.getType());
                schedulePharmacistFrontDTOS.add(holidaySchedulePharmacistFrontDTO);
            }
        }
        return schedulePharmacistFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(schedulePharmacistFrontDTOS);
    }


    @GetMapping("/medication")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<List<Medication>> getMedication()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();

        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());

        return medicationPriceService.findByPharmacy(pharmacyAdmin.getPharmacy().getId()) == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medicationPriceService.findMedicationByPharmacy(pharmacyAdmin.getPharmacy().getId()));
    }
    @GetMapping("/medicationFront")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<List<MedicationPriceFrontDTO>> getMedicationFront()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();

        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        List<MedicationPriceFrontDTO> medicationPriceFrontDTOS = new ArrayList<MedicationPriceFrontDTO>();
        for(MedicationPrice medicationPrice : medicationPriceService.findByPharmacy(pharmacyAdmin.getPharmacy().getId())){
            MedicationPriceFrontDTO medicationPriceFrontDTO = new MedicationPriceFrontDTO();
            medicationPriceFrontDTO.setName(medicationPrice.getMedication().getName());
            medicationPriceFrontDTO.setPrice(medicationPrice.getPrice());
            medicationPriceFrontDTO.setCode(medicationPrice.getMedication().getCode());
            medicationPriceFrontDTO.setDate(medicationPrice.getDate());
            medicationPriceFrontDTO.setForm(medicationPrice.getMedication().getForm());
            medicationPriceFrontDTO.setManufacturer(medicationPrice.getMedication().getSpecification().getManufacturer());
            medicationPriceFrontDTOS.add(medicationPriceFrontDTO);
        }
        return medicationPriceFrontDTOS == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medicationPriceFrontDTOS);
    }


    @GetMapping("/orders")
    ResponseEntity<List<OrderReviewDTO>> getAllOrders()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        List<Order> orders = orderService.findAll();
        List<OrderReviewDTO> ordersDto = new ArrayList<>();
        for (Order order: orders) {
            if((order.getPharmacyAdmin().getPharmacy().getId() == pharmacyAdmin.getPharmacy().getId())) {
                ordersDto.add(new OrderReviewDTO(order.getId(), order.getDate(), order.getStatus(), getMedicationsInOrder(order.getMedicationInOrders()),
                        order.getPharmacyAdmin().getPharmacy().getPharmacyName()));
            }
        }
        return ordersDto == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(ordersDto);
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