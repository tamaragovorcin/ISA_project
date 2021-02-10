package com.isaproject.isaproject.Unit;
import com.isaproject.isaproject.Model.Medicine.Medication;
import com.isaproject.isaproject.Model.Pharmacy.Actions;
import com.isaproject.isaproject.Model.Pharmacy.Pharmacy;
import com.isaproject.isaproject.Model.Users.Address;
import com.isaproject.isaproject.Model.Users.Authority;
import com.isaproject.isaproject.Model.Users.Patient;
import com.isaproject.isaproject.Model.Users.Pharmacist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final Integer ID = 1;
    public static final Integer ID2 = 2;

    public static final String NAME = "Name";
    public static final String NAME2 = "Name2";

    public static final String SURNAME = "Surname";
    public static final String SURNAME2 = "Surname2";

    public static final String EMAIL = "email@gmail.com";
    public static final String EMAIL2 = "email2@gmail.com";

    public static final String PASSWORD = "password";
    public static final String PASSWORD2 = "password2";

    public static final String PHONENUMBER = "7894165";
    public static final String PHONENUMBER2 = "78941652";

    public static final Long CODE = 1234l;

    public static final Address ADDRESS = new Address(1,"Town","Street", 5,5,"Country");
    public static final Address ADDRESS2 = new Address(2,"Town2","Street2", 52,52,"Country2");

    public static final Patient PATIENT = new Patient(1, "Name", "Surname", "patient@gmail.com", ADDRESS);
    public static final Patient PATIENT2 = new Patient(1, "Name2", "Surname2", "patient2@gmail.com", ADDRESS2);
    public  static final Pharmacy PHARMACY = new Pharmacy(1, "PharmacyName", 3.5, ADDRESS2);
    public static final Medication MEDICATION = new Medication(1, "Medication" ,  1234l);
    public static final Medication MEDICATION2 = new Medication(2, "Medication2" ,  12345l);

    public static final Pharmacist PHARMACIST = new Pharmacist(1,"PharmacistName","PharmacistSurname","pharmacist@gmail.com",ADDRESS,5,PHARMACY);
    public static final Pharmacist PHARMACIST2 = new Pharmacist(2,"PharmacistName2","PharmacistSurname2","pharmacist2@gmail.com",ADDRESS2,5,PHARMACY);

    public static final String DESCRIPTION = "Description";
    public static final String NEW_DESCRIPTION = "New Action Description";



    public static final List<Authority> AUTHORITIES = new ArrayList<>();

    public static final String NEW_NAME = "NewName";
    public static final String NEW_SURNAME = "NewSurname";
}
