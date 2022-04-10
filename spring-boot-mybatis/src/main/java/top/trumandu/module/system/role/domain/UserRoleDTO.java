package top.trumandu.module.system.role.domain;

import javax.validation.constraints.NotNull;

/**
 * @author Truman.P.Du
 * @date 2022/04/10
 * @description
 */
public class UserRoleDTO {
    @NotNull
    private Long roleId;
    @NotNull
    private Long userId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserRoleDTO{" +
                "roleId=" + roleId +
                ", userId=" + userId +
                '}';
    }
}
