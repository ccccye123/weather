package org.ccccye.weather.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.ccccye.weather.dto.*;
import org.ccccye.weather.dto.he.Forecast;
import org.ccccye.weather.dto.he.HeWeather;
import org.ccccye.weather.dto.he.Lifestyle;
import org.ccccye.weather.dto.he.Now;
import org.ccccye.weather.feign.HeWeatherFeignClient;
import org.ccccye.weather.service.ExtWeatherService;
import org.ccccye.weather.vo.DailyForecastVo;
import org.ccccye.weather.vo.LifeStyleItemVo;
import org.ccccye.weather.vo.LifeStyleVo;
import org.ccccye.weather.vo.RealTimeWeatherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtHeWeatherServiceImpl implements ExtWeatherService {
    /**
     * 和风天气feign
     */
    @Autowired
    private HeWeatherFeignClient heWeatherFeignClient;

    /**
     * 和风天气秘钥
     */
    @Value("${weather.he.key}")
    private String heWeatherKey;

    /**
     * 获取实时天气
     * @param city
     * @return
     */
    @Override
    public RealTimeWeatherVo getRealTime(Citycode city) {
        RealTimeWeatherVo vo = new RealTimeWeatherVo();

        HeWeather heWeather = heWeatherFeignClient.request("now", city.getCity_CN(), heWeatherKey);
        Now heNow = heWeather.getHeWeather6().get(0).getNow();

        vo.setTemp(heNow.getTmp());
        vo.setWeather(heNow.getCond_txt());
        vo.setWindDegree(heNow.getWind_deg());
        vo.setWindDir(heNow.getWind_dir());
        vo.setWindPower(heNow.getWind_sc());
        vo.setWindSpeed(heNow.getWind_spd());
        vo.setHumidity(heNow.getHum());
        vo.setPcpn(heNow.getPcpn());
        vo.setAirPressure(heNow.getPres());

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

        HeWeather heWeather = heWeatherFeignClient.request("lifestyle", city.getCity_CN(), heWeatherKey);
        List<Lifestyle> lifestyleList = heWeather.getHeWeather6().get(0).getLifestyle();

        vo.setComf(assembleLifeStyleItemVo(findLife(lifestyleList, "comf")));
        vo.setCw(assembleLifeStyleItemVo(findLife(lifestyleList, "cw")));
        vo.setDrsg(assembleLifeStyleItemVo(findLife(lifestyleList, "drsg")));
        vo.setFlu(assembleLifeStyleItemVo(findLife(lifestyleList, "flu")));
        vo.setSport(assembleLifeStyleItemVo(findLife(lifestyleList, "sport")));
        vo.setUv(assembleLifeStyleItemVo(findLife(lifestyleList, "uv")));


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

        HeWeather heWeather = heWeatherFeignClient.request("forecast", city.getCity_CN(), heWeatherKey);
        List<Forecast> forecastList = heWeather.getHeWeather6().get(0).getDaily_forecast();
        for (Forecast item : forecastList){
            DailyForecastVo forecastVo = new DailyForecastVo();
            forecastVo.setDate(item.getDate());
            forecastVo.setTempMax(item.getTmp_max());
            forecastVo.setTempMin(item.getTmp_min());
            forecastVo.setWeatherNight(item.getCond_txt_n());
            forecastVo.setWeatherDay(item.getCond_txt_d());
            forecastVo.setWindDegree(item.getWind_deg());
            forecastVo.setWindDir(item.getWind_dir());
            forecastVo.setWindPower(item.getWind_sc());
            forecastVo.setWindSpeed(item.getWind_spd());
            forecastVo.setHumidity(item.getHum());
            forecastVo.setPcpn(item.getPcpn());
            forecastVo.setAirPressure(item.getPres());

            voList.add(forecastVo);
        }

        return voList;
    }

    //region 内部函数
    private LifeStyleItemVo assembleLifeStyleItemVo(Lifestyle lifestyle){
        LifeStyleItemVo vo = new LifeStyleItemVo();

        vo.setTxt(lifestyle.getTxt());

        return vo;
    }

    private Lifestyle findLife(List<Lifestyle> lifestyleList, String key){
        if (Strings.isNullOrEmpty(key)){
            return null;
        }
        Lifestyle result = null;

        for (Lifestyle item : lifestyleList){
            if (key.equals(item.getType())){
                result = item;
                break;
            }
        }

        return result;
    }
    //endregion
}
