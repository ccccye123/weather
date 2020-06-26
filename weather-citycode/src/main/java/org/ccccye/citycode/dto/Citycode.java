package org.ccccye.citycode.dto;

public class Citycode {
    private String City_ID;
    private String City_EN;
    private String City_CN;
    private String AD_code;

    public Citycode() {
    }

    public String getCity_ID() {
        return City_ID;
    }

    public void setCity_ID(String city_ID) {
        City_ID = city_ID;
    }

    public String getCity_EN() {
        return City_EN;
    }

    public void setCity_EN(String city_EN) {
        City_EN = city_EN;
    }

    public String getCity_CN() {
        return City_CN;
    }

    public void setCity_CN(String city_CN) {
        City_CN = city_CN;
    }

    public String getAD_code() {
        return AD_code;
    }

    public void setAD_code(String AD_code) {
        this.AD_code = AD_code;
    }
}
