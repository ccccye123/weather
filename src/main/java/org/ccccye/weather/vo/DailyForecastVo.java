package org.ccccye.weather.vo;

public class DailyForecastVo {
    /**
     * 日期
     */
    private String date;

    /**
     * 最高温度
     */
    private String tempMax;

    /**
     * 最低温度
     */
    private String tempMin;

    /**
     * 晚上天气状况
     */
    private String weatherNight;

    /**
     * 白天天气状况
     */
    private String weatherDay;

    /**
     * 风向360角度
     */
    private String windDegree;

    /**
     * 风向
     */
    private String windDir;

    /**
     * 风力
     */
    private String windPower;

    /**
     * 风速
     */
    private String windSpeed;

    /**
     * 相对湿度
     */
    private String humidity;

    /**
     * 降水量
     */
    private String pcpn;

    /**
     * 大气气压
     */
    private String airPressure;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getWeatherNight() {
        return weatherNight;
    }

    public void setWeatherNight(String weatherNight) {
        this.weatherNight = weatherNight;
    }

    public String getWeatherDay() {
        return weatherDay;
    }

    public void setWeatherDay(String weatherDay) {
        this.weatherDay = weatherDay;
    }

    public String getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(String windDegree) {
        this.windDegree = windDegree;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public String getWindPower() {
        return windPower;
    }

    public void setWindPower(String windPower) {
        this.windPower = windPower;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPcpn() {
        return pcpn;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    public String getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(String airPressure) {
        this.airPressure = airPressure;
    }
}
