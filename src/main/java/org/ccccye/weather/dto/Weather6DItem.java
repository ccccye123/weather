package org.ccccye.weather.dto;

import java.util.List;

public class Weather6DItem {
    private String city;
    private String life;
    private String temp;
    private Weather6Detail yesterday;
    private List<Weather6Detail> forecast;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public Weather6Detail getYesterday() {
        return yesterday;
    }

    public void setYesterday(Weather6Detail yesterday) {
        this.yesterday = yesterday;
    }

    public List<Weather6Detail> getForecast() {
        return forecast;
    }

    public void setForecast(List<Weather6Detail> forecast) {
        this.forecast = forecast;
    }

}
