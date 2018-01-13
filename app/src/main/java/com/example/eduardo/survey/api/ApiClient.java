package com.example.eduardo.survey.api;


import android.util.Log;

import com.example.eduardo.survey.fields.SurveyFields;
import com.example.eduardo.survey.fields.WalmartFields;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Mediate between API and service component (SurveySubmitter)
 */

public class ApiClient {


    private final static String BASE_URL = "http://192.168.1.110:9001";
    private final static SurveyAPI apiService = getServiceInstance();

    private onSurveyResponse mListener;


    //Constructor that register activities
    public ApiClient(onSurveyResponse listener) {

        //Since the ApiClient is not a static class, and its instances are created and destroyed
        // within the activity life-cycle, there is not need to declare the view with weak reference
        mListener = listener;
    }


    private static SurveyAPI getServiceInstance() {

        //Since logging isn’t integrated by default anymore in Retrofit 2, we need to add a
        // logging interceptor for OkHttp.
        //OkHttp’s logging interceptor has four log levels: NONE, BASIC, HEADERS, BODY
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        //This method customize the Gson serialization of Java objects
        //When you have a list of polymorphic objects, the default way for GSON is implement the
        // superclass (parent), thus preventing the creation of objects with specific
        // subclass (child) properties. With this customization, Gson is able to include the fields
        //of subclasses like in WalmartFields
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(SurveyFields.class, new JsonSerializer<SurveyFields>() {
                    @Override
                    public JsonElement serialize(SurveyFields src, Type typeOfSrc, JsonSerializationContext context) {
                        if (src instanceof WalmartFields) {
                            return context.serialize(src, WalmartFields.class);
                        }
                        return context.serialize(src);
                    }
                })
                .create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(SurveyAPI.class);
    }



    //Conduct survey
    public void conductSurvey(SurveyFields surveyFields) {

        Log.d("RetrofitClient", surveyFields.toString());

        Call<ResponseBody> submit = apiService.conductSurvey(surveyFields);

        //asynchronus call (in UI thread)
        submit.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                //Within Retrofit 2, the onResponse() method is called even though the request
                // wasn’t successful. The Response class has a convenience method isSuccessful()
                // to check yourself whether the request was handled successfully (returning status
                // code 2xx) and you can use the response object for further processing.
                Log.d("ApiClient", "response code: " + response.code());
                if (response.isSuccessful()) {

                    mListener.onResponse(call, response);

                } else {

                    mListener.onFailure(response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                // handle execution failures like no internet connectivity
                mListener.onFailure(t.getMessage());
                t.printStackTrace();

            }

        });


    }



    //interface to be implemented by components registered with ApiClient
    // in order to receive callbacks
    public interface onSurveyResponse {

        void onResponse(Call<ResponseBody> call, Response<ResponseBody> response);

        void onFailure(String message);

    }




}
