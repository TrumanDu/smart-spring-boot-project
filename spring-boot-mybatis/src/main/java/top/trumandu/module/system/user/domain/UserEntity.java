package top.trumandu.module.system.user.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import top.trumandu.common.domain.BaseEntity;

import java.io.Serializable;

/**
 * @author Truman.P.Du
 * @date 2022/03/30
 * @description
 */
@TableName("sys_user")
public class UserEntity extends BaseEntity implements Serializable {


    private static final long serialVersionUID = -9177168268823678532L;


    private String username;
    private String password;
    private String name;
    private String email;
    private String description;
    /**
     * 用戶是否刪除
     */
    private Integer delFlag;

    private Long orgId;
    @TableField("create_user")
    private Long createUserId;
    @TableField("last_edit_user")
    private Long lastEditUserId;

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

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getLastEditUserId() {
        return lastEditUserId;
    }

    public void setLastEditUserId(Long lastEditUserId) {
        this.lastEditUserId = lastEditUserId;
    }
}
