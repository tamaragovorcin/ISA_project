package com.isaproject.isaproject.Service.IServices;
import com.isaproject.isaproject.DTO.OfferDTO;
import com.isaproject.isaproject.Model.Orders.Offer;

import java.util.List;

public interface IOfferService {
    Offer findById(Integer id);
    List<Offer> findAll ();
    Offer save(OfferDTO orderDTO);
    void delete(Offer order);
}
