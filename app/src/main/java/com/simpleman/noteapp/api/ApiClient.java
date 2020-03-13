package com.simpleman.noteapp.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "https://apiapps8484.000webhostapp.com/notes_app/";
    private static Retrofit retrofit;

    public static Retrofit getApiCLient(){

        Gson gson = new GsonBuilder().setLenient().create();

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit;
    }
}
