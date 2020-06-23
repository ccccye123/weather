package org.ccccye.weather.common.dto.he;



import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class HeWeather {

    private List<HeWeatherInfo> HeWeather6;


    public List<HeWeatherInfo> getHeWeather6() {
        return HeWeather6;
    }

    @JsonProperty("HeWeather6")
    public void setHeWeather6(List<HeWeatherInfo> heWeather6) {
        HeWeather6 = heWeather6;
    }
}
