package com.aiplusplus.favorites.doman.dto.simple.weather;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月02日 10:19
 * @package com.aiplusplus.favorites.doman.dto.simple.weather
 * @ClassName: FutureDTO
 * @Description: TODO(描述)
 */
@Data
@Schema(name = "天气预报DTO")
public class FutureDTO {

    /**
     * date : 2019-02-22
     * temperature : 1/7℃
     * weather : 小雨转多云
     * wid : {"day":"07","night":"01"}
     * direct : 北风转西北风
     */
    @Schema(name = "日期")
    private String date;
    @Schema(name = "温度")
    private String temperature;
    @Schema(name = "天气情况")
    private String weather;
    @Schema(name = "天气种类")
    private WidBean wid;
    @Schema(name = "风向")
    private String direct;
}
