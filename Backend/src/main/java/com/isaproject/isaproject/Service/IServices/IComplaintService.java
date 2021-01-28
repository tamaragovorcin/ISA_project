package com.isaproject.isaproject.Service.IServices;
import com.isaproject.isaproject.DTO.ComplaintDTO;
import com.isaproject.isaproject.Model.HelpModel.Complaint;

import java.util.List;

public interface IComplaintService {
    Complaint findById(Integer id);
    List<Complaint> findAll ();
    Complaint save(ComplaintDTO complaintDTO);
}
