package com.isaproject.isaproject.Service.Implementations;
import com.isaproject.isaproject.DTO.OfferDTO;
import com.isaproject.isaproject.DTO.OfferUpdateDTO;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Medicine.SupplierMedications;
import com.isaproject.isaproject.Model.Orders.MedicationInOrder;
import com.isaproject.isaproject.Model.Orders.Offer;
import com.isaproject.isaproject.Model.Orders.Order;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Model.Users.Supplier;
import com.isaproject.isaproject.Repository.MedicationInOrderRepository;
import com.isaproject.isaproject.Repository.OfferRepository;
import com.isaproject.isaproject.Repository.OrderRepository;
import com.isaproject.isaproject.Repository.SupplierRepository;
import com.isaproject.isaproject.Service.IServices.IOfferService;
import org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class OfferService implements IOfferService {
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MedicationInOrderRepository medicationInOrderRepository;
    @Autowired
    SupplierMedicationService supplierMedicationService;
    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public Offer findById(Integer id) {
        return offerRepository.findById(id).get();
    }

    @Override
    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Offer save(OfferDTO offerDTO) {
        Offer offer = new Offer();
        offer.setSupplier(supplierRepository.findById(offerDTO.getSupplier()).get());
        offer.setOrder(orderRepository.findById(offerDTO.getOrderId()).get());
        offer.setDateOfDelivery(offerDTO.getDateOfDelivery());
        offer.setSummaryPrice(offerDTO.getSummaryPrice());
        offer.setStatus("CREATED");
        return offerRepository.save(offer);
    }

    @Override
    public void delete(Offer order) { }

    public Offer update(OfferUpdateDTO offerUpdateDto) {

        Offer offer = findById(offerUpdateDto.getOfferId());
        offer.setDateOfDelivery(offerUpdateDto.getDateOfDelivery());
        offer.setSummaryPrice(offerUpdateDto.getSummaryPrice());
        return offerRepository.save(offer);
    }

    public Offer proccedOffer(OfferDTO offerDTO) {
        if(canMakeOffer(offerDTO))  {
            Boolean quantitiesUpdated = supplierMedicationService.updateQuantities(offerDTO.getOrderId());
            Offer offer = save(offerDTO);
            Boolean canMakeOffer = canMakeOffer(offerDTO);
            if(offer == null || quantitiesUpdated==false || canMakeOffer==false) {return null;}
            return offer;
        }
        return null;

    }

    private Boolean canMakeOffer(OfferDTO offerDTO) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        Supplier supplier = supplierRepository.findById(user.getId()).get();

        if(!offerDTO.getDateOfDelivery().isAfter(LocalDate.now()) || offerDTO.getDateOfDelivery()==null){
            throw new IllegalArgumentException("Date of delievery must be in the future!");
        }

        try {
            supplierRepository.findById(offerDTO.getSupplier()).get();
        }
        catch(Exception e) {
            throw new IllegalArgumentException("Supplier with given id does not exist!");
        }

        if(supplier.getId()!=offerDTO.getSupplier()) {
            throw new IllegalArgumentException("Only logged supplier can make offer!");
        }
        Order order;
        try {
            order = orderRepository.findById(offerDTO.getOrderId()).get();
        }
        catch(Exception e) {
            throw new IllegalArgumentException("Order no longer exists!");
        }

        Set<Offer> offers = order.getOffer();
        for (Offer offer:offers) {
            if(supplier.getId()==offer.getSupplier().getId()) {
                throw new IllegalArgumentException("You have already created offer for this tender!");
            }
        }
        for(MedicationInOrder medication : order.getMedicationInOrders()) {
            if(doesSupplierHaveMedication(medication.getMedicine(), medication.getQuantity(), supplier.getSupplierMedications())) {}
            else {
                throw new IllegalArgumentException("Sorry. You cant make offer for this tender. You dont have enough medications.");
            }
        }
        if(!order.getDate().isAfter(LocalDate.now()) || order.getStatus().equals("CLOSED")) {
            throw new IllegalArgumentException("Creating offer is not possible. Order is CLOSED.");
        }

        return true;
    }

    private boolean doesSupplierHaveMedication(Medication medication, int quantity, Set<SupplierMedications> supplierMedications) {
        for (SupplierMedications supplierMedication: supplierMedications) {
            if(supplierMedication.getCode()==medication.getCode() && supplierMedication.getName().equals(medication.getName())) {
                if(supplierMedication.getQuantity()>quantity) { return true;}
            }
        }
        return false;
    }


    public boolean offerCanBeUpdated(OfferUpdateDTO offerUpdateDto) {
        Offer offer;
        try {
            offer = findById(offerUpdateDto.getOfferId());
        }
        catch(Exception e) {
            throw new IllegalArgumentException("Offer with thid id does not exist.");
        }
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        Supplier supplier = supplierRepository.findById(user.getId()).get();

        if(offer.getSupplier().getId()!=supplier.getId()) {
            throw new IllegalArgumentException("You can not update offer from another supplier.");
        }

        Order order = offer.getOrder();
        if(order.getStatus().equals("CLOSED")) {
            throw new IllegalArgumentException("Updating offer is not possible. Order is CLOSED.");
        }
        if(!order.getDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Updating offer is not possible. Order is CLOSED.");
        }
        if(offerUpdateDto.getDateOfDelivery()==null) {
           throw new IllegalArgumentException("Date of delievery must be entered!");
        }
        if(!offerUpdateDto.getDateOfDelivery().isAfter(LocalDate.now()) || offerUpdateDto.getDateOfDelivery()==null){
            throw new IllegalArgumentException("Date of delievery must be in the future!");
        }
        return true;
    }
}
