package org.ccccye.weather.api.wx.controller;

import org.ccccye.weather.api.wx.service.WeatherService;
import org.ccccye.weather.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/weather/wx/")
public class WxController {

    @Autowired
    private WeatherService weatherService;

    /**
     * 获取天气数据
     * @param adcode
     * @return
     */
    @RequestMapping(value = "get.do", method = RequestMethod.GET)
    public ServerResponse getWeatherInfo(HttpServletRequest request, String adcode){
        return weatherService.getWeatherInfo(adcode);
    }
}
