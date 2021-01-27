package com.isaproject.isaproject.Controller;
import com.isaproject.isaproject.DTO.ComplaintDTO;
import com.isaproject.isaproject.Model.HelpModel.Complaint;
import com.isaproject.isaproject.Service.Implementations.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaint")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ComplaintController {
    @Autowired
    ComplaintService complaintService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<Complaint> add(@RequestBody ComplaintDTO complaintDTO)
    {
        Complaint complaint = complaintService.save(complaintDTO);
        return complaint == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(complaint);
    }

    @GetMapping("")
    ResponseEntity<List<Complaint>> getAll()
    {
        List<Complaint> complaints = complaintService.findAll();
        return complaints == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(complaints);
    }

    @GetMapping("notAnswered")
    ResponseEntity<List<Complaint>> getAllNotAnswered()
    {
        List<Complaint> complaints = complaintService.findAllNotAnswered();
        return complaints == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(complaints);
    }



}
