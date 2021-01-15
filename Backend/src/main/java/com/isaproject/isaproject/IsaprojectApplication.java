package com.isaproject.isaproject;

import com.isaproject.isaproject.Model.Examinations.*;
import com.isaproject.isaproject.Model.HelpModel.*;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Medicine.Specification;
import com.isaproject.isaproject.Model.Orders.Offer;
import com.isaproject.isaproject.Model.Orders.Order;
import com.isaproject.isaproject.Model.Pharmacy.Actions;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Schedule.HolidayScheduleDermatologist;
import com.isaproject.isaproject.Model.Schedule.HolidaySchedulePharmacist;
import com.isaproject.isaproject.Model.Schedule.WorkingHoursDermatologist;
import com.isaproject.isaproject.Model.Schedule.WorkingHoursPharmacist;
import com.isaproject.isaproject.Model.Users.*;
import org.apache.tomcat.jni.Local;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

@EnableScheduling
@SpringBootApplication
public class IsaprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsaprojectApplication.class, args);





	}
}