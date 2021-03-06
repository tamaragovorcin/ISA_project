package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.PersonUserDTO;
import com.isaproject.isaproject.DTO.PharmacyDTO;
import com.isaproject.isaproject.DTO.WorkingHoursDermatologistDTO;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;

import java.util.List;

public interface IPharmacyService {
    Pharmacy findById(Integer id);
    List<Pharmacy> findAll ();
    Pharmacy save(PharmacyDTO pharmacy);
    Boolean savePharmacy(WorkingHoursDermatologistDTO dto);
    Pharmacy update(Pharmacy pharmacy);

}
