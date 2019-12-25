package org.ccccye.weather.vo;

import org.ccccye.weather.dto.LifeGradeList;

import java.util.List;

public class WeatherVo {
    private String cityen;//城市名称英文
    private String city;//城市名称
    private String citycode;//城市编码
    private String temp;//实时温度
    private String tempf;//华氏温度
    private String wd;//风向
    private String wden;//风向英文
    private String wdforce;//风力
    private String wdspd;//风速
    private String uptime;//更新时间
    private String weather;//天气状况
    private String weatheren;//天气状况英文
    private String weatherimg;//天气状况图标
    private String stp;//气压
    private String wisib;//能见度
    private String humidity;//湿度
    private String prcp;//降雨
    private String prcp24h;//24小时降雨量
    private String aqi;//AQI
    private String pm25;//PM2.5
    private String today;//今天日期
    private LifeGradeList lifeInfo; // 生活指数
    private List<FutureWeatherVo> futureWeatherVo4D; //未来4天天气预告

    public WeatherVo() {
    }

    public String getCityen() {
        return cityen;
    }

    public void setCityen(String cityen) {
        this.cityen = cityen;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTempf() {
        return tempf;
    }

    public void setTempf(String tempf) {
        this.tempf = tempf;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getWden() {
        return wden;
    }

    public void setWden(String wden) {
        this.wden = wden;
    }

    public String getWdforce() {
        return wdforce;
    }

    public void setWdforce(String wdforce) {
        this.wdforce = wdforce;
    }

    public String getWdspd() {
        return wdspd;
    }

    public void setWdspd(String wdspd) {
        this.wdspd = wdspd;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeatheren() {
        return weatheren;
    }

    public void setWeatheren(String weatheren) {
        this.weatheren = weatheren;
    }

    public String getWeatherimg() {
        return weatherimg;
    }

    public void setWeatherimg(String weatherimg) {
        this.weatherimg = weatherimg;
    }

    public String getStp() {
        return stp;
    }

    public void setStp(String stp) {
        this.stp = stp;
    }

    public String getWisib() {
        return wisib;
    }

    public void setWisib(String wisib) {
        this.wisib = wisib;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPrcp() {
        return prcp;
    }

    public void setPrcp(String prcp) {
        this.prcp = prcp;
    }

    public String getPrcp24h() {
        return prcp24h;
    }

    public void setPrcp24h(String prcp24h) {
        this.prcp24h = prcp24h;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public LifeGradeList getLifeInfo() {
        return lifeInfo;
    }

    public void setLifeInfo(LifeGradeList lifeInfo) {
        this.lifeInfo = lifeInfo;
    }

    public List<FutureWeatherVo> getFutureWeatherVo4D() {
        return futureWeatherVo4D;
    }

    public void setFutureWeatherVo4D(List<FutureWeatherVo> futureWeatherVo4D) {
        this.futureWeatherVo4D = futureWeatherVo4D;
    }
}
