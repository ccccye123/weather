package org.ccccye.weather.controller;

import org.ccccye.weather.dto.Weather;
import org.ccccye.weather.feign.WeatherFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/weather/")
public class WeatherController {
    @Autowired
    private WeatherFeignClient weatherFeignClient;

    @RequestMapping(value = "today", method = RequestMethod.GET)
    public String getToday(){
        Weather weather = weatherFeignClient.todayWeather("101300101");
        return weather.getTemp();
    }
}
