package top.trumandu.module.system.role.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Truman.P.Du
 * @date 2022/04/10
 * @description
 */
public class RoleBaseDTO {
    @NotEmpty
    @Size(min = 2,max = 10,message = "roleCode length must in 2-10")
    private String roleCode;
    @NotEmpty
    @Size(min = 2,max = 10,message = "roleName length must in 2-10")
    private String roleName;
    private String description;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RoleBaseDTO{" +
                "roleCode='" + roleCode + '\'' +
                ", roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
