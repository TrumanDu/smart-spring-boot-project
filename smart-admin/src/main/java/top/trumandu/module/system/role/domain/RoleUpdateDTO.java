package top.trumandu.module.system.role.domain;

/**
 * @author Truman.P.Du
 * @date 2022/04/10
 * @description
 */
public class RoleUpdateDTO extends RoleBaseDTO{
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RoleUpdateDTO{" +
                "id=" + id +
                '}';
    }
}
