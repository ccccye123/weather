package org.ccccye.weather.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.ccccye.weather.common.ServerResponse;
import org.ccccye.weather.dto.*;
import org.ccccye.weather.feign.CityCodeFeignClient;
import org.ccccye.weather.feign.WeatherFeignClient;
import org.ccccye.weather.service.WeatherService;
import org.ccccye.weather.vo.FutureWeatherVo;
import org.ccccye.weather.vo.WeatherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    private WeatherFeignClient weatherFeignClient;
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

        Citycode citycode = cityCodeFeignClient.queryByAdcode(adcode);
        if (citycode == null) {
            return ServerResponse.createByErrorMessage("获取城市编码失败");
        }

        Weather weather = getToday(citycode.getCity_ID());
        Life life = getLife(citycode.getCity_CN());
        Weather6D weather6D = getFuture6D(citycode.getCity_ID());

        WeatherVo weatherVo = assembleWeatherVo(weather, life, weather6D);

        return ServerResponse.createBySuccess(weatherVo);
    }

    /**
     * 获取实时天气
     * @param City_ID
     * @return
     */
    @Override
    public Weather getToday(String City_ID) {
        return weatherFeignClient.todayWeather(City_ID);
    }

    /**
     * 获取实时生活指数
     * @param City_CN
     * @return
     */
    @Override
    public Life getLife(String City_CN) {
        return weatherFeignClient.lifeWeather(City_CN);
    }

    /**
     * 获取最近6天天气
     * @param City_ID
     * @return
     */
    @Override
    public Weather6D getFuture6D(String City_ID) {
        return weatherFeignClient.d6Weather(City_ID);
    }


    //region 内部函数
    private WeatherVo assembleWeatherVo(Weather weather, Life life, Weather6D weather6D){
        WeatherVo vo = new WeatherVo();

        vo.setCityen(weather.getCityen());
        vo.setCity(weather.getCity());
        vo.setCitycode(weather.getCitycode());
        vo.setTemp(weather.getTemp());
        vo.setTempf(weather.getTempf());
        vo.setWd(weather.getWd());
        vo.setWden(weather.getWden());
        vo.setWdforce(weather.getWdforce());
        vo.setWdspd(weather.getWdspd());
        vo.setUptime(weather.getUptime());
        vo.setWeather(weather.getWeather());
        vo.setWeatheren(weather.getWeatheren());
        vo.setWeatherimg(weather.getWeatherimg());
        vo.setStp(weather.getStp());
        vo.setWisib(weather.getWisib());
        vo.setHumidity(weather.getHumidity());
        vo.setPrcp(weather.getPrcp());
        vo.setPrcp24h(weather.getPrcp24h());
        vo.setAqi(weather.getAqi());
        vo.setPm25(weather.getPm25());
        vo.setToday(weather.getToday());

        vo.setLifeInfo(life.getData());

        List<FutureWeatherVo> futureWeatherVoList = Lists.newArrayList();
        List<Weather6Detail> forecast = weather6D.getData().getForecast();
        for (int i=0;  i < forecast.size(); i++){
            if (i == 0){
                continue;
            }
            FutureWeatherVo futureWeatherVo = new FutureWeatherVo();
            futureWeatherVo.setDate(forecast.get(i).getDate());
            futureWeatherVo.setTemphigh(forecast.get(i).getTemphigh());
            futureWeatherVo.setTemplow(forecast.get(i).getTemplow());
            futureWeatherVo.setWeather(forecast.get(i).getWeather());
            futureWeatherVo.setWind(forecast.get(i).getWind());
            futureWeatherVo.setWindforce(forecast.get(i).getWindforce());

            futureWeatherVoList.add(futureWeatherVo);
        }

        vo.setFutureWeatherVo4D(futureWeatherVoList);
        return vo;
    }



    //endregion
}
