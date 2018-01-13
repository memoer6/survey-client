package com.example.eduardo.survey.fields;


import android.os.Bundle;

import com.example.eduardo.survey.utility.Store;

/**
 * Factory class to provide abstract creation of SurveyFields in MainActivity
 */

public class FieldsFactory {



    private FieldsFactory() {

    }

    public static SurveyFields getFields(Bundle args) {

        //Store is Walmart
        if (args.getInt(Store.KEY_FIELD_STORE_ID) == Store.STORE_WALMART) {
            WalmartFields surveyFields = new WalmartFields(WalmartFields.storeName);
            surveyFields.setFirstName(args.getString(Store.KEY_FIELD_FIRST_NAME, ""));
            surveyFields.setLastName(args.getString(Store.KEY_FIELD_LAST_NAME, ""));
            surveyFields.setGender(args.getString(Store.KEY_FIELD_GENDER, ""));
            surveyFields.setBirthYear(args.getInt(Store.KEY_FIELD_BIRTH_YEAR, Store.DEFAULT_BIRTH_YEAR));
            surveyFields.setStreet(args.getString(Store.KEY_FIELD_STREET, ""));
            surveyFields.setCity(args.getString(Store.KEY_FIELD_CITY, ""));
            surveyFields.setPostalCode(args.getString(Store.KEY_FIELD_POSTAL_CODE, ""));
            surveyFields.setEmail(args.getString(Store.KEY_FIELD_EMAIL, ""));
            surveyFields.setPhone(args.getString(Store.KEY_FIELD_PHONE, ""));
            surveyFields.setDate(args.getString(Store.KEY_FIELD_DATE, ""));
            surveyFields.setTime(args.getString(Store.KEY_FIELD_TIME, ""));
            surveyFields.setStoreNum(args.getString(Store.KEY_FIELD_STORE_NUMBER, ""));
            surveyFields.setTc(args.getString(Store.KEY_FIELD_TC, ""));
            return surveyFields;
        }

        return null;
    }

}
