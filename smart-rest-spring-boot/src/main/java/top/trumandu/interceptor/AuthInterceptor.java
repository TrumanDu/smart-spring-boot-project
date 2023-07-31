package top.trumandu.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.trumandu.common.anno.AuthIgnore;
import top.trumandu.common.domain.SessionAttr;
import top.trumandu.common.domain.UserInfo;


/**
 * @author Truman.P.Du
 * @date 2022/04/04
 * @description
 */
public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (handler instanceof HandlerMethod) {
            boolean isNoNeedAuth = ((HandlerMethod) handler).getMethodAnnotation(AuthIgnore.class) != null;
            if (isNoNeedAuth) {
                return true;
            }
            // session超时，返回401
            HttpSession session = request.getSession(true);
            UserInfo loginUserVO = (UserInfo) session.getAttribute(SessionAttr.USER.getValue());
            if (loginUserVO == null) {
                //TODO 重新登录
            }
        }

        return true;
    }
}
