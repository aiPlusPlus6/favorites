package com.aiplusplus.favorites.doman.dto.simple.weather;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月02日 10:21
 * @package com.aiplusplus.favorites.doman.dto.simple.weather
 * @ClassName: WidBean
 * @Description: TODO(描述)
 */
@Data
@Schema(name = "天气种类")
public class WidBean {
    @Schema(name = "白天天气标识")
    private String day;
    @Schema(name = "夜晚天气标识")
    private String night;
    @Schema(name = "白天天气")
    private String dayWeather;
    @Schema(name = "夜晚天气")
    private String nightWeather;
}
