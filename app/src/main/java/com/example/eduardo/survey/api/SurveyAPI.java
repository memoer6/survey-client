package com.example.eduardo.survey.api;

import com.example.eduardo.survey.fields.SurveyFields;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * API to request the submission of a survey, sending the data to the server in SurveyFields class
 */

public interface SurveyAPI {

    @POST("/survey")
    Call<ResponseBody> conductSurvey(@Body SurveyFields surveyFields);

}
