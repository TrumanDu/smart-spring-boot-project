package top.trumandu.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.trumandu.common.domain.ResponseBodyWrapFactoryBean;

/**
 * @author Truman.P.Du
 * @date 2020/04/25
 * @description
 */
@SuppressWarnings("unused")
@Configuration
public class WebConfig implements WebMvcConfigurer {


    /**
     * 所有RestController注解暴露出来的接口增加统一前缀/api
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configure) {
        configure.addPathPrefix("/api", c -> c.isAnnotationPresent(RestController.class));
    }

    @Bean
    public ResponseBodyWrapFactoryBean getResponseBodyWrap() {
        return new ResponseBodyWrapFactoryBean();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
