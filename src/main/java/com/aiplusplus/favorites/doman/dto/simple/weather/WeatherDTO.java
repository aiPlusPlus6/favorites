package com.aiplusplus.favorites.doman.dto.simple.weather;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月02日 10:12
 * @package com.aiplusplus.favorites.doman.dto.simple.weather
 * @ClassName: WeatherDTO
 * @Description: TODO(描述)
 */

@Data
@Schema(name = "天气接收")
public class WeatherDTO {
    @Schema(name = "城市")
    private String city;
    @Schema(name = "天气实况")
    private RealtimeDTO realtime;
    @Schema(name = "近5日天气")
    private List<FutureDTO> future;
}
