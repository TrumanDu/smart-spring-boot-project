package top.trumandu.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.trumandu.common.domain.SessionAttr;
import top.trumandu.module.system.login.domain.LoginUserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Truman.P.Du
 * @date 2022/04/04
 * @description
 */
public class SmartCurrentUserUtil {
    private static ThreadLocal<LoginUserVO> requestUserThreadLocal = new ThreadLocal<LoginUserVO>();

    /**
     * 获取当前用户，仅能在controller中获取
     *
     * @return
     */
    public static LoginUserVO getCurrentUser() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            if (request != null) {
                HttpSession session = request.getSession();
                Object userObj = session.getAttribute(SessionAttr.USER.getValue());
                return (LoginUserVO) userObj;
            }
        }
        return null;
    }

    public static void setCurrentUser(HttpServletRequest request, LoginUserVO currentUserVO) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttr.USER.getValue(), currentUserVO);
        requestUserThreadLocal.set(currentUserVO);
    }

    public static void removeCurrentUser() {
        requestUserThreadLocal.remove();
    }

    public static LoginUserVO getThreadLocalUserVO() {
        return requestUserThreadLocal.get();
    }

    public static Long getCurrentUserId() {
        LoginUserVO requestUser = getCurrentUser();
        if (null == requestUser) {
            return null;
        }
        return requestUser.getId();
    }


}
