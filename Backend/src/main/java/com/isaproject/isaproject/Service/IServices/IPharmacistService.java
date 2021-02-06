
package com.isaproject.isaproject.Service.IServices;

import com.isaproject.isaproject.DTO.PharmacistDTO;
import com.isaproject.isaproject.DTO.PharmacyAdminDTO;
import com.isaproject.isaproject.Model.Users.Pharmacist;
import com.isaproject.isaproject.Model.Users.PharmacyAdmin;

import java.util.List;

public interface IPharmacistService {
    Pharmacist findById(Integer id);
    Pharmacist findByEmail(String email);
    List<Pharmacist> findByPharmacy(String name);
    List<Pharmacist> findByMark(int markMin, int markMax);
    List<Pharmacist> findByName(String name, String surname);
    List<Pharmacist> findAll ();
    Pharmacist save(PharmacistDTO userRequest);
    void delete(Pharmacist admin);
    Pharmacist update(Pharmacist pharmacist);
    Pharmacist updateMark(Pharmacist pharmacist);

}
