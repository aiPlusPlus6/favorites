package com.aiplusplus.favorites.common.prefix;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月01日 9:11
 * @package com.aiplusplus.favorites.common.prefix
 * @ClassName: SimpleWeatherPrefix
 * @Description: TODO(描述)
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "simpleweather")
public class SimpleWeatherPrefix {
    @Schema(name = "根据城市查询天气")
    private String query;
    @Schema(name = "根据城市查询生活指数")
    private String life;
    @Schema(name = "天气种类列表")
    private String wids;
    @Schema(name = "支持城市列表")
    private String citylist;
    @Schema(name = "apiKey")
    private String apikey;
}
