package org.ccccye.weather.dto.he;

import org.ccccye.weather.vo.LifeStyleItemVo;

import java.util.List;

public class HeWeatherInfo {
    private Basic basic;
    private Update update;
    private String status;
    private Now now;
    private List<Lifestyle> lifestyle;
    private List<Forecast> daily_forecast;

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Now getNow() {
        return now;
    }

    public void setNow(Now now) {
        this.now = now;
    }

    public List<Forecast> getDaily_forecast() {
        return daily_forecast;
    }

    public void setDaily_forecast(List<Forecast> daily_forecast) {
        this.daily_forecast = daily_forecast;
    }

    public List<Lifestyle> getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(List<Lifestyle> lifestyle) {
        this.lifestyle = lifestyle;
    }
}
