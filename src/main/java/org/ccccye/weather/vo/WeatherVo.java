package org.ccccye.weather.vo;

import org.ccccye.weather.dto.Life;
import org.ccccye.weather.dto.Weather;
import org.ccccye.weather.dto.Weather6D;

public class WeatherVo {
    private Weather weatherDetail;
    private Life lifeInfo;
    private Weather6D weatherFuture6D;

    public WeatherVo() {
    }

    public WeatherVo(Weather weatherDetail, Life lifeInfo, Weather6D weatherFuture6D) {
        this.weatherDetail = weatherDetail;
        this.lifeInfo = lifeInfo;
        this.weatherFuture6D = weatherFuture6D;
    }

    public Weather getWeatherDetail() {
        return weatherDetail;
    }

    public void setWeatherDetail(Weather weatherDetail) {
        this.weatherDetail = weatherDetail;
    }

    public Life getLifeInfo() {
        return lifeInfo;
    }

    public void setLifeInfo(Life lifeInfo) {
        this.lifeInfo = lifeInfo;
    }

    public Weather6D getWeatherFuture6D() {
        return weatherFuture6D;
    }

    public void setWeatherFuture6D(Weather6D weatherFuture6D) {
        this.weatherFuture6D = weatherFuture6D;
    }
}
