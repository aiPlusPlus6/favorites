package com.aiplusplus.favorites.web.service.impl;

import com.aiplusplus.favorites.common.customizeException.BizException;
import com.aiplusplus.favorites.doman.dto.RoleAddDTO;
import com.aiplusplus.favorites.doman.entity.SysRole;
import com.aiplusplus.favorites.mapper.RoleMapper;
import com.aiplusplus.favorites.web.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月28日 16:22
 * @package com.aiplusplus.favorites.web.service.impl
 * @ClassName: RoleServiceImpl
 * @Description: TODO(描述)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, SysRole> implements RoleService {
    private final RoleMapper roleMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addRole(RoleAddDTO roleAddDTO) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleAddDTO,sysRole);
        Long l = roleMapper.selectCount(new LambdaQueryWrapper<SysRole>().eq(SysRole::getRoleName, roleAddDTO.getRoleName()));
        if (l > 0) {
            throw new BizException("角色名称已存在");
        }
        if (roleAddDTO.getRoleSort()!=null) {
            l = roleMapper.selectCount(new LambdaQueryWrapper<SysRole>().eq(SysRole::getRoleSort, roleAddDTO.getRoleSort()));
            if (l > 0) {
                throw new BizException("显示顺序已存在");
            }
        }else {
            sysRole.setRoleSort(roleMapper.selectMaxRoleSort());
        }
        sysRole.setVersion(0);
        sysRole.setDeleted(0);
        sysRole.setStatus(0);
        int insert = roleMapper.insert(sysRole);
        if (insert==0){
            log.error("新增角色失败,{}",roleAddDTO);
            throw new BizException("新增角色失败,请联系管理员");
        }
        return sysRole.getId().toString();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRole(String roleId, Integer version) {
        SysRole sysRole = roleMapper.selectById(roleId);
        if (sysRole==null){
            throw new BizException("角色不存在");
        }
        if (!version.equals(sysRole.getVersion())){
            throw new BizException("数据已更新请刷新后重试");
        }
        int i = roleMapper.deleteById(sysRole);
        if (i==0){
            throw new BizException("删除角色失败");
        }
    }
}
