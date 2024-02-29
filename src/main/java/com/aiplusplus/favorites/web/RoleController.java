package com.aiplusplus.favorites.web;

import com.aiplusplus.favorites.common.R;
import com.aiplusplus.favorites.web.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月28日 16:21
 * @package com.aiplusplus.favorites.web
 * @ClassName: RoleController
 * @Description: TODO(角色控制)
 */
@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/role")
public class RoleController {
    private final RoleService roleService;
    //新建角色
    //删除角色
    //修改角色
    //查询角色列表
    //查询角色详情
}
