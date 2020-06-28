package org.ccccye.weather.api.wx.api;

import org.ccccye.weather.common.ServerResponse;
import org.ccccye.weather.common.vo.WeatherVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "source")
public interface WeatherSourceApiService {

    @RequestMapping(value = "/get.do?adcode={adcode}", method = RequestMethod.GET)
    public WeatherVo getWeatherInfo(@PathVariable("adcode") String adcode);
}
