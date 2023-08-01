package top.trumandu.module.system.role.domain;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/11
 * @description
 */
public class RoleMenuDTO {
    private Long roleId;
    private List<Long>menuIds;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }

    @Override
    public String toString() {
        return "RoleMenuDTO{" +
                "roleId=" + roleId +
                ", menuIds=" + menuIds +
                '}';
    }
}
