package org.ccccye.weather.api.source.service.impl;

//import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.ccccye.weather.api.source.api.HeWeatherApiService;
import org.ccccye.weather.common.dto.Citycode;
import org.ccccye.weather.common.dto.he.Forecast;
import org.ccccye.weather.common.dto.he.HeWeather;
import org.ccccye.weather.common.dto.he.Lifestyle;
import org.ccccye.weather.common.dto.he.Now;
import org.ccccye.weather.api.source.service.ExtWeatherService;
import org.ccccye.weather.common.vo.DailyForecastVo;
import org.ccccye.weather.common.vo.LifeStyleItemVo;
import org.ccccye.weather.common.vo.LifeStyleVo;
import org.ccccye.weather.common.vo.RealTimeWeatherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtHeWeatherServiceImpl implements ExtWeatherService {
    /**
     * 和风天气接口
     */
    @Autowired
    private HeWeatherApiService heWeatherApiService;

    /**
     * 和风天气秘钥
     */
    @Value("${weather.he.key}")
//    @NacosValue(value = "${weather.he.key}", autoRefreshed = false)
    private String heWeatherKey;


    /**
     * 获取实时天气
     * @param city
     * @return
     */
    @Override
    public RealTimeWeatherVo getRealTime(Citycode city) {
        RealTimeWeatherVo vo = new RealTimeWeatherVo();

        HeWeather heWeather = heWeatherApiService.request("now", city.getCity_CN(), heWeatherKey);
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

        HeWeather heWeather = heWeatherApiService.request("lifestyle", city.getCity_CN(), heWeatherKey);
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

        HeWeather heWeather = heWeatherApiService.request("forecast", city.getCity_CN(), heWeatherKey);
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
