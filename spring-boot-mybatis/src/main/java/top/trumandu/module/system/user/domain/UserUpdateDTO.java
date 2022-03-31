package top.trumandu.module.system.user.domain;

import javax.validation.constraints.NotEmpty;

/**
 * @author Truman.P.Du
 * @date 2022/03/30
 * @description
 */
public class UserUpdateDTO extends UserBaseDTO{
    @NotEmpty
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserUpdateDTO{" +
                "id=" + id +
                '}';
    }
}
