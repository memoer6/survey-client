package com.example.eduardo.survey.fields;

/**
 * Class that contains the data to request the survey
 */

//Retrofit 2 will use the converter library chosen to handle the deserialization of data from
// a Java object. If you annotate the parameter with a @Body parameter, this work will be done
// automatically. If you are using the Gson library for instance, any field belonging to the class
// will be serialized for you. You can change this name using the @SerializedName decorator:
public class SurveyFields {

    String storeName;
    String firstName;
    String lastName;
    String gender;
    int birthYear;
    String street;
    String city;
    String postalCode;
    String email;
    String phone;
    String date;
    String time;


    SurveyFields(String storeName) {
        this.storeName = storeName;
    }


    //getter and setters are required by Retrofit
    String getStoreName() {
        return storeName;
    }
    void setStoreName(String storeName) {this.storeName = storeName; }
    String getFirstName() {
        return firstName;
    }
    void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    String getLastName() {
        return lastName;
    }
    void setLastName(String lastName) {
        this.lastName = lastName;
    }
    String getGender() {
        return gender;
    }
    void setGender(String gender) {
        this.gender = gender;
    }
    int getBirthYear() {
        return birthYear;
    }
    void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
    String getStreet() {
        return street;
    }
    void setStreet(String street) {
        this.street = street;
    }
    String getCity() {
        return city;
    }
    void setCity(String city) {
        this.city = city;
    }
    String getPostalCode() {
        return postalCode;
    }
    void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    String getEmail() {
        return email;
    }
    void setEmail(String email) {
        this.email = email;
    }
    String getPhone() {
        return phone;
    }
    void setPhone(String phone) {
        this.phone = phone;
    }
    String getDate() {
        return date;
    }
    void setDate(String date) {
        this.date = date;
    }
    String getTime() {
        return time;
    }
    void setTime(String time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "SurveyFields{" +
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
                '}';
    }
}
