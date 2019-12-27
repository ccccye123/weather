package org.ccccye.weather.vo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class WeatherVo {
    /**
     * 城市名称
     */
    private String location;

    /**
     * 城市编号
     */
    private String cityID;

    /**
     * Adcode
     * 国家基础地理信息中心定义的区域代码
     */
    private String adCode;

    /**
     * 城市纬度
     */
    private String lat;

    /**
     * 城市经度
     */
    private String lon;

    /**
     * 实时天气
     */
    private RealTimeWeatherVo realTime;

    /**
     * 生活指数
     */
    private LifeStyleVo lifeStyle;

    /**
     * 天气预报
     */
    private List<DailyForecastVo> forecasts;

    public RealTimeWeatherVo getRealTime() {
        return realTime;
    }

    public void setRealTime(RealTimeWeatherVo realTime) {
        this.realTime = realTime;
    }

    public LifeStyleVo getLifeStyle() {
        return lifeStyle;
    }

    public void setLifeStyle(LifeStyleVo lifeStyle) {
        this.lifeStyle = lifeStyle;
    }

    public List<DailyForecastVo> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<DailyForecastVo> forecasts) {
        this.forecasts = forecasts;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
