package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.Model.HelpModel.LoyaltyProgram;

import java.util.List;

public interface ILoyaltyProgramService {
    LoyaltyProgram findById(Integer id);
    List<LoyaltyProgram> findAll ();
    LoyaltyProgram save(LoyaltyProgram loyaltyProgramDTO);
}
