package org.ccccye.weather.service.impl;

import com.google.common.collect.Lists;
import org.ccccye.weather.dto.*;
import org.ccccye.weather.dto.he.Forecast;
import org.ccccye.weather.dto.he.HeWeather;
import org.ccccye.weather.dto.he.Lifestyle;
import org.ccccye.weather.dto.he.Now;
import org.ccccye.weather.feign.WeatherFeignClient;
import org.ccccye.weather.service.ExtWeatherService;
import org.ccccye.weather.vo.DailyForecastVo;
import org.ccccye.weather.vo.LifeStyleItemVo;
import org.ccccye.weather.vo.LifeStyleVo;
import org.ccccye.weather.vo.RealTimeWeatherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtHelpWeatherServiceImpl implements ExtWeatherService {
    @Autowired
    private WeatherFeignClient weatherFeignClient;

    /**
     * 获取实时天气
     * @param city
     * @return
     */
    @Override
    public RealTimeWeatherVo getRealTime(Citycode city) {
        RealTimeWeatherVo vo = new RealTimeWeatherVo();

        Weather weather = weatherFeignClient.todayWeather(city.getCity_ID());

        vo.setTemp(weather.getTemp());
        vo.setWeather(weather.getWeather());
        //vo.setWindDegree();
        vo.setWindDir(weather.getWd());
        vo.setWindPower(weather.getWdforce());
        vo.setWindSpeed(weather.getWdspd());
        vo.setHumidity(weather.getHumidity());
        vo.setPcpn(weather.getPrcp());
        vo.setAirPressure(weather.getStp());

        return vo;
    }

    /**
     * 获取生活指数
     * @param city
     * @return
     */
    @Override
    public LifeStyleVo getLifeStyle(Citycode city) {
        LifeStyleVo vo = new LifeStyleVo();

        Life life = weatherFeignClient.lifeWeather(city.getCity_CN());
        LifeGradeList lifeGradeList = life.getData();

        vo.setComf(assembleLifeStyleItemVo(lifeGradeList.getZs_ssd()));
        vo.setCw(assembleLifeStyleItemVo(lifeGradeList.getZs_xc()));
        vo.setDrsg(assembleLifeStyleItemVo(lifeGradeList.getZs_cy()));
        vo.setFlu(assembleLifeStyleItemVo(lifeGradeList.getZs_gm()));
        vo.setSport(assembleLifeStyleItemVo(lifeGradeList.getZs_yd()));
        vo.setUv(assembleLifeStyleItemVo(lifeGradeList.getZs_zwx()));

        return vo;
    }

    /**
     * 获取天气预告
     * @param city
     * @return
     */
    @Override
    public List<DailyForecastVo> getForecast(Citycode city) {
        List<DailyForecastVo> voList = Lists.newArrayList();

        Weather6D weather6D = weatherFeignClient.d6Weather(city.getCity_ID());
        List<Weather6Detail> weather6DetailList = weather6D.getData().getForecast();

        for (Weather6Detail item : weather6DetailList){
            DailyForecastVo forecastVo = new DailyForecastVo();
            forecastVo.setDate(item.getDate());
            forecastVo.setTempMax(item.getTemphigh());
            forecastVo.setTempMin(item.getTemplow());
            forecastVo.setWeatherNight(item.getWeather());
            forecastVo.setWeatherDay(item.getWeather());
//                forecastVo.setWindDegree();
            forecastVo.setWindDir(item.getWind());
            forecastVo.setWindPower(item.getWindforce());
//                forecastVo.setWindSpeed();
//                forecastVo.setHumidity();
//                forecastVo.setPcpn();
//                forecastVo.setAirPressure();

            voList.add(forecastVo);
        }

        return voList;
    }

    //region
    private LifeStyleItemVo assembleLifeStyleItemVo(LifeGrade lifeGrade){
        LifeStyleItemVo vo = new LifeStyleItemVo();

        vo.setTxt(lifeGrade.getInfo());

        return vo;
    }

    //endregion
}
