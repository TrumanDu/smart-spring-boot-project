package top.trumandu.common.anno;

import org.springframework.context.annotation.Import;
import top.trumandu.config.SmartConfig;

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
