package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.LoyaltyProgramDTO;
import com.isaproject.isaproject.Model.HelpModel.LoyaltyProgram;
import com.isaproject.isaproject.Repository.LoyaltyProgramRepository;
import com.isaproject.isaproject.Repository.MedicationReservationRepository;
import com.isaproject.isaproject.Service.IServices.ILoyaltyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LoyaltyProgramService implements ILoyaltyProgramService {
    @Autowired
    LoyaltyProgramRepository loyaltyProgramRepository;

    @Override
    public LoyaltyProgram findById(Integer id) {
        return loyaltyProgramRepository.findById(id).get();
    }

    @Override
    public List<LoyaltyProgram> findAll() {
        return loyaltyProgramRepository.findAll();
    }

    @Override
    public LoyaltyProgram save(LoyaltyProgramDTO loyaltyProgramDTO) {
        LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
        loyaltyProgram.setConsultingPoints(loyaltyProgramDTO.getConsultingPoints());
        loyaltyProgram.setExaminationPoints(loyaltyProgramDTO.getExaminationPoints());
        loyaltyProgram.setSilverLimit(loyaltyProgramDTO.getSilverLimit());
        loyaltyProgram.setSilverDiscount(loyaltyProgramDTO.getSilverDiscount());

        loyaltyProgram.setRegularLimit(loyaltyProgramDTO.getRegularLimit());
        loyaltyProgram.setRegularDiscount(loyaltyProgramDTO.getRegularDiscount());
        loyaltyProgram.setGoldenDiscount(loyaltyProgramDTO.getGoldenDiscount());
        loyaltyProgram.setGoldLimit(loyaltyProgramDTO.getGoldLimit());
        return this.loyaltyProgramRepository.save(loyaltyProgram);
    }

    @Override
    public LoyaltyProgram update(LoyaltyProgram loyaltyProgram) {
        return this.loyaltyProgramRepository.save(loyaltyProgram);
    }
}
