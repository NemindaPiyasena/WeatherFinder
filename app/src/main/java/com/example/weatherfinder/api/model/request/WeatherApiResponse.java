package com.example.weatherfinder.api.model.request;

import java.util.List;

public class WeatherApiResponse {

    private List<RainData> weather;
    private TempData main;
    private WindData wind;

    public WeatherApiResponse() {
    }

    public WeatherApiResponse(List<RainData> weather, TempData main, WindData wind) {
        this.weather = weather;
        this.main = main;
        this.wind = wind;
    }

    public List<RainData> getWeather() {
        return weather;
    }

    public void setWeather(List<RainData> weather) {
        this.weather = weather;
    }

    public TempData getMain() {
        return main;
    }

    public void setMain(TempData main) {
        this.main = main;
    }

    public WindData getWind() {
        return wind;
    }

    public void setWind(WindData wind) {
        this.wind = wind;
    }
}
