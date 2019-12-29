package org.ccccye.weather.service.impl;

import com.google.common.base.Strings;
import org.ccccye.weather.common.ServerResponse;
import org.ccccye.weather.dto.*;
import org.ccccye.weather.feign.CityCodeFeignClient;
import org.ccccye.weather.service.ExtWeatherService;
import org.ccccye.weather.service.WeatherService;
import org.ccccye.weather.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 天气服务实现类
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    /**
     * 城市编码接口
     */
    @Autowired
    private CityCodeFeignClient cityCodeFeignClient;

    /**
     * 天气数据接口
     */
    private static ExtWeatherService extWeatherService;
    /**
     * 和风天气接口
     */
    @Autowired
    private ExtHeWeatherServiceImpl extHeWeatherService;
    /**
     * 帮助之家天气接口
     */
    @Autowired
    private ExtHelpWeatherServiceImpl extHelpWeatherService;

    /**
     * 天气类型
     */
    @Value("${weather.type}")
    private String typeWeather;


    /**
     * 天气数据源配置
     */
    @PostConstruct
    private void init(){
        if ("he".equals(typeWeather)){
            extWeatherService = extHeWeatherService;
        }else{
            extWeatherService = extHelpWeatherService;
        }
    }


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
     * @param city
     * @return
     */
    @Override
    public RealTimeWeatherVo getRealTime(Citycode city) {
        return extWeatherService.getRealTime(city);
    }

    /**
     * 获取生活指数
     * @param city
     * @return
     */
    @Override
    public LifeStyleVo getLifeStyle(Citycode city) {
        return extWeatherService.getLifeStyle(city);
    }

    /**
     * 获取天气预告
     * @param city
     * @return
     */
    @Override
    public List<DailyForecastVo> getForecast(Citycode city) {
        return extWeatherService.getForecast(city);
    }

    //region 内部函数
    private WeatherVo assembleWeatherVo(RealTimeWeatherVo realtime, LifeStyleVo life, List<DailyForecastVo> dailyForecastVoList, Citycode city){
        WeatherVo vo = new WeatherVo();

        vo.setToday(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
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
