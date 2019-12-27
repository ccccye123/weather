package org.ccccye.weather.service;

import org.ccccye.weather.dto.Citycode;
import org.ccccye.weather.vo.DailyForecastVo;
import org.ccccye.weather.vo.LifeStyleVo;
import org.ccccye.weather.vo.RealTimeWeatherVo;
import org.ccccye.weather.vo.WeatherVo;

import java.util.List;

public interface ExtWeatherService {
    RealTimeWeatherVo getRealTime(Citycode city);

    LifeStyleVo getLifeStyle(Citycode city);

    List<DailyForecastVo> getForecast(Citycode city);
}
