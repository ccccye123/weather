package org.ccccye.weather.controller;

import com.google.common.base.Strings;
import org.ccccye.weather.common.ServerResponse;
import org.ccccye.weather.dto.Citycode;
import org.ccccye.weather.dto.Life;
import org.ccccye.weather.dto.Weather;
import org.ccccye.weather.dto.Weather6D;
import org.ccccye.weather.feign.CityCodeFeignClient;
import org.ccccye.weather.feign.WeatherFeignClient;
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
     *
     * @param adcode
     * @return
     */
    @RequestMapping(value = "get.do", method = RequestMethod.GET)
    public ServerResponse getWeatherInfo(String adcode){
        return weatherService.getWeatherInfo(adcode);
    }

}
