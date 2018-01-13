package com.example.eduardo.survey.utility;

/**
 * Enum listing all the filters available
 */

public final class Store {

    //tags for bundle keys
    public static final String KEY_FIELD_STORE_ID = "Store Id";
    public static final String KEY_FIELD_DATE = "Date"; //walmart
    public static final String KEY_FIELD_TIME = "Time"; //walmart
    public static final String KEY_FIELD_STORE_NUMBER = "Store Number"; //walmart
    public static final String KEY_FIELD_TC = "TC#"; //walmart
    public static final String KEY_FIELD_FIRST_NAME = "First Name";
    public static final String KEY_FIELD_LAST_NAME = "Last Name";
    public static final String KEY_FIELD_GENDER = "Gender";
    public static final String KEY_FIELD_BIRTH_YEAR = "Born Year";
    public static final String KEY_FIELD_STREET = "Street";
    public static final String KEY_FIELD_CITY = "City";
    public static final String KEY_FIELD_POSTAL_CODE = "Postal Code";
    public static final String KEY_FIELD_EMAIL = "Email";
    public static final String KEY_FIELD_PHONE = "Phone";
    public static final int DEFAULT_BIRTH_YEAR = 1990;



    //tags for stores ids
    public final static int STORE_WALMART = 1;

    private Store() {
        //this prevents even the native class from calling this constructor as well :
        throw new AssertionError();
    }


}


