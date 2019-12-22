package org.ccccye.weather.controller;

import org.ccccye.weather.dto.Citycode;
import org.ccccye.weather.dto.Life;
import org.ccccye.weather.dto.Weather;
import org.ccccye.weather.dto.Weather6D;
import org.ccccye.weather.feign.CityCodeFeignClient;
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
    @Autowired
    private CityCodeFeignClient cityCodeFeignClient;

    @RequestMapping(value = "today", method = RequestMethod.GET)
    public Weather getToday(String adcode){
        Citycode citycode = cityCodeFeignClient.queryByAdcode(adcode);

        Weather weather = weatherFeignClient.todayWeather(citycode.getCity_ID());
        return weather;
    }

    @RequestMapping(value = "life", method = RequestMethod.GET)
    public Life getLife(String adcode){
        Citycode citycode = cityCodeFeignClient.queryByAdcode(adcode);

        Life life = weatherFeignClient.lifeWeather(citycode.getCity_CN());
        return life;
    }

    @RequestMapping(value = "d6", method = RequestMethod.GET)
    public Weather6D getd6(String adcode){
        Citycode citycode = cityCodeFeignClient.queryByAdcode(adcode);

        Weather6D weather = weatherFeignClient.d6Weather(citycode.getCity_ID());
        return weather;
    }
}
