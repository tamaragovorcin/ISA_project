package com.isaproject.isaproject.Controller;
import com.isaproject.isaproject.DTO.*;
import com.isaproject.isaproject.Exception.ResourceConflictException;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.HelpModel.MedicationPrice;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Orders.MedicationInOrder;
import com.isaproject.isaproject.Model.Orders.Order;
import com.isaproject.isaproject.Model.Pharmacy.Actions;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Schedule.HolidaySchedulePharmacist;
import com.isaproject.isaproject.Model.Users.*;
import com.isaproject.isaproject.Service.Implementations.*;
import com.isaproject.isaproject.Validation.CommonValidatior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    @Autowired
    PharmacistService pharmacistService;
    @Autowired
    DermatologistService dermatologistService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<String> addUser(@RequestBody PharmacyAdminDTO userRequest) {
        CommonValidatior commonVlidatior = new CommonValidatior();
        if(!commonVlidatior.checkValidationPharmacyAdmin(userRequest)) {
            throw new IllegalArgumentException("Please fill in all the fields correctly!");
        }

        if(!userRequest.getPassword().equals(userRequest.getRewritePassword())) {
            throw new IllegalArgumentException("Please make sure your password and rewrite password match!");
        }

        PersonUser existUser = pharmacyAdminService.findByEmail(userRequest.getEmail());
        if (existUser != null) {
            throw new ResourceConflictException("Entered email already exists", "Email already exists");
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

        PharmacyAdmin patient = pharmacyAdminService.update(person);
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
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    List<DermatologistFrontDTO> getOurDermatologists()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        List<DermatologistFrontDTO> dermatologists = new ArrayList<DermatologistFrontDTO>();
        for(Dermatologist derm : pharmacyAdmin.getPharmacy().getDermatologists()){
            DermatologistFrontDTO dermatologistFrontDTO = new DermatologistFrontDTO();
            dermatologistFrontDTO.setFirstname(derm.getName());
            dermatologistFrontDTO.setSurname(derm.getSurname());
            dermatologistFrontDTO.setEmail(derm.getEmail());
            dermatologistFrontDTO.setId(derm.getId());
            dermatologistFrontDTO.setPhonenumber(derm.getPhoneNumber());
            dermatologistFrontDTO.setMarkDermatologist(derm.getMarkDermatologist());
            dermatologists.add(dermatologistFrontDTO);
        }
        return dermatologists;
    }
    @PostMapping("dermatologist/searchName")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<List<DermatologistFrontDTO>> getDermatologistByName(@RequestBody PharmacistSearchDTO dto)
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        List<DermatologistFrontDTO> dermatologists = new ArrayList<DermatologistFrontDTO>();
        for(Dermatologist derm : pharmacyAdmin.getPharmacy().getDermatologists()){
            if(derm.getName().toLowerCase().contains(dto.getFirstName().toLowerCase(Locale.ROOT))&& derm.getSurname().toLowerCase().contains(dto.getSurName().toLowerCase())){
                DermatologistFrontDTO dermatologistFrontDTO = new DermatologistFrontDTO();
                dermatologistFrontDTO.setFirstname(derm.getName());
                dermatologistFrontDTO.setSurname(derm.getSurname());
                dermatologistFrontDTO.setEmail(derm.getEmail());
                dermatologistFrontDTO.setId(derm.getId());
                dermatologistFrontDTO.setPhonenumber(derm.getPhoneNumber());
                dermatologistFrontDTO.setMarkDermatologist(derm.getMarkDermatologist());
                dermatologists.add(dermatologistFrontDTO);
            }

        }
        return dermatologists.equals(Collections.emptyList()) ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(dermatologists);

    }

    @GetMapping("dermatologist/searchMark/{MarkMin}/{MarkMax}")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<List<DermatologistFrontDTO>> getDermatologistByMark(@PathVariable int MarkMin,@PathVariable int MarkMax )
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        List<DermatologistFrontDTO> dermatologists = new ArrayList<DermatologistFrontDTO>();
        for(Dermatologist derm : pharmacyAdmin.getPharmacy().getDermatologists()){
            if(derm.getMarkDermatologist() >= MarkMin && derm.getMarkDermatologist() <= MarkMax){
                DermatologistFrontDTO dermatologistFrontDTO = new DermatologistFrontDTO();
                dermatologistFrontDTO.setFirstname(derm.getName());
                dermatologistFrontDTO.setSurname(derm.getSurname());
                dermatologistFrontDTO.setEmail(derm.getEmail());
                dermatologistFrontDTO.setId(derm.getId());
                dermatologistFrontDTO.setPhonenumber(derm.getPhoneNumber());
                dermatologistFrontDTO.setMarkDermatologist(derm.getMarkDermatologist());
                dermatologists.add(dermatologistFrontDTO);
            }

        }
        return dermatologists.equals(Collections.emptyList()) ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(dermatologists);

    }


    @GetMapping("/dermatologist/remove/{id}")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<String> removeDermatologistFromPharmacy(@PathVariable Integer id)
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        Dermatologist dermatologist =  dermatologistService.findById(id);
        if(pharmacyAdminService.removeDermatologistFromPharmacy(pharmacyAdmin.getPharmacy().getId(),dermatologist)) {
            return new ResponseEntity<>("Dermatologist successfully removed from pharmacy!", HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>("Dermatologist can't be removed, he's got some scheduled examinations!", HttpStatus.ACCEPTED);

        }
    }

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
    @GetMapping("/terms")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<List<TermsFrontDTO>> getExaminationTerms()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        List<TermsFrontDTO> termsList = new ArrayList<>();
        for(ExaminationSchedule examinationSchedule : pharmacyAdmin.getPharmacy().getExaminationSchedules()){
            TermsFrontDTO termsFrontDTO = new TermsFrontDTO(examinationSchedule.getDermatologist().getName(),examinationSchedule.getDermatologist().getSurname(),examinationSchedule.getDate(),examinationSchedule.getStartTime(),examinationSchedule.getDuration(),examinationSchedule.getPrice());
            termsList.add(termsFrontDTO);
        }
        return termsList == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(termsList);
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
        return schedulePharmacistFrontDTOS.equals(Collections.emptyList()) ?
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
            medicationPriceFrontDTO.setId(medicationPrice.getMedication().getId());
            medicationPriceFrontDTO.setQuantity(medicationPrice.getQuantity());
            medicationPriceFrontDTO.setName(medicationPrice.getMedication().getName());
            medicationPriceFrontDTO.setPrice(medicationPrice.getPrice());
            medicationPriceFrontDTO.setCode(medicationPrice.getMedication().getCode());
            medicationPriceFrontDTO.setDate(medicationPrice.getDate());
            medicationPriceFrontDTO.setForm(medicationPrice.getMedication().getForm());
            medicationPriceFrontDTO.setManufacturer(medicationPrice.getMedication().getSpecification().getManufacturer());
            medicationPriceFrontDTOS.add(medicationPriceFrontDTO);
        }
        return medicationPriceFrontDTOS.equals(Collections.emptyList()) ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medicationPriceFrontDTOS);
    }
    @GetMapping("/medicationFront/{searchField}")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<List<MedicationPriceFrontDTO>> getSearchMedication(@PathVariable String searchField)
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();

        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        List<MedicationPriceFrontDTO> medicationPriceFrontDTOS = new ArrayList<MedicationPriceFrontDTO>();
        for(MedicationPrice medicationPrice : medicationPriceService.findByPharmacy(pharmacyAdmin.getPharmacy().getId())){
            if(medicationPrice.getMedication().getName().toLowerCase().equals(searchField.toLowerCase()) || String.valueOf(medicationPrice.getMedication().getCode()).equals(searchField)) {
                MedicationPriceFrontDTO medicationPriceFrontDTO = new MedicationPriceFrontDTO();
                medicationPriceFrontDTO.setId(medicationPrice.getMedication().getId());
                medicationPriceFrontDTO.setQuantity(medicationPrice.getQuantity());
                medicationPriceFrontDTO.setName(medicationPrice.getMedication().getName());
                medicationPriceFrontDTO.setPrice(medicationPrice.getPrice());
                medicationPriceFrontDTO.setCode(medicationPrice.getMedication().getCode());
                medicationPriceFrontDTO.setDate(medicationPrice.getDate());
                medicationPriceFrontDTO.setForm(medicationPrice.getMedication().getForm());
                medicationPriceFrontDTO.setManufacturer(medicationPrice.getMedication().getSpecification().getManufacturer());
                medicationPriceFrontDTOS.add(medicationPriceFrontDTO);
            }
        }
        return medicationPriceFrontDTOS.equals(Collections.emptyList()) ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(medicationPriceFrontDTOS);
    }



    @GetMapping("pharmacist/searchMark/{MarkMin}/{MarkMax}")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<List<Pharmacist>> getAllByMark(@PathVariable int MarkMin,@PathVariable int MarkMax )
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        List<Pharmacist> pharmacists = new ArrayList<>();
        for(Pharmacist pharmacist : pharmacyAdmin.getPharmacy().getPharmacists()){
            if(pharmacist.getMarkPharmacist() >= MarkMin && pharmacist.getMarkPharmacist() <= MarkMax){
                pharmacists.add(pharmacist);
            }
        }
        return pharmacists.equals(Collections.emptyList()) ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacists);
    }
    @PostMapping("pharmacist/searchName")
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    ResponseEntity<List<Pharmacist>> getAllByName(@RequestBody PharmacistSearchDTO dto)
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        List<Pharmacist> pharmacists = new ArrayList<>();
        for(Pharmacist pharmacist : pharmacyAdmin.getPharmacy().getPharmacists()){
            if(pharmacist.getName().toLowerCase().contains(dto.getFirstName().toLowerCase()) && pharmacist.getSurname().toLowerCase().contains(dto.getSurName().toLowerCase())){
                pharmacists.add(pharmacist);
            }
        }
        return pharmacists.equals(Collections.emptyList()) ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(pharmacists);
    }

    @GetMapping("/activeOrders")
    ResponseEntity<List<OrderReviewDTO>> getActiveOrders()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        List<Order> orders = orderService.findAll();
        List<OrderReviewDTO> ordersDto = new ArrayList<>();
        for (Order order: orders) {
            if((order.getPharmacyAdmin().getPharmacy().getId() == pharmacyAdmin.getPharmacy().getId()) && order.getStatus().equals("WAITING_OFFERS")) {
                ordersDto.add(new OrderReviewDTO(order.getId(), order.getDate(), order.getStatus(), getMedicationsInOrder(order.getMedicationInOrders()),
                        order.getPharmacyAdmin().getPharmacy().getPharmacyName()));
            }
        }
        return ordersDto.equals(Collections.emptyList()) ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(ordersDto);
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
        return ordersDto.equals(Collections.emptyList()) ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(ordersDto);
    }

    @GetMapping("/finishedOrders")
    ResponseEntity<List<OrderReviewDTO>> getFinishedOrders()
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        PharmacyAdmin pharmacyAdmin = pharmacyAdminService.findById(user.getId());
        List<Order> orders = orderService.findAll();
        List<OrderReviewDTO> ordersDto = new ArrayList<>();
        for (Order order: orders) {
            if((order.getPharmacyAdmin().getPharmacy().getId() == pharmacyAdmin.getPharmacy().getId()) && order.getStatus().equals("CLOSED")) {
                ordersDto.add(new OrderReviewDTO(order.getId(), order.getDate(), order.getStatus(), getMedicationsInOrder(order.getMedicationInOrders()),
                        order.getPharmacyAdmin().getPharmacy().getPharmacyName()));
            }
        }
        return ordersDto.equals(Collections.emptyList()) ?
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