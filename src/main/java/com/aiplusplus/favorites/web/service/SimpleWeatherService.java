package com.aiplusplus.favorites.web.service;

import com.aiplusplus.favorites.doman.dto.simple.weather.WeatherDTO;
import com.aiplusplus.favorites.doman.entity.simple.SimpleCity;
import com.aiplusplus.favorites.doman.entity.simple.SimpleCityUser;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

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

    Object getLifeIndex(HttpServletRequest request, Integer cityId);

    void saveCity(Long id);

    List<SimpleCityUser> selectCityUserList();

    void deleteCityUser(Long id);

    List<SimpleCity> selectCityList(String name);
}
