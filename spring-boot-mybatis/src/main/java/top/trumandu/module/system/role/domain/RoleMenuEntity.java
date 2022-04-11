package top.trumandu.module.system.role.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import top.trumandu.common.domain.BaseEntity;

/**
 * @author Truman.P.Du
 * @date 2022/04/11
 * @description
 */
@TableName("sys_role_menu")
public class RoleMenuEntity extends BaseEntity {
    @TableField("role_id")
    private Long roleId;
    @TableField("menu_id")
    private Long menuId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
