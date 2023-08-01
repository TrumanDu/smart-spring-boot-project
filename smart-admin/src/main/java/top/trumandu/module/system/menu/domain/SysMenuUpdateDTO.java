package top.trumandu.module.system.menu.domain;

/**
 * @author Truman.P.Du
 * @date 2022/04/08
 * @description
 */
public class SysMenuUpdateDTO extends SysMenuBaseDTO{
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SysMenuUpdateDTO{" +
                "id=" + id +
                '}';
    }
}
