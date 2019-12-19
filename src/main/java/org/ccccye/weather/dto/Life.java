package org.ccccye.weather.dto;

import java.util.List;

public class Life {
    private String status;
    private String msg;
    private String city;
    private String update;
    private LifeGradeList data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public LifeGradeList getData() {
        return data;
    }

    public void setData(LifeGradeList data) {
        this.data = data;
    }
}
