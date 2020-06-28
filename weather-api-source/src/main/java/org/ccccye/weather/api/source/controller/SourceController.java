package org.ccccye.weather.api.source.controller;

import org.ccccye.weather.api.source.service.WeatherService;
import org.ccccye.weather.common.ServerResponse;
import org.ccccye.weather.common.vo.WeatherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SourceController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping(value = "/get.do")
    public WeatherVo get(String adcode){
        return weatherService.getWeatherInfo(adcode);
    }

}
