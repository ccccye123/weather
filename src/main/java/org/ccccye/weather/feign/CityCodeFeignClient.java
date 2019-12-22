package org.ccccye.weather.feign;

import org.ccccye.weather.dto.Citycode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "cityCode", url = "https://www.ccccye.cn/city")
public interface CityCodeFeignClient {
    @RequestMapping(value = "/queryByAdcode/{code}", method = RequestMethod.GET)
    Citycode queryByAdcode(@PathVariable("code") String code);
}
