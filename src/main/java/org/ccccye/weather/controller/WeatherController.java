package org.ccccye.weather.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.ccccye.weather.aop.RestControllerRequestLimit;
import org.ccccye.weather.common.ServerResponse;
import org.ccccye.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/weather/")
public class WeatherController {
    private final static Log logger = LogFactory.getLog(WeatherController.class);

    @Autowired
    private WeatherService weatherService;


    /**
     * 获取天气数据
     * @param adcode
     * @return
     */
    @RestControllerRequestLimit(maxCount = 3)
    @RequestMapping(value = "get.do", method = RequestMethod.GET)
    public ServerResponse getWeatherInfo(HttpServletRequest request, String adcode){
        return weatherService.getWeatherInfo(adcode);
    }

}
