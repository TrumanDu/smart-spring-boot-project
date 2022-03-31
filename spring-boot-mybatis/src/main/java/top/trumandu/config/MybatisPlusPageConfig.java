package top.trumandu.config;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Truman.P.Du
 * @date 2022/03/29
 * @description
 */
@Configuration
public class MybatisPlusPageConfig {

    /**
     * PageHelper的分页插件
     */
    @Bean
    public PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }
}
