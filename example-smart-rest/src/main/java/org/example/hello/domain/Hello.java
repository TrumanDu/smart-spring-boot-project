package org.example.hello.domain;

import jakarta.validation.constraints.*;

/**
 * @author Truman.P.Du
 * @date 2021/05/18
 * @description
 */
public class Hello {
    @NotNull
    @Size(min = 1, max = 10, message = "info length must in 1-10")
    private String info;
    @NotEmpty(message = "name must not be empty.")
    private String name;

    @NotNull(message = "length 不能为null.")
    @Min(1)
    @Max(12)
    private int length;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
