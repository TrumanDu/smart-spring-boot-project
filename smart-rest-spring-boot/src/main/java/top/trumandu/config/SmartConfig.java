package top.trumandu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import top.trumandu.common.exception.ExceptionHandle;
import top.trumandu.util.RestClient;

/**
 * @author Truman.P.Du
 * @date 2023/07/31
 * @description
 */
@Configuration
@ComponentScan("top.trumandu.config")
@Import({RestClient.class, ExceptionHandle.class})
public class SmartConfig {
}
