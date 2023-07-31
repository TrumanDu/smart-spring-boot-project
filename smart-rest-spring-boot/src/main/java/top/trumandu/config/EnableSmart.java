package top.trumandu.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Truman.P.Du
 * @date 2023/07/31
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({SmartConfig.class})
public @interface EnableSmart {
}
