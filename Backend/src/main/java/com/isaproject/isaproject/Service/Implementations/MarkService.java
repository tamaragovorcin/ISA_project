package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.MarkDTO;
import com.isaproject.isaproject.Model.Examinations.ExaminationSchedule;
import com.isaproject.isaproject.Model.Users.Mark;
import com.isaproject.isaproject.Repository.MarkRepository;
import com.isaproject.isaproject.Service.IServices.IMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MarkService implements IMarkService {
    @Autowired
    MarkRepository markRepository;


    @Override
    public Mark findById(Integer id) {
        return null;
    }

    @Override
    public List<Mark> findAll() {

            return markRepository.findAll();


    }


    @Override
    public Mark save(Mark markDTO) {

        return markRepository.save(markDTO);
    }

    @Override
    public void delete(Mark mark) {

    }

    @Override
    public Mark update(Mark markDTO) {
        Mark mark= findById(markDTO.getId());

        return null;
    }
}
