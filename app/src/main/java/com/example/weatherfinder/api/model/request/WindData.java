package com.example.weatherfinder.api.model.request;

public class WindData {

    private float speed;
    private int deg;

    public WindData() {
    }

    public WindData(float speed, int deg) {
        this.speed = speed;
        this.deg = deg;
    }


    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }
}
