package com.isaproject.isaproject.Repository;

import com.isaproject.isaproject.Model.HelpModel.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

}
