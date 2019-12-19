package org.ccccye.weather.controller;

import org.ccccye.weather.dto.Life;
import org.ccccye.weather.dto.Weather;
import org.ccccye.weather.dto.Weather6D;
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
    public Weather getToday(){
        Weather weather = weatherFeignClient.todayWeather("101300101");
        return weather;
    }

    @RequestMapping(value = "life", method = RequestMethod.GET)
    public Life getLife(){
        Life life = weatherFeignClient.lifeWeather("南宁");
        return life;
    }

    @RequestMapping(value = "d6", method = RequestMethod.GET)
    public Weather6D getd6(){
        Weather6D weather = weatherFeignClient.d6Weather("101300101");
        return weather;
    }
}
