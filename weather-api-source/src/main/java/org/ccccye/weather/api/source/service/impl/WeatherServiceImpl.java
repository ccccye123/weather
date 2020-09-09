package org.ccccye.weather.api.source.service.impl;

//import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.google.common.base.Strings;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ccccye.weather.api.source.api.CityCodeApiService;
import org.ccccye.weather.common.ServerResponse;
import org.ccccye.weather.common.dto.*;
import org.ccccye.weather.api.source.service.ExtWeatherService;
import org.ccccye.weather.api.source.service.WeatherService;
import org.ccccye.weather.common.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 天气服务实现类
 */
@Service
public class WeatherServiceImpl implements WeatherService {
    private final static Log logger = LogFactory.getLog(WeatherServiceImpl.class);

    /**
     * 城市编码接口
     */
    @Autowired
    private CityCodeApiService cityCodeApiService;

    /**
     * 和风天气接口
     */
    @Autowired
    private ExtHeWeatherServiceImpl extHeWeatherService;
    /**
     * 帮助之家天气接口
     */
//    @Autowired
//    private ExtHelpWeatherServiceImpl extHelpWeatherService;

    /**
     * 天气类型
     */
//    @NacosValue(value = "${weather.type:he}", autoRefreshed = true)
    @Value(value = "${weather.type:he}")
    private String typeWeather;

    /**
     * 获取第三方天气数据源
     * @return
     */
    private ExtWeatherService getExtWeatherService(){
        return extHeWeatherService;

//        if ("he".equals(typeWeather)){
//            return extHeWeatherService;
//        }else{
//            return extHelpWeatherService;
//        }
    }

    private static int testID = 0;
    /**
     * 根据adcode获取天气数据
     * @param adcode
     * @return
     */
    @Override
    @Cacheable(value = "cacheTest#60",key = "#adcode")
    public WeatherVo getWeatherInfo(String adcode) {
        System.out.println(adcode);
        WeatherVo weather = new WeatherVo();
        weather.setAdCode(adcode);
        weather.setCityID(String.valueOf(testID));
        testID++;
        return weather;

//        if (Strings.isNullOrEmpty(adcode)){
////            return ServerResponse.createByErrorMessage("Adcode不能为空");
//            return null;
//        }
//
//        Citycode city = cityCodeApiService.queryByAdcode(adcode);
//        if (city == null) {
////            return ServerResponse.createByErrorMessage("获取城市编码失败");
//            return null;
//        }
//
//        RealTimeWeatherVo realTimeWeatherVo = getExtWeatherService().getRealTime(city);
//        LifeStyleVo lifeStyleVo = getExtWeatherService().getLifeStyle(city);
//        List<DailyForecastVo> dailyForecastVoList = getExtWeatherService().getForecast(city);
//
//        WeatherVo weatherVo = assembleWeatherVo(realTimeWeatherVo, lifeStyleVo, dailyForecastVoList, city);
//
//        return weatherVo;
    }

    /**
     * 获取实时天气
     * @param city
     * @return
     */
    @Override
    public RealTimeWeatherVo getRealTime(Citycode city) {
        return getExtWeatherService().getRealTime(city);
    }

    /**
     * 获取生活指数
     * @param city
     * @return
     */
    @Override
    public LifeStyleVo getLifeStyle(Citycode city) {
        return getExtWeatherService().getLifeStyle(city);
    }

    /**
     * 获取天气预告
     * @param city
     * @return
     */
    @Override
    public List<DailyForecastVo> getForecast(Citycode city) {
        return getExtWeatherService().getForecast(city);
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
