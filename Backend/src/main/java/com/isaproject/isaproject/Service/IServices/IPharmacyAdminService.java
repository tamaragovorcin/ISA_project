package com.isaproject.isaproject.Service.IServices;
import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.DTO.PharmacyAdminDTO;
import com.isaproject.isaproject.Model.Users.PharmacyAdmin;

import java.util.List;

public interface IPharmacyAdminService {
    PharmacyAdmin findById(Integer id);
    PharmacyAdmin findByEmail(String email);
    List<PharmacyAdmin> findAll ();
    PharmacyAdmin save(PharmacyAdminDTO userRequest);
}
