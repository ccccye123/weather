package org.ccccye.weather.api.wx.service.impl;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.google.common.base.Strings;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ccccye.weather.common.ServerResponse;
import org.ccccye.weather.common.dto.*;
import org.ccccye.weather.api.source.feign.CityCodeFeignClient;
import org.ccccye.weather.api.wx.service.ExtWeatherService;
import org.ccccye.weather.api.wx.service.WeatherService;
import org.ccccye.weather.common.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CityCodeFeignClient cityCodeFeignClient;

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
    @NacosValue(value = "${weather.type:he}", autoRefreshed = true)
    private String typeWeather;

    /**
     * 获取第三方天气数据源
     * @return
     */
    private ExtWeatherService getExtWeatherService(){
        if ("he".equals(typeWeather)){
            return extHeWeatherService;
        }else{
            return extHelpWeatherService;
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

        RealTimeWeatherVo realTimeWeatherVo = getExtWeatherService().getRealTime(city);
        LifeStyleVo lifeStyleVo = getExtWeatherService().getLifeStyle(city);
        List<DailyForecastVo> dailyForecastVoList = getExtWeatherService().getForecast(city);

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
