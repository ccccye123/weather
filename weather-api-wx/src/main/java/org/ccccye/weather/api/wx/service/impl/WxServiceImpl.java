package org.ccccye.weather.api.wx.service.impl;

//import org.ccccye.weather.api.source.service.WeatherService;
import org.ccccye.weather.api.wx.api.WeatherSourceApiService;
import org.ccccye.weather.api.wx.service.WxService;
import org.ccccye.weather.common.ServerResponse;
import org.ccccye.weather.common.vo.WeatherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxServiceImpl implements WxService {

    @Autowired
    private WeatherSourceApiService sourceApiService;

    @Override
    public ServerResponse getWeather(String adcode) {
        WeatherVo weatherVo = sourceApiService.getWeatherInfo(adcode);
        return ServerResponse.createBySuccess(weatherVo);
    }
}
