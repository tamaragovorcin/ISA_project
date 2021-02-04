package com.isaproject.isaproject.Service.Implementations;

import com.isaproject.isaproject.DTO.HolidayScheduleDermatologistDTO;
import com.isaproject.isaproject.DTO.RefuseHolidayScheduleDTO;
import com.isaproject.isaproject.Model.Schedule.HolidayScheduleDermatologist;
import com.isaproject.isaproject.Model.Schedule.HolidaySchedulePharmacist;
import com.isaproject.isaproject.Repository.HolidayScheduleDermatologistRepository;
import com.isaproject.isaproject.Service.IServices.IHolidayScheduleDermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class HolidayScheduleDermatologistService implements IHolidayScheduleDermatologistService {
    @Autowired
    HolidayScheduleDermatologistRepository holidayScheduleRepository;
    @Autowired
    private Environment environment;
    @Autowired
    JavaMailSenderImpl mailSender;


    @Override
    public HolidayScheduleDermatologist findById(Integer id) {
        return holidayScheduleRepository.getOne(id);
    }

    @Override
    public List<HolidayScheduleDermatologist> findAll() {
        return holidayScheduleRepository.findAll();
    }

    @Override
    public HolidayScheduleDermatologist save(HolidayScheduleDermatologistDTO scheduleDTO) {
        HolidayScheduleDermatologist offer = new HolidayScheduleDermatologist();
        offer.setDermatologist(scheduleDTO.getDermatologist());
        offer.setStartDate(scheduleDTO.getStartDate());
        offer.setEndDate(scheduleDTO.getEndDate());
        offer.setType(scheduleDTO.getType());
        offer.setApproved("WAIT_FOR_RESPONSE");

        return holidayScheduleRepository.save(offer);
    }


    public HolidayScheduleDermatologist approve(Integer id){
        HolidayScheduleDermatologist holidayScheduleDermatologist =  holidayScheduleRepository.getOne(id);
        holidayScheduleDermatologist.setApproved("APPROVED");
        return this.holidayScheduleRepository.save(holidayScheduleDermatologist);
    }

    public HolidayScheduleDermatologist refuse(RefuseHolidayScheduleDTO dto){
        HolidayScheduleDermatologist holidayScheduleDermatologist =  holidayScheduleRepository.getOne(dto.getRequestId());
        holidayScheduleDermatologist.setApproved("REFUSED");
        holidayScheduleDermatologist.setMassage(dto.getReason());
        return this.holidayScheduleRepository.save(holidayScheduleDermatologist);
    }
    public  void sendEmailApproved(Integer id){
        HolidayScheduleDermatologist request = holidayScheduleRepository.getOne(id);
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(request.getDermatologist().getEmail());
        mail.setSubject("Your holiday/absence request is approved!");
        mail.setFrom(environment.getProperty("spring.mail.username"));
        mail.setText("Your request for "+request.getType()+", from "+request.getStartDate()+" to "+request.getEndDate()
                +" " +"is approved.");

        mailSender.send(mail);
    }

    public  void sendEmailRefused(RefuseHolidayScheduleDTO dto){
        HolidayScheduleDermatologist request = holidayScheduleRepository.getOne(dto.getRequestId());
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(request.getDermatologist().getEmail());
        mail.setSubject("Your holiday/absence request is refused.");
        mail.setFrom(environment.getProperty("spring.mail.username"));
        mail.setText("Your request for "+request.getType()+", from "+request.getStartDate()+" to "+request.getEndDate()
                +" " +"is refused. Reason: "+dto.getReason());

        mailSender.send(mail);
    }
}
