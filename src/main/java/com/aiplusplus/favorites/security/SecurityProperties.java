package com.aiplusplus.favorites.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月28日 13:47
 * @package com.aiplusplus.favorites.security
 * @ClassName: SecurityProperties
 * @Description: TODO(描述)
 */

@Configuration
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    private List<String> ignoredPaths;

    public List<String> getIgnoredPaths() {
        return ignoredPaths;
    }

    public void setIgnoredPaths(List<String> ignoredPaths) {
        this.ignoredPaths = ignoredPaths;
    }
}
