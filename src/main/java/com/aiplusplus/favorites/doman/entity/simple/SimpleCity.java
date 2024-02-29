package com.aiplusplus.favorites.doman.entity.simple;

import com.aiplusplus.favorites.doman.entity.base.DateEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月29日 17:49
 * @package com.aiplusplus.favorites.doman.entity.simple
 * @ClassName: SimpleCity
 * @Description: TODO(天气支持城市表)
 */
@Data
@Schema(name = "天气支持城市表")
public class SimpleCity extends DateEntity {
    @Schema(name = "天气城市id")
    private Integer simpleCityId;
    @Schema(name = "省")
    private String province;
    @Schema(name = "市")
    private String city;
    @Schema(name = "区")
    private String district;
}
