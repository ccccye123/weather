package org.ccccye.weather.api.wx.controller;

import org.ccccye.weather.api.wx.service.WxService;
import org.ccccye.weather.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
public class WxController {

    @Autowired
    private WxService wxService;

    /**
     * 提供微信小程序天气数据
     * @param adcode
     * @return
     */
    @RequestMapping(value = "/get_weather.do", method = RequestMethod.GET)
    public ServerResponse getWeatherInfo(HttpServletRequest request, String adcode){
        return wxService.getWeather(adcode);
    }

}
