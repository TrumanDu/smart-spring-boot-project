package top.trumandu.module.system.org.domain;

/**
 * @author Truman.P.Du
 * @date 2022/04/06
 * @description
 */
public class SysOrgUpdateDTO extends SysOrgBaseDTO{
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SysOrgUpdateDTO{" +
                "id=" + id +
                '}';
    }
}
