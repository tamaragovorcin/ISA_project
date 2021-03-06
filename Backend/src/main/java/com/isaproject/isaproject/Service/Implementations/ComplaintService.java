package com.isaproject.isaproject.Service.Implementations;
import com.isaproject.isaproject.DTO.ComplaintDTO;
import com.isaproject.isaproject.DTO.ComplaintReviewDTO;
import com.isaproject.isaproject.Model.HelpModel.Complaint;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.PersonUser;
import com.isaproject.isaproject.Repository.ComplaintRepository;
import com.isaproject.isaproject.Repository.PatientRepository;
import com.isaproject.isaproject.Service.IServices.IComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ComplaintService implements IComplaintService {
    @Autowired
    ComplaintRepository complaintRepository;
    @Autowired
    DermatologistService dermatologistService;
    @Autowired
    PharmacyService pharmacyService;
    @Autowired
    PharmacistService pharmacistService;
    @Autowired
    JavaMailSenderImpl mailSender;
    @Autowired
    private Environment environment;
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Complaint findById(Integer id) {
        return complaintRepository.findById(id).get();
    }

    @Override
    public List<Complaint> findAll() {
        return complaintRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public Complaint save(ComplaintDTO complaintDTO) {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        PersonUser user = (PersonUser)currentUser.getPrincipal();
        Patient patient = patientRepository.getOne(user.getId());

        Complaint complaint = new Complaint();
        complaint.setAnswered(complaintDTO.isAnswered());
        complaint.setSubject(complaintDTO.getSubject());
        complaint.setMassage(complaintDTO.getMassage());
        complaint.setAnswer(complaintDTO.getAnswer());
        complaint.setPatient(patient);

        if(complaintDTO.getDermatologist()==null) complaint.setDermatologist(null);
        else complaint.setDermatologist(dermatologistService.findByEmail(complaintDTO.getDermatologist().getEmail()));

        if(complaintDTO.getPharmacist()==null) complaint.setPharmacist(null);
        else complaint.setPharmacist(pharmacistService.findByEmail(complaintDTO.getPharmacist().getEmail()));

        if(complaintDTO.getPharmacyName()==null) complaint.setPharmacy(null);
        else complaint.setPharmacy(pharmacyService.findById(complaintDTO.getPharmacyName().getPharmacyId()));

        return complaintRepository.save(complaint);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Complaint update(ComplaintReviewDTO complaintReviewDTO) {
        Complaint complaint = findById(complaintReviewDTO.getId());
        complaint.setAnswered(true);
        complaint.setAnswer(complaintReviewDTO.getAnswer());

        String email = complaint.getPatient().getEmail();
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Complaint on " + complaint.getSubject() +"!");
        mail.setFrom(environment.getProperty("spring.mail.username"));
        mail.setText("Your complaint:  " + complaint.getMassage() + "\n\nAnswer:  " + complaint.getAnswer());
        mailSender.send(mail);

        return complaintRepository.save(complaint);
    }
}