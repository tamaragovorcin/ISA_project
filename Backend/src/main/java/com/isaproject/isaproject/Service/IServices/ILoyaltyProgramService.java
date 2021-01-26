package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.LoyaltyProgramDTO;
import com.isaproject.isaproject.Model.HelpModel.LoyaltyProgram;

import java.util.List;

public interface ILoyaltyProgramService {
    LoyaltyProgram findById(Integer id);
    List<LoyaltyProgram> findAll ();
    LoyaltyProgram save(LoyaltyProgramDTO loyaltyProgramDTO);
    LoyaltyProgram update(LoyaltyProgram loyaltyProgram);
}
