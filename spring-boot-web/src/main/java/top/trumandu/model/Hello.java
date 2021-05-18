package top.trumandu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Truman.P.Du
 * @date 2021/05/18
 * @description
 */
public class Hello {
    @NotNull
    @Size(min = 1, max = 10, message = "info length must in 1-10")
    private String info;

    @NotNull(message = "length 不能为null.")
    @Size(min = 1, max = 10, message = "length length must in 1-10")
    private String length;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
