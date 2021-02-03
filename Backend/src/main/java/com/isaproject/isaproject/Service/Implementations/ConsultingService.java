package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.ConsultingDTO;
import com.isaproject.isaproject.Model.Examinations.Consulting;
import com.isaproject.isaproject.Repository.ConsultingRepository;
import com.isaproject.isaproject.Service.IServices.IConsultingService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class ConsultingService implements IConsultingService {

    @Autowired
    ConsultingRepository consultingRepository;

    @Override
    public Consulting findById(Integer id) {
        return consultingRepository.findById(id).get();
    }

    @Override
    public List<Consulting> findAll() {
        return consultingRepository.findAll();
    }

    @Override
    public Consulting save(ConsultingDTO consultingDTO) {


        Consulting consulting = new Consulting();
        consulting.setPharmacist(consultingDTO.getPharmacist());
        consulting.setPatient(consultingDTO.getPatient());
        consulting.setDate(consultingDTO.getDate());
        consulting.setStartTime(consultingDTO.getStartTime());
        consulting.setDuration(consultingDTO.getDuration());
        consulting.setPrice(consultingDTO.getPrice());
        consulting.setCancelled(false);
        consulting.setShowedUp(false);
        consulting.setInformation("");
        return this.consultingRepository.save(consulting);
    }

    @Override
    public Consulting update(Consulting loyaltyProgram) {
        return this.consultingRepository.save(loyaltyProgram);
    }

    @Override
    public Consulting updateIgnored(Consulting loyaltyProgram) {
       Consulting cons = new Consulting();

       cons.setInformation("Patient was ignored this consulting.");
       cons.setShowedUp(false);
       cons.setPrice(0);
       cons.setDuration(0);
       cons.setStartTime(loyaltyProgram.getStartTime());
       cons.setDate(loyaltyProgram.getDate());
       cons.setDuration(loyaltyProgram.getDuration());
       return  null;

    }
}
