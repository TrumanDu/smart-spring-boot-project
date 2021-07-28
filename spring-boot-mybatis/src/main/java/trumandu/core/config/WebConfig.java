package trumandu.core.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.trumandu.core.common.ResponseBodyWrapFactoryBean;
import top.trumandu.core.common.SessionAttr;
import top.trumandu.core.model.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Truman.P.Du
 * @date 2020/04/25
 * @description
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 用户未登录 /session超时
     * 拦截处理
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                // session超时，返回401
                HttpSession session = request.getSession(true);
                UserInfo userInfo = (UserInfo) session.getAttribute(SessionAttr.USER.getValue());
                if (userInfo == null) {
                    // TODO 自动登录逻辑
                    UserInfo user = null;
                    //UserInfo user = loginService.checkJwtAuth(request);
                    if (user == null) {
                        response.setStatus(401);
                        return false;
                    } else {
                        session.setAttribute(SessionAttr.USER.getValue(), user);
                    }
                }
                return true;
            }


        }).addPathPatterns("/api/**").excludePathPatterns("/api/login/**","/api/hello/**");
    }

    /**
     * 所有RestController注解暴露出来的接口增加统一前缀/api
     *
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
      configurer.addPathPrefix("/api", c -> c.isAnnotationPresent(RestController.class));
    }


    @Bean
    public ResponseBodyWrapFactoryBean getResponseBodyWrap() {
        return new ResponseBodyWrapFactoryBean();
    }


}
