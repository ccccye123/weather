package org.ccccye.weather.api.wx.service;

import org.ccccye.weather.common.dto.Citycode;
import org.ccccye.weather.common.vo.DailyForecastVo;
import org.ccccye.weather.common.vo.LifeStyleVo;
import org.ccccye.weather.common.vo.RealTimeWeatherVo;

import java.util.List;

public interface ExtWeatherService {
    RealTimeWeatherVo getRealTime(Citycode city);

    LifeStyleVo getLifeStyle(Citycode city);

    List<DailyForecastVo> getForecast(Citycode city);
}
