package org.ccccye.weather.controller;

import org.ccccye.weather.common.ServerResponse;
import org.ccccye.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/weather/")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    /**
     * 获取天气数据
     * @param adcode
     * @return
     */
    @RequestMapping(value = "get.do", method = RequestMethod.GET)
    public ServerResponse getWeatherInfo(String adcode){
        return weatherService.getWeatherInfo(adcode);
    }

}
