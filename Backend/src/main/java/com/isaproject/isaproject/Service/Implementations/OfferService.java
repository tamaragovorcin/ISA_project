package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.OfferDTO;
import com.isaproject.isaproject.Model.Orders.Offer;
import com.isaproject.isaproject.Repository.MedicationInOrderRepository;
import com.isaproject.isaproject.Repository.OfferRepository;
import com.isaproject.isaproject.Repository.OrderRepository;
import com.isaproject.isaproject.Service.IServices.IOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService implements IOfferService {
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MedicationInOrderRepository medicationInOrderRepository;

    @Override
    public Offer findById(Integer id) {
        return offerRepository.findById(id).get();
    }

    @Override
    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

    @Override
    public Offer save(OfferDTO offerDTO) {
        Offer offer = new Offer();
        offer.setSupplier(offerDTO.getSupplier());
        offer.setOrder(orderRepository.findById(offerDTO.getOrderId()).get());
        offer.setDateOfDelivery(offerDTO.getDateOfDelivery());
        offer.setSummaryPrice(offerDTO.getSummaryPrice());
        offer.setStatus("CREATED");
        return offerRepository.save(offer);
    }

    @Override
    public void delete(Offer order) {

    }
}
