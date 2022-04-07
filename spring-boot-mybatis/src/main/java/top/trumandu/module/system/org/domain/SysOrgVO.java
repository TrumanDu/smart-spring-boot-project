package top.trumandu.module.system.org.domain;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/06
 * @description
 */
public class SysOrgVO {
    private Long id;
    private String orgName;
    private String orgCode;
    private String description;
    private Long parentId;
    private List<SysOrgVO> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SysOrgVO> getChildren() {
        return children;
    }

    public void setChildren(List<SysOrgVO> children) {
        this.children = children;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "SysOrgVO{" +
                "id=" + id +
                ", orgName='" + orgName + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", description='" + description + '\'' +
                ", parentId=" + parentId +
                ", children=" + children +
                '}';
    }
}
