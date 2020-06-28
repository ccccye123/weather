package org.ccccye.weather.api.source.api;

import org.ccccye.weather.common.dto.Life;
import org.ccccye.weather.common.dto.Weather;
import org.ccccye.weather.common.dto.Weather6D;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 天气接口
 */
@FeignClient(name = "weatherHelp", url = "http://api.help.bj.cn")
public interface HelpWeatherAPiService {

    @RequestMapping(value = "/apis/weather?id={id}", method = RequestMethod.GET)
    Weather todayWeather(@PathVariable("id") String id);

    @RequestMapping(value = "/apis/life27?id={id}", method = RequestMethod.GET)
    Life lifeWeather(@PathVariable("id") String id);

    @RequestMapping(value = "/apis/weather6d?id={id}", method = RequestMethod.GET)
    Weather6D d6Weather(@PathVariable("id") String id);
}
