package top.trumandu.module.system.org.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import top.trumandu.common.domain.BaseEntity;

/**
 * @author Truman.P.Du
 * @date 2022/04/06
 * @description
 */
@TableName("sys_org")
public class SysOrgEntity extends BaseEntity {
    private String orgName;
    private String orgCode;
    private Long parentId;
    private String description;
    @TableField("create_user")
    private Long createUserId;
    @TableField("last_edit_user")
    private Long lastEditUserId;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
