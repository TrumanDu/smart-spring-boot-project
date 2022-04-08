package top.trumandu.module.system.menu.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import top.trumandu.common.domain.BaseEntity;

/**
 * @author Truman.P.Du
 * @date 2022/04/08
 * @description
 */
@TableName("sys_menu")
public class SysMenuEntity extends BaseEntity {
    private String menuName;
    private String menuUrl;
    private String menuIcon;
    private Long sort;
    @TableField("parent_id")
    private Long parentId;
    private String description;
    @TableField("create_user")
    private Long createUserId;
    @TableField("last_edit_user")
    private Long lastEditUserId;

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
