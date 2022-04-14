package top.trumandu.module.system.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.trumandu.common.domain.ResponseDTO;
import top.trumandu.common.domain.SessionAttr;
import top.trumandu.constant.CommonConst;
import top.trumandu.module.system.login.domain.LoginDTO;
import top.trumandu.module.system.login.domain.LoginUserVO;
import top.trumandu.module.system.user.UserDao;
import top.trumandu.util.SmartCurrentUserUtil;
import top.trumandu.util.SmartDigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Truman.P.Du
 * @date 2022/03/30
 * @description
 */
@Service
public class LoginService {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    UserDao userDao;


    public ResponseDTO login(LoginDTO loginDTO, HttpServletRequest request) {
        String encryptPassword = SmartDigestUtils.encryptPassword(CommonConst.Password.SALT_FORMAT, loginDTO.getPassword());
        LoginUserVO userVO = userDao.login(loginDTO.getUsername(), encryptPassword);
        if (userVO == null) {
            return ResponseDTO.failure("Incorrect username/password");
        }
        SmartCurrentUserUtil.setCurrentUser(request, userVO);
        return ResponseDTO.success();
    }

    public LoginUserVO reLogin(HttpServletRequest request) {
        //TODO 重登陆实现逻辑
        return null;
    }

    public ResponseDTO logout(HttpServletRequest request) {
        try {
            request.logout();
            HttpSession session = request.getSession(true);
            session.removeAttribute(SessionAttr.USER.getValue());
            SmartCurrentUserUtil.removeCurrentUser();
        } catch (ServletException e) {
            LOGGER.error("logout fail.", e);
            ResponseDTO.error("logout fail,please try again.");
        }
        return ResponseDTO.success();
    }

}
