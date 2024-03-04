package com.aiplusplus.favorites.web;

import com.aiplusplus.favorites.common.R;
import com.aiplusplus.favorites.doman.dto.simple.weather.WeatherDTO;
import com.aiplusplus.favorites.doman.entity.simple.SimpleCity;
import com.aiplusplus.favorites.doman.entity.simple.SimpleCityUser;
import com.aiplusplus.favorites.web.service.SimpleWeatherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月29日 17:38
 * @package com.aiplusplus.favorites.web
 * @ClassName: SimpleWeatherController
 * @Description: TODO(天气管理)
 */
@Tag(name = "天气管理")
@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/simple/weather")
public class SimpleWeatherController {
    private final SimpleWeatherService simpleWeatherService;
    //拉取城市
    @GetMapping("/pullCityList")
    public R<String> pullCityList() {
        simpleWeatherService.pullCityList();
        return R.ok("拉取成功");
    }
    //拉取天气种类列表
    @GetMapping("/pullWeatherTypeList")
    public R<String> pullWeatherTypeList() {
        simpleWeatherService.pullWeatherTypeList();
        return R.ok("拉取成功");
    }
    //获取天气
    @GetMapping("/getWeather")
    public R<WeatherDTO> getWeather(HttpServletRequest request, @RequestParam(required = false) Integer cityId) {
        return R.ok(simpleWeatherService.getWeather(request,cityId));
    }

    //获取生活指数
    @GetMapping("/getLifeIndex")
    public R<Object> getLifeIndex(HttpServletRequest request, @RequestParam(required = false) Integer cityId) {
        return R.ok(simpleWeatherService.getLifeIndex(request,cityId));
    }

    //保存城市
    @PostMapping("/saveCity")
    public R<String> saveCity(@RequestParam Long id) {
        simpleWeatherService.saveCity(id);
        return R.ok("保存成功");
    }

    //查询城市保存信息
    @GetMapping("/selectCityUserList")
    public R<List<SimpleCityUser>> selectCityUserList() {
        return R.ok(simpleWeatherService.selectCityUserList());
    }
    //删除保存城市信息
    @PostMapping("/deleteCityUser")
    public R<String> deleteCityUser(@RequestParam Long id) {
        simpleWeatherService.deleteCityUser(id);
        return R.ok("删除成功");
    }
    //查询支持城市
    @GetMapping("/selectCityList")
    public R<List<SimpleCity>> selectCityList(@RequestParam(required = false,name = "name") String name) {
        return R.ok(simpleWeatherService.selectCityList(name));
    }
}
