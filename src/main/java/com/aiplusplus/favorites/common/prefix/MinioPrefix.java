package com.aiplusplus.favorites.common.prefix;

import io.minio.MinioClient;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月04日 15:50
 * @package com.aiplusplus.favorites.common.prefix
 * @ClassName: MinioPrefix
 * @Description: TODO(描述)
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioPrefix {
    private String bucketName;
    private String endpoint;
    private String accesskey;
    private String secretkey;
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accesskey, secretkey)
                .build();
    }
}
