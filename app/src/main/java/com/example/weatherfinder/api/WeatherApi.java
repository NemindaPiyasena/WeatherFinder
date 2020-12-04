package com.example.weatherfinder.api;

import com.example.weatherfinder.api.model.request.WeatherApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("data/2.5/weather?appid=d0b610c79c1f1e175476ec857ce8da61")
    Call<WeatherApiResponse> getWeatherApiResponseAt(@Query("q") String city);

}
