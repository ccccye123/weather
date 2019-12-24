package org.ccccye.weather.service.impl;

import com.google.common.base.Strings;
import org.ccccye.weather.common.ServerResponse;
import org.ccccye.weather.dto.Citycode;
import org.ccccye.weather.dto.Life;
import org.ccccye.weather.dto.Weather;
import org.ccccye.weather.dto.Weather6D;
import org.ccccye.weather.feign.CityCodeFeignClient;
import org.ccccye.weather.feign.WeatherFeignClient;
import org.ccccye.weather.service.WeatherService;
import org.ccccye.weather.vo.WeatherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    private WeatherFeignClient weatherFeignClient;
    @Autowired
    private CityCodeFeignClient cityCodeFeignClient;

    @Override
    public ServerResponse getWeatherInfo(String adcode) {
        if (Strings.isNullOrEmpty(adcode)){
            return ServerResponse.createByErrorMessage("Adcode不能为空");
        }

        Citycode citycode = cityCodeFeignClient.queryByAdcode(adcode);
        if (citycode == null) {
            return ServerResponse.createByErrorMessage("获取城市编码失败");
        }

        Weather weather = getToday(citycode.getCity_ID());
        Life life = getLife(citycode.getCity_CN());
        Weather6D weather6D = getFuture6D(citycode.getCity_ID());

        WeatherVo weatherVo = new WeatherVo(weather, life, weather6D);

        return ServerResponse.createBySuccess(weatherVo);
    }

    @Override
    public Weather getToday(String City_ID) {
        return weatherFeignClient.todayWeather(City_ID);
    }

    @Override
    public Life getLife(String City_CN) {
        return weatherFeignClient.lifeWeather(City_CN);
    }

    @Override
    public Weather6D getFuture6D(String City_ID) {
        return weatherFeignClient.d6Weather(City_ID);
    }
}
