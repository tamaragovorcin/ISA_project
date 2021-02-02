package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.MarkDTO;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.Model.Users.Mark;
import com.isaproject.isaproject.Model.Users.Patient;

import java.util.List;

public interface IMarkService {

    Mark findById(Integer id);
    List<Mark> findAll ();
    Mark save(Mark markDTO);
    void delete(Mark mark);
    Mark update(Mark markDTO);
}
