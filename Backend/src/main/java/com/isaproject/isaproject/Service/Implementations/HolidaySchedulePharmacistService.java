package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.HolidaySchedulePharmacistDTO;
import com.isaproject.isaproject.DTO.RefuseHolidayScheduleDTO;
import com.isaproject.isaproject.Model.Schedule.HolidaySchedulePharmacist;
import com.isaproject.isaproject.Repository.HolidaySchedulePharmacistRepository;
import com.isaproject.isaproject.Service.IServices.IHolidaySchedulePharmacistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidaySchedulePharmacistService implements IHolidaySchedulePharmacistsService {
    @Autowired
    HolidaySchedulePharmacistRepository holidaySchedulePharmacistRepository;
    @Autowired
    private Environment environment;
    @Autowired
    JavaMailSenderImpl mailSender;

    @Override
    public HolidaySchedulePharmacist findById(Integer id) {
        return holidaySchedulePharmacistRepository.getOne(id);
    }

    @Override
    public List<HolidaySchedulePharmacist> findAll() {
        return holidaySchedulePharmacistRepository.findAll();
    }

    @Override
    public HolidaySchedulePharmacist save(HolidaySchedulePharmacistDTO scheduleDTO) {
        HolidaySchedulePharmacist offer = new HolidaySchedulePharmacist();
        offer.setPharmacist(scheduleDTO.getPharmacist());
        offer.setStartDate(scheduleDTO.getStartDate());
        offer.setEndDate(scheduleDTO.getEndDate());
        offer.setType(scheduleDTO.getType());
        offer.setApproved("WAIT_FOR_RESPONSE");

        return holidaySchedulePharmacistRepository.save(offer);
    }

    public HolidaySchedulePharmacist approve(Integer id){
        HolidaySchedulePharmacist holidaySchedulePharmacist =  holidaySchedulePharmacistRepository.getOne(id);
        holidaySchedulePharmacist.setApproved("APPROVED");
         return this.holidaySchedulePharmacistRepository.save(holidaySchedulePharmacist);
    }

    public HolidaySchedulePharmacist refuse(RefuseHolidayScheduleDTO dto){
        HolidaySchedulePharmacist holidaySchedulePharmacist =  holidaySchedulePharmacistRepository.getOne(dto.getRequestId());
        holidaySchedulePharmacist.setApproved("REFUSED");
        holidaySchedulePharmacist.setMassage(dto.getReason());
        return this.holidaySchedulePharmacistRepository.save(holidaySchedulePharmacist);
    }
    public  void sendEmailApproved(Integer id){
        HolidaySchedulePharmacist request = holidaySchedulePharmacistRepository.getOne(id);
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(request.getPharmacist().getEmail());
        mail.setSubject("Your holiday/absence request is approved!");
        mail.setFrom(environment.getProperty("spring.mail.username"));
        mail.setText("Your request for "+request.getType()+", from "+request.getStartDate()+" to "+request.getEndDate()
                +" " +"is approved.");

        mailSender.send(mail);
    }

    public  void sendEmailRefused(RefuseHolidayScheduleDTO dto){
        HolidaySchedulePharmacist request = holidaySchedulePharmacistRepository.getOne(dto.getRequestId());
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(request.getPharmacist().getEmail());
        mail.setSubject("Your holiday/absence request is refused.");
        mail.setFrom(environment.getProperty("spring.mail.username"));
        mail.setText("Your request for "+request.getType()+", from "+request.getStartDate()+" to "+request.getEndDate()
                +" " +"is refused. Reason: "+dto.getReason());

        mailSender.send(mail);
    }
}
