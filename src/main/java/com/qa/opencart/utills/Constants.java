package com.qa.opencart.utills;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final String LOGIN_PAGE_TITLE ="Account Login1";
    public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
    public static final int DEFAULT_TIME_OUT = 5;

    public static final String REGISTER_PAGE_TITLE = "Register Account";
    public static final  String ACCOUNT_CREATED = "Your Account Has Been Created!";

    public static final  String ACCOUNT_PAGE_TITLE = "My Account1";
    public static final String LOGIN_ERROR_MESSG = "No match for E-Mail Address and/or Password";

    public static  List<String> getExpectedArraylist(){
        List<String> expectedList = new ArrayList<>();
        expectedList.add("My Account");
        expectedList.add("My Orders");
        expectedList.add("My Affiliate Account");
        expectedList.add("Newsletter");
        return expectedList;
    }


}
