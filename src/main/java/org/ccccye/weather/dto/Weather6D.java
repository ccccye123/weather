package org.ccccye.weather.dto;

public class Weather6D {
    private String status;
    private String msg;
    private Weather6DItem data;

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

    public Weather6DItem getData() {
        return data;
    }

    public void setData(Weather6DItem data) {
        this.data = data;
    }
}
