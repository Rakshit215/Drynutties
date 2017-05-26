package com.citrus.sample;

import com.citrus.sdk.Environment;

/**
 * Created by salil on 13/6/15.
 */
public interface Constants {

    String BILL_URL = "http://www.drynutties.com/Android/bill.php";
    String RETURN_URL_LOAD_MONEY = "http://www.drynutties.com/Android/redirectUrlLoadCash.php";

    String SIGNUP_ID = "2c91plpxo3-signup";
    String SIGNUP_SECRET = "14497cab324330cf0c8164fc27f7f8ef";
    String SIGNIN_ID = "2c91plpxo3-signin";
    String SIGNIN_SECRET = "a2d17afa803aee16bc6a3a3d48e3a0c1";
    String VANITY = "drynutties";
    Environment environment = Environment.PRODUCTION;

    boolean enableLogging = false;

    String colorPrimaryDark = "#fd803b";
    String colorPrimary = "#fd803b";
    String textColor = "#ffffff";
}
