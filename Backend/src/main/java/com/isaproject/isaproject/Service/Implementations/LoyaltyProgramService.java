package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.Model.HelpModel.LoyaltyProgram;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Repository.LoyaltyProgramRepository;
import com.isaproject.isaproject.Repository.PatientRepository;
import com.isaproject.isaproject.Service.IServices.ILoyaltyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.ObjectInputFilter;
import java.util.List;


@Service
public class LoyaltyProgramService implements ILoyaltyProgramService {
    @Autowired
    LoyaltyProgramRepository loyaltyProgramRepository;
    @Autowired
    PatientRepository patientRepository;

    @Override
    public LoyaltyProgram findById(Integer id) {
        return loyaltyProgramRepository.findById(id).get();
    }

    @Override
    public List<LoyaltyProgram> findAll() {
        return loyaltyProgramRepository.findAll();
    }

    @Override
    public LoyaltyProgram save(LoyaltyProgram loyaltyProgram) {
        return this.loyaltyProgramRepository.save(loyaltyProgram);
    }
    public void updatePatientsLoyaltyPoints(int points) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();

        Patient patient = patientRepository.getOne(user.getId());
        patient.setPoints(patient.getPoints()+points);
        String status = patient.getLoyaltyCategory();
        try {
            LoyaltyProgram loyaltyProgram = findAll().get(0);
            if(status.equals("REGULAR")) {
                if(patient.getPoints()>=loyaltyProgram.getSilverLimit()) {
                    patient.setDiscount(loyaltyProgram.getSilverDiscount());
                    patient.setLoyaltyCategory("SILVER");
                }
            }
            else if(status.equals("SILVER")) {
                if(patient.getPoints()>=loyaltyProgram.getGoldLimit()) {
                    patient.setDiscount(loyaltyProgram.getGoldenDiscount());
                    patient.setLoyaltyCategory("GOLD");
                }
            }
            patientRepository.save(patient);
        }
        catch(Exception e ) {}

    }


}
