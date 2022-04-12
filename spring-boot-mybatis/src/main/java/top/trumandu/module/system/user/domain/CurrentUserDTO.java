package top.trumandu.module.system.user.domain;

import top.trumandu.module.system.role.domain.RoleVO;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/12
 * @description
 */
public class CurrentUserDTO {
    private String name;
    private String username;
    private Long userId;
    private String email;
    private RoleVO role;
    private List<MenuData> menuDataList;

    public CurrentUserDTO() {
    }

    public CurrentUserDTO(Long userId, String name, String username, String email) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleVO getRole() {
        return role;
    }

    public void setRole(RoleVO role) {
        this.role = role;
    }

    public List<MenuData> getMenuDataList() {
        return menuDataList;
    }

    public void setMenuDataList(List<MenuData> menuDataList) {
        this.menuDataList = menuDataList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "CurrentUserDTO{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", userId=" + userId +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", menuDataList=" + menuDataList +
                '}';
    }


}
