package org.ccccye.weather.service;

import org.ccccye.weather.common.ServerResponse;
import org.ccccye.weather.dto.Citycode;
import org.ccccye.weather.dto.Life;
import org.ccccye.weather.dto.Weather;
import org.ccccye.weather.dto.Weather6D;
import org.ccccye.weather.vo.DailyForecastVo;
import org.ccccye.weather.vo.LifeStyleVo;
import org.ccccye.weather.vo.RealTimeWeatherVo;

import java.util.List;

public interface WeatherService {
    /**
     * 获取天气数据
     * @param adcode
     * @return
     */
    ServerResponse getWeatherInfo(String adcode);

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
