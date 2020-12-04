package com.example.weatherfinder.api;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://api.openweathermap.org/";
    private static WeatherApi weatherApi;

    private static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            Gson gson = new Gson();
            retrofit = new  Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit;
    }

    public static WeatherApi getWeatherApi() {
        if (weatherApi == null) {
            weatherApi = getRetrofitInstance().create(WeatherApi.class);
        }
        return weatherApi;
    }



}
