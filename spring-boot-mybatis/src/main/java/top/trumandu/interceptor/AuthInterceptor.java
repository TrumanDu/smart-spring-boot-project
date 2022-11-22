package top.trumandu.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.trumandu.common.anno.AuthIgnore;
import top.trumandu.common.domain.SessionAttr;
import top.trumandu.module.system.login.LoginService;
import top.trumandu.module.system.login.domain.LoginUserVO;
import top.trumandu.util.SmartCurrentUserUtil;


/**
 * @author Truman.P.Du
 * @date 2022/04/04
 * @description
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {


    private LoginService loginService;

    @Autowired
    public AuthInterceptor(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            Boolean isNoNeedAuth = ((HandlerMethod) handler).getMethodAnnotation(AuthIgnore.class) != null;
            if (isNoNeedAuth) {
                return true;
            }
            // session超时，返回401
            HttpSession session = request.getSession(true);
            LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute(SessionAttr.USER.getValue());
            if (loginUserVO == null) {
                LoginUserVO user = loginService.reLogin(request);
                if (user == null) {
                    response.setStatus(401);
                    response.getWriter().print("{\"code\":401,\"message\":\"No auth.Please log in again.\"}");
                    return false;
                } else {
                    session.setAttribute(SessionAttr.USER.getValue(), user);
                }
            }
            SmartCurrentUserUtil.setCurrentUser(request, loginUserVO);
        }

        return true;
    }
}
