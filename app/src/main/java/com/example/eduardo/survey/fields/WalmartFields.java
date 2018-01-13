package com.example.eduardo.survey.fields;

/**
 * Fields for Walmart survey.
 */

public class WalmartFields extends SurveyFields {


    static final String storeName = "Walmart";
    private String tc;
    private String storeNum;


    public String getTc() {
        return tc;
    }
    public void setTc(String tc) {
        this.tc = tc;
    }
    public String getStoreNum() {
        return storeNum;
    }
    public void setStoreNum(String storeNum) {
        this.storeNum = storeNum;
    }



    WalmartFields(String storeName) {
        super(storeName);
    }


    @Override
    public String toString() {
        return "WalmartFields{" +
                "storeName='" + storeName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthYear=" + birthYear +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                "tc='" + tc + '\'' +
                ", storeNum='" + storeNum + '\'' +
                '}';
    }

}
