package com.isaproject.isaproject.Service.IServices;
import com.isaproject.isaproject.DTO.QRcodeInformationDTO;
import com.isaproject.isaproject.Model.Examinations.EPrescription;
import java.util.List;

public interface IEPrescriptionService {
    EPrescription findById(Integer id);
    List<EPrescription> findAll ();
    EPrescription save(List<QRcodeInformationDTO> qRcodeInformationDTOS);
}
