package top.trumandu.common.anno;

import java.lang.annotation.*;

/**
 * @author Truman.P.Du
 * @date 2022/04/04
 * @description 记录操作log注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String operation() default "";

    /**
     * 是否记录参数
     * @return
     */
    boolean params() default false;
}
