package top.trumandu.module.system.role.domain;

import top.trumandu.module.system.menu.domain.SysMenuVO;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/11
 * @description
 */
public class RoleMenuVO {
    private List<SysMenuVO> menuVOList;
    private List<Long>  checkedKeys;

    public List<SysMenuVO> getMenuVOList() {
        return menuVOList;
    }

    public void setMenuVOList(List<SysMenuVO> menuVOList) {
        this.menuVOList = menuVOList;
    }

    public List<Long> getCheckedKeys() {
        return checkedKeys;
    }

    public void setCheckedKeys(List<Long> checkedKeys) {
        this.checkedKeys = checkedKeys;
    }

    @Override
    public String toString() {
        return "RoleMenuVO{" +
                "menuVOList=" + menuVOList +
                ", checkedKeys=" + checkedKeys +
                '}';
    }
}
