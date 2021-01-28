package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.ConsultingDTO;
import com.isaproject.isaproject.Model.Examinations.Consulting;


import java.util.List;

public interface IConsultingService {

    Consulting findById(Integer id);
    List<Consulting> findAll ();
    Consulting save(ConsultingDTO loyaltyProgramDTO);
    Consulting update(Consulting loyaltyProgram);
}
