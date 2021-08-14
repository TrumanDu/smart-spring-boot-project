package top.trumandu.common.base;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.trumandu.common.domain.RestResult;
import top.trumandu.common.domain.SessionAttr;
import top.trumandu.common.domain.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Truman.P.Du
 * @date 2020/04/25
 * @description
 */
public class BaseController {


    public UserInfo getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        HttpSession session = request.getSession();
        UserInfo userInfo = null;
        Object userObj = session.getAttribute(SessionAttr.USER.getValue());
        if (userObj != null) {
            userInfo = (UserInfo) userObj;
        }

        return userInfo;
    }

    public RestResult success() {
        return RestResult.success();
    }

    public RestResult success(Object data) {
        return RestResult.success(data);
    }


    /**
     * 客户端参数错误
     *
     * @param message
     * @return
     */
    public RestResult failure(String message) {
        return RestResult.failure(message);
    }

    /**
     * 服务器内部错误
     *
     * @param message
     * @return
     */
    public RestResult error(String message) {
        return RestResult.error(message);
    }

    /**
     * 未认证（签名错误）
     *
     * @return
     */
    public RestResult unauthorized() {
        return RestResult.unauthorized();
    }
}
