package top.trumandu.module.system.org.domain;

/**
 * @author Truman.P.Du
 * @date 2022/04/06
 * @description
 */
public class SysOrgBaseDTO {
    private String orgName;
    private String orgCode;
    private Long parentId;
    private String description;

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

    @Override
    public String toString() {
        return "SysOrgBaseDTO{" +
                "orgName='" + orgName + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", parentId=" + parentId +
                ", description='" + description + '\'' +
                '}';
    }
}
