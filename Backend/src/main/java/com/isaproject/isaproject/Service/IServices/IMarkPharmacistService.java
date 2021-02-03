package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.Model.Users.MarkMedication;
import com.isaproject.isaproject.Model.Users.MarkPharmacist;

import java.util.List;

public interface IMarkPharmacistService {

    MarkPharmacist findById(Integer id);
    List<MarkPharmacist> findAll ();
    MarkPharmacist save(MarkPharmacist markDTO);
    void delete(MarkPharmacist mark);
    MarkPharmacist update(MarkPharmacist markDTO);
}
