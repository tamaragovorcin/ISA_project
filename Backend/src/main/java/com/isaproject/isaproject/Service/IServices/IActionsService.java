package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.ActionsDTO;
import com.isaproject.isaproject.Model.Pharmacy.Actions;
import com.isaproject.isaproject.Model.Users.Patient;

import java.util.List;

public interface IActionsService {
    Actions findById(Integer id);
    List<Actions> findAll ();
    Actions save(ActionsDTO userRequest);
}
