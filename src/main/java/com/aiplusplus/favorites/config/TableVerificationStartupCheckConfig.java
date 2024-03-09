package com.aiplusplus.favorites.config;

import com.aiplusplus.favorites.common.customizeException.BizException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月09日 14:44
 * @package com.aiplusplus.favorites.config
 * @ClassName: TableVerificationStartupCheckConfig
 * @Description: TODO(项目启动表名校验)
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class TableVerificationStartupCheckConfig {
    private final DataSource dataSource;
    @EventListener(ApplicationReadyEvent.class)
    public void checkTablesExistence() throws Exception {

        // 假设有一个方法getAnnotatedTableNames()用于获取所有使用@TableName注解的表名
        Set<String> tableNames = getAnnotatedTableNames();
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData dbMetaData = connection.getMetaData();
            Set<String> missingTables = new HashSet<>(tableNames);
            try (ResultSet rs = dbMetaData.getTables(null, null, "%", new String[]{"TABLE"})) {
                while (rs.next()) {
                    String tableName = rs.getString("TABLE_NAME").toLowerCase();
                    missingTables.remove(tableName);
                }
            }

            if (!missingTables.isEmpty()) {
                System.err.println("以下表不存在: " + String.join(", ", missingTables));
                log.error("以下表不存在: " + String.join(", ", missingTables));
                throw new RuntimeException("以下表不存在: " + String.join(", ", missingTables));
                // 可以在这里抛出异常或进行其他错误处理
            } else {
                System.out.println("所有标注的表都已经验证存在。");
            }
        }
    }
    private Set<String> getAnnotatedTableNames() throws Exception{
        Set<String> tableNames = new HashSet<>();
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(TableName.class));

        // 假设你的实体类都在这个包下，根据实际情况调整
        String basePackage = "com.aiplusplus.favorites.doman.entity";
        for (var beanDefinition : provider.findCandidateComponents(basePackage)) {
            Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());
            TableName tableNameAnnotation = clazz.getAnnotation(TableName.class);
            if (tableNameAnnotation != null) {
                tableNames.add(tableNameAnnotation.value());
            }
        }
        return tableNames;
    }
}
