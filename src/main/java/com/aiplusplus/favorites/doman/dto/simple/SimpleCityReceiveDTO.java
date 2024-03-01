package com.aiplusplus.favorites.doman.dto.simple;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月01日 10:52
 * @package com.aiplusplus.favorites.doman.dto.simple
 * @ClassName: SimpleCityReceiveDTO
 * @Description: TODO(描述)
 */

@Data
@Schema(name = "天气可用城市接收DTO")
public class SimpleCityReceiveDTO {
    @Schema(name = "id")
    private Integer id;
    @Schema(name = "省份")
    private String province;
    @Schema(name = "城市")
    private String city;
    @Schema(name = "区县")
    private String district;
}
