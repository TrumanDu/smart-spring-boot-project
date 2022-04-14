package top.trumandu.module.system.user.domain;

import top.trumandu.module.system.org.domain.SysOrgVO;

/**
 * @author Truman.P.Du
 * @date 2022/03/30
 * @description
 */
public class UserVO {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String description;

    private SysOrgVO sysOrgVO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SysOrgVO getSysOrgVO() {
        return sysOrgVO;
    }

    public void setSysOrgVO(SysOrgVO sysOrgVO) {
        this.sysOrgVO = sysOrgVO;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", sysOrgVO=" + sysOrgVO +
                '}';
    }
}
