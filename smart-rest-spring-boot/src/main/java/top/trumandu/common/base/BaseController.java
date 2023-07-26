package top.trumandu.common.base;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.trumandu.common.domain.SessionAttr;
import top.trumandu.common.domain.UserInfo;

import java.util.Objects;

/**
 * @author Truman.P.Du
 * @date 2020/04/25
 * @description
 */
@SuppressWarnings("unused")
public class BaseController {


    public UserInfo getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
        HttpSession session = request.getSession();
        UserInfo userInfo = null;
        Object userObj = session.getAttribute(SessionAttr.USER.getValue());
        if (userObj != null) {
            userInfo = (UserInfo) userObj;
        }

        return userInfo;
    }

}
