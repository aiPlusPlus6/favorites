package com.aiplusplus.favorites.doman.entity;

import com.aiplusplus.favorites.doman.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月20日 9:25
 * @package com.aiplusplus.favorites.doman.entity
 * @ClassName: SysUser
 * @Description: TODO(描述)
 */
@Data
@TableName("sys_user")
@Schema(name = "系统用户实体类")
public class SysUser extends BaseEntity implements UserDetails {
    @Schema(name = "用户名")
    private String userName;
    @Schema(name = "密码")
    private String password;
    @Schema(name = "手机号码")
    private String phone;
    @Schema(name = "性别")
    private Integer gender;
    @Schema(name = "头像存储地址")
    private String avatar;
    @Schema(name = "个性签名")
    private String signature;
    @Schema(name = "0启用 1禁用")
    private Integer status;
    @Schema(name = "角色")
    @TableField(exist = false)
    private SysRole role;

    @Override//用户所拥有的权限，返回的列表中至少得有一个值，否则这个用户啥权限都没有
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getRoleName()));
    }

    @Override//实现UserDetails的getPassword方法，返回实体类的password
    public String getPassword() {
        return password;
    }

    @Override//这个方法是UserDetails中的方法，必须实现
    public String getUsername() {
        return userName;
    }

    public String getUserName(){//这个是mybatis-plus需要用到的方法
        return this.userName;
    }

    @Override//返回true，代表用户账号没过期
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override//返回true，代表用户账号没被锁定
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override//返回true，代表用户密码没有过期
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override//返回true，代表用户账号还能够使用
    public boolean isEnabled() {
        return true;
    }
}
