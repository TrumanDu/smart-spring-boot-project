package top.trumandu.core.common;

import java.lang.annotation.*;

/**
 * @author Truman.P.Du
 * @date 2021/01/14
 * @description
 *   使用该注解可以避免返回统一格式（RestResult）
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreRestResult {
}
