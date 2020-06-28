package org.ccccye.weather.api.wx.service;

import org.ccccye.weather.common.ServerResponse;

public interface WxService {
    ServerResponse getWeather(String adcode);
}
