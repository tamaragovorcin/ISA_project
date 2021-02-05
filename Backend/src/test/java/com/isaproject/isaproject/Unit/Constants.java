package com.isaproject.isaproject.Unit;
import com.isaproject.isaproject.Model.Users.Address;
import com.isaproject.isaproject.Model.Users.Authority;
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

    public static final Address ADDRESS = new Address(1,"Town","Street", 5,5,"Country");
    public static final Address ADDRESS2 = new Address(2,"Town2","Street2", 52,52,"Country2");

    public static final String DESCRIPTION = "Description";

    public static final List<Authority> AUTHORITIES = new ArrayList<>();

    public static final String NEW_NAME = "NewName";
    public static final String NEW_SURNAME = "NewSurname";
}
