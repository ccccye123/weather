package org.ccccye.weather.feign;

import org.ccccye.weather.dto.Life;
import org.ccccye.weather.dto.Weather;
import org.ccccye.weather.dto.Weather6D;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "weather", url = "http://api.help.bj.cn")
public interface WeatherFeignClient {

    @RequestMapping(value = "/apis/weather?id={id}", method = RequestMethod.GET)
    Weather todayWeather(@PathVariable("id") String id);

    @RequestMapping(value = "/apis/life27?id={id}", method = RequestMethod.GET)
    Life lifeWeather(@PathVariable("id") String id);

    @RequestMapping(value = "/apis/weather6d?id={id}", method = RequestMethod.GET)
    Weather6D d6Weather(@PathVariable("id") String id);
}
