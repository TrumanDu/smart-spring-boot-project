package top.trumandu.module.system.user.domain;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * @author Truman.P.Du
 * @date 2022/03/30
 * @description
 */
public class UserBaseDTO {
    @NotEmpty(message = "username must not be empty.")
    @Size(min = 4,max = 20,message = "username length must in 5-20")
    private String username;
    @Size(min = 4,max = 20,message = "password length must in 5-20")
    @NotEmpty(message = "password must not be empty.")
    private String password;
    @Size(min = 5,max = 20,message = "name length must in 5-20")
    @NotEmpty(message = "name must not be empty.")
    private String name;
    private String email;
    private String description;

    private Long orgId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        return "UserBaseDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", orgId=" + orgId +
                '}';
    }
}
