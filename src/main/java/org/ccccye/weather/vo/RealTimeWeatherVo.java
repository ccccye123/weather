package org.ccccye.weather.vo;

public class RealTimeWeatherVo {

    /**
     * 温度
     * 单位：摄氏度
     */
    private String temp;

    /**
     * 天气状况
     */
    private String weather;

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

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
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
