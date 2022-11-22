package top.trumandu.common.anno;

import java.lang.annotation.*;

/**
 * @author Truman.P.Du
 * @date 2022/04/04
 * @description 不需要权限校验注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthIgnore {
}
