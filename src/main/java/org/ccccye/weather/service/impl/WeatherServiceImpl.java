package org.ccccye.weather.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.ccccye.weather.common.ServerResponse;
import org.ccccye.weather.dto.*;
import org.ccccye.weather.feign.CityCodeFeignClient;
import org.ccccye.weather.feign.WeatherFeignClient;
import org.ccccye.weather.service.ExtWeatherService;
import org.ccccye.weather.service.WeatherService;
import org.ccccye.weather.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    private ExtWeatherService extWeatherService;
    @Autowired
    private CityCodeFeignClient cityCodeFeignClient;

    /**
     * 获取天气数据
     * @param adcode
     * @return
     */
    @Override
    public ServerResponse getWeatherInfo(String adcode) {
        if (Strings.isNullOrEmpty(adcode)){
            return ServerResponse.createByErrorMessage("Adcode不能为空");
        }

        Citycode city = cityCodeFeignClient.queryByAdcode(adcode);
        if (city == null) {
            return ServerResponse.createByErrorMessage("获取城市编码失败");
        }

        RealTimeWeatherVo realTimeWeatherVo = extWeatherService.getRealTime(city);
        LifeStyleVo lifeStyleVo = extWeatherService.getLifeStyle(city);
        List<DailyForecastVo> dailyForecastVoList = extWeatherService.getForecast(city);

        WeatherVo weatherVo = assembleWeatherVo(realTimeWeatherVo, lifeStyleVo, dailyForecastVoList, city);

        return ServerResponse.createBySuccess(weatherVo);
    }

    /**
     * 获取实时天气
     * @param City_ID
     * @return
     */
    @Override
    public Weather getToday(String City_ID) {
        throw new NotImplementedException();
    }

    /**
     * 获取实时生活指数
     * @param City_CN
     * @return
     */
    @Override
    public Life getLife(String City_CN) {
        throw new NotImplementedException();
    }

    /**
     * 获取最近6天天气
     * @param City_ID
     * @return
     */
    @Override
    public Weather6D getFuture6D(String City_ID) {
        throw new NotImplementedException();
    }


    //region 内部函数
    private WeatherVo assembleWeatherVo(RealTimeWeatherVo realtime, LifeStyleVo life, List<DailyForecastVo> dailyForecastVoList, Citycode city){
        WeatherVo vo = new WeatherVo();

        vo.setLocation(city.getCity_CN());
        vo.setCityID(city.getCity_ID());
        vo.setAdCode(city.getAD_code());
        // todo
        //vo.setLat();
        //vo.setLon();
        vo.setRealTime(realtime);
        vo.setLifeStyle(life);
        vo.setForecasts(dailyForecastVoList);

        return vo;
    }

    //endregion
}
