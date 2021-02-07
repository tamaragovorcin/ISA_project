package com.isaproject.isaproject.Service.Implementations;
import com.isaproject.isaproject.DTO.OfferDTO;
import com.isaproject.isaproject.DTO.OfferUpdateDTO;
import com.isaproject.isaproject.Model.Orders.Offer;
import com.isaproject.isaproject.Repository.MedicationInOrderRepository;
import com.isaproject.isaproject.Repository.OfferRepository;
import com.isaproject.isaproject.Repository.OrderRepository;
import com.isaproject.isaproject.Service.IServices.IOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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
        offer.setSupplier(offerDTO.getSupplier());
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

    @Transactional(readOnly = false)
    public Offer proccedOffer(OfferDTO offerDTO) {
        Boolean quantitiesUpdated = supplierMedicationService.updateQuantities(offerDTO.getOrderId());
        Offer offer = save(offerDTO);
        if(offer == null || quantitiesUpdated==false) {return null;}
        return offer;
    }
}
