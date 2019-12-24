package org.ccccye.weather.service;

import org.ccccye.weather.common.ServerResponse;
import org.ccccye.weather.dto.Life;
import org.ccccye.weather.dto.Weather;
import org.ccccye.weather.dto.Weather6D;

public interface WeatherService {
    /**
     * 获取天气数据
     * @param adcode
     * @return
     */
    ServerResponse getWeatherInfo(String adcode);

    /**
     * 获取实时天气
     * @param City_ID
     * @return
     */
    Weather getToday(String City_ID);

    /**
     * 获取生活指数
     * @param City_CN
     * @return
     */
    Life getLife(String City_CN);

    /**
     * 实时+4day+昨天
     * @param City_ID
     * @return
     */
    Weather6D getFuture6D(String City_ID);
}
