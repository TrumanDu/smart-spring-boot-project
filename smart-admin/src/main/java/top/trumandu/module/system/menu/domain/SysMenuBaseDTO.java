package top.trumandu.module.system.menu.domain;

/**
 * @author Truman.P.Du
 * @date 2022/04/08
 * @description
 */
public class SysMenuBaseDTO {
    private String menuName;
    private String menuUrl;
    private String menuIcon;
    private Long sort;
    private Long parentId;
    private String description;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
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
        return "SysMenuBaseDTO{" +
                "menuName='" + menuName + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                ", menuIcon='" + menuIcon + '\'' +
                ", sort=" + sort +
                ", parentId=" + parentId +
                ", description='" + description + '\'' +
                '}';
    }
}
