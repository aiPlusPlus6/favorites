package com.aiplusplus.favorites.doman.dto.simple.weather;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月02日 10:15
 * @package com.aiplusplus.favorites.doman.dto.simple.weather
 * @ClassName: RealtimeDTO
 * @Description: TODO(描述)
 */

@Data
@Schema(name = "天气实况")
public class RealtimeDTO {

    /**
     * temperature : 4
     * humidity : 82
     * info : 阴
     * wid : 02
     * direct : 西北风
     * power : 3级
     * aqi : 80
     */
    @Schema(name = "温度")
    private String temperature;
    @Schema(name = "湿度")
    private String humidity;
    @Schema(name = "天气情况")
    private String info;
    @Schema(name = "天气种类id")
    private String wid;
    @Schema(name = "风向")
    private String direct;
    @Schema(name = "风力")
    private String power;
    @Schema(name = "空气质量指数")
    private String aqi;

}
