package com.aiplusplus.favorites.web.service;

import com.aiplusplus.favorites.doman.dto.simple.weather.WeatherDTO;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月29日 17:51
 * @package com.aiplusplus.favorites.web.service
 * @ClassName: SimpleWeatherService
 * @Description: TODO(描述)
 */
public interface SimpleWeatherService {
    public void pullCityList();

    WeatherDTO getWeather(HttpServletRequest request, Integer cityId);

    void pullWeatherTypeList();

}
