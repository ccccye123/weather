package org.ccccye.weather.api.source.api;

import org.ccccye.weather.common.dto.Citycode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 城市编码接口
 */
@FeignClient(name = "citycode")
public interface CityCodeApiService {

    @RequestMapping(value = "/queryByAdcode/{code}", method = RequestMethod.GET)
    Citycode queryByAdcode(@PathVariable("code") String code);
}
