package com.aiplusplus.favorites.web;

import com.aiplusplus.favorites.common.R;
import com.aiplusplus.favorites.web.service.SimpleWeatherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

}
