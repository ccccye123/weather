package org.ccccye.weather.api.source.api;

import org.ccccye.weather.common.dto.he.HeWeather;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 和风天气接口
 */
@FeignClient(name = "weatherHe", url = "https://free-api.heweather.net")
public interface HeWeatherApiService {

    @RequestMapping(value = "/s6/weather/{type}?location={location}&key={key}", method = RequestMethod.GET)
    HeWeather request(@PathVariable("type") String type, @PathVariable("location") String location, @PathVariable("key") String key);
}
