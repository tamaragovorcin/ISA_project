package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.ComplaintDTO;
import com.isaproject.isaproject.DTO.ComplaintReviewDTO;
import com.isaproject.isaproject.Model.HelpModel.Complaint;
import com.isaproject.isaproject.Repository.ComplaintRepository;
import com.isaproject.isaproject.Repository.DermatologistRepository;
import com.isaproject.isaproject.Repository.PharmacistRepository;
import com.isaproject.isaproject.Repository.PharmacyRepository;
import com.isaproject.isaproject.Service.IServices.IComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService implements IComplaintService {
    @Autowired
    ComplaintRepository complaintRepository;
    @Autowired
    DermatologistRepository dermatologistRepository;
    @Autowired
    PharmacyRepository pharmacyRepository;
    @Autowired
    PharmacistRepository pharmacistRepository;

    @Autowired
    JavaMailSenderImpl mailSender;
    @Autowired
    private Environment environment;

    @Override
    public Complaint findById(Integer id) {
        return complaintRepository.findById(id).get();
    }

    @Override
    public List<Complaint> findAll() {
        return complaintRepository.findAll();
    }

    @Override
    public Complaint save(ComplaintDTO complaintDTO) {
        Complaint complaint = new Complaint();
        complaint.setAnswered(complaintDTO.isAnswered());
        complaint.setSubject(complaintDTO.getSubject());
        complaint.setMassage(complaintDTO.getMassage());
        complaint.setAnswer(complaintDTO.getAnswer());
        complaint.setPatient(complaintDTO.getPatient());

        if(complaintDTO.getDermatologist()==null) complaint.setDermatologist(null);
        else complaint.setDermatologist(dermatologistRepository.findByEmail(complaintDTO.getDermatologist().getEmail()));

        if(complaintDTO.getPharmacist()==null) complaint.setPharmacist(null);
        else complaint.setPharmacist(pharmacistRepository.findByEmail(complaintDTO.getPharmacist().getEmail()));

        if(complaintDTO.getPharmacyName()==null) complaint.setPharmacy(null);
        else complaint.setPharmacy(pharmacyRepository.findByPharmacyName(complaintDTO.getPharmacyName().getPharmacyName()));

        return complaintRepository.save(complaint);
    }

    public Complaint sendAnswerToPatient(ComplaintReviewDTO complaintReviewDTO) {
        String email = complaintReviewDTO.getPatient().getEmail();
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Complaint on " + complaintReviewDTO.getSubject() +"!");
        mail.setFrom(environment.getProperty("spring.mail.username"));
        mail.setText("Your complaint:  " + complaintReviewDTO.getMassage() + "\n\nAnswer:  " + complaintReviewDTO.getAnswer());
        mailSender.send(mail);

        Complaint complaint = complaintRepository.findById(complaintReviewDTO.getId()).get();
        complaint.setAnswered(true);
        complaint.setAnswer(complaintReviewDTO.getAnswer());
        return complaintRepository.save(complaint);
    }
}
