package top.trumandu.module.system.menu.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/08
 * @description
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysMenuVO {
    private Long id;
    private String menuName;
    private String menuUrl;
    private String menuIcon;
    private Long sort;
    private Long parentId;
    private String description;

    private List<SysMenuVO> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<SysMenuVO> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenuVO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "SysMenuVO{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                ", menuIcon='" + menuIcon + '\'' +
                ", sort=" + sort +
                ", parentId=" + parentId +
                ", description='" + description + '\'' +
                ", children=" + children +
                '}';
    }
}
