package top.trumandu.module.system.user.domain;

/**
 * @author Truman.P.Du
 * @date 2022/03/30
 * @description
 */
public class UserUpdateDTO extends UserBaseDTO{
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
