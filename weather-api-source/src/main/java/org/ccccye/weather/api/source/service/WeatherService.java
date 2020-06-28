package org.ccccye.weather.api.source.service;

import org.ccccye.weather.common.ServerResponse;
import org.ccccye.weather.common.dto.Citycode;
import org.ccccye.weather.common.vo.DailyForecastVo;
import org.ccccye.weather.common.vo.LifeStyleVo;
import org.ccccye.weather.common.vo.RealTimeWeatherVo;
import org.ccccye.weather.common.vo.WeatherVo;

import java.util.List;

public interface WeatherService {
    /**
     * 获取天气数据
     * @param adcode
     * @return
     */
    WeatherVo getWeatherInfo(String adcode);

    /**
     * 获取实时天气
     * @param city
     * @return
     */
    RealTimeWeatherVo getRealTime(Citycode city);

    /**
     * 获取生活指数
     * @param city
     * @return
     */
    LifeStyleVo getLifeStyle(Citycode city);

    /**
     * 获取天气预告
     * @param city
     * @return
     */
    List<DailyForecastVo> getForecast(Citycode city);
}
