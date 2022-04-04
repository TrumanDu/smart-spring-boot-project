package top.trumandu.module.system.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.trumandu.common.anno.AuthIgnore;
import top.trumandu.common.domain.ResponseDTO;
import top.trumandu.module.system.login.domain.LoginDTO;
import top.trumandu.module.system.login.domain.LoginUserVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Truman.P.Du
 * @date 2022/03/30
 * @description
 */
@RestController
public class LoginController{
    @Autowired
    LoginService loginService;

    @AuthIgnore
    @PostMapping("/login")
    public ResponseDTO<LoginUserVO> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        return loginService.login(loginDTO,request);
    }

    @GetMapping("/logout")
    public ResponseDTO logout(HttpServletRequest request) {
        return loginService.logout(request);
    }
}
