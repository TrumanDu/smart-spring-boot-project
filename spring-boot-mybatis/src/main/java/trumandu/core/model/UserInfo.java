package trumandu.core.model;

/**
 * @author Truman.P.Du
 * @date 2021/03/20
 * @description
 */

public class UserInfo {
    private Long id;
    private String name;
    private String realName;



    public UserInfo() {
    }

    public UserInfo(String name, String realName) {
        this.name = name;
        this.realName = realName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}

