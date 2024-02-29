package com.aiplusplus.favorites.web;

import com.aiplusplus.favorites.common.R;
import com.aiplusplus.favorites.doman.dto.RoleAddDTO;
import com.aiplusplus.favorites.web.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月28日 16:21
 * @package com.aiplusplus.favorites.web
 * @ClassName: RoleController
 * @Description: TODO(角色控制)
 */
@Tag(name = "角色管理")
@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService roleService;
    //新建角色

    @PostMapping("/add")
    @Operation(summary = "新增角色")
    private R<String> addRole(@RequestBody @Validated RoleAddDTO roleAddDTO) {
        return R.ok(roleService.addRole(roleAddDTO));
    }
    //删除角色
    @PostMapping("/delete/{roleId}")
    @Operation(summary = "删除角色")
    @Parameters({
            @Parameter(name = "roleId", description = "角色ID", required = true),
            @Parameter(name = "version", description = "版本号", required = true)
    })
    private R<String> deleteRole(@PathVariable String roleId,@RequestParam Integer version) {
        roleService.deleteRole(roleId,version);
        return R.ok("删除成功");
    }
    //修改角色
    //查询角色列表
    //查询角色详情
}
