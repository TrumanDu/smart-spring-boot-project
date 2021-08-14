package top.trumandu.common.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

/**
 * @author Truman.P.Du
 * @date 2021/08/14
 * @description
 */
public class BaseEntity {
    @TableId(type = IdType.AUTO)
    public Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public void setInUser(String inUser) {
        this.inUser = inUser;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public void setLastEditUser(String lastEditUser) {
        this.lastEditUser = lastEditUser;
    }

    public void setLastEditDate(Date lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public String inUser;
    public Date inDate;
    public String lastEditUser;
    public Date lastEditDate;
}
