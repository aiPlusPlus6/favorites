package com.aiplusplus.favorites.config;

import com.aiplusplus.favorites.security.SecurityUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月28日 11:40
 * @package com.aiplusplus.favorites.config
 * @ClassName: MyMetaObjectHandler
 * @Description: TODO(描述)
 */
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 在插入时填充创建时间和创建者ID
        this.setFieldValByName("createDate", LocalDateTime.now(), metaObject);
        this.setFieldValByName("createBy", getCurrentUserId(), metaObject);
        this.setFieldValByName("updateDate", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateBy", getCurrentUserId(), metaObject);
        this.setFieldValByName("version",1,metaObject);
        this.setFieldValByName("deleted",0,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 在更新时填充修改时间和修改者ID
        this.setFieldValByName("updateDate", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateBy", getCurrentUserId(), metaObject);
    }

    // 获取当前用户的方法，根据您的应用逻辑来实现
    private Long getCurrentUserId() {
        // 这里需要您根据实际情况来获取当前用户的ID
        return SecurityUtils.getCurrentUsername()==null?1:SecurityUtils.getCurrentUsername().getId();
    }
}

