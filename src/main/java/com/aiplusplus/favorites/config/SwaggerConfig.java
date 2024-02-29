package com.aiplusplus.favorites.config;



import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author 李俊杰
 * {@code @date} 2024年02月20日 9:47
 * @package com.aiplusplus.favorites.config
 * @ClassName: SwaggerConfig
 * @Description: TODO(swagger3)
 */
//
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("spring-projects-favorites")
                .packagesToScan("com.aiplusplus.favorites")
                .build();
    }
}
