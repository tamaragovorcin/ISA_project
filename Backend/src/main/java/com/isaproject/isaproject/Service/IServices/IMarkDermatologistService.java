package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.Model.Users.MarkDermatologist;
import com.isaproject.isaproject.Model.Users.MarkMedication;

import java.util.List;

public interface IMarkDermatologistService {
    MarkDermatologist findById(Integer id);
    List<MarkDermatologist> findAll ();
    MarkDermatologist save(MarkDermatologist markDTO);
    void delete(MarkDermatologist mark);
    MarkDermatologist update(MarkDermatologist markDTO);
}
