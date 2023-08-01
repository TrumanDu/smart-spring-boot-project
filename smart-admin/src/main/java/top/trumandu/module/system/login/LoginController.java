package top.trumandu.module.system.login;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.trumandu.common.anno.AuthIgnore;
import top.trumandu.common.anno.SysLog;
import top.trumandu.common.domain.Response;
import top.trumandu.module.system.login.domain.LoginDTO;


/**
 * @author Truman.P.Du
 * @date 2022/03/30
 * @description
 */
@RestController
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @AuthIgnore
    @SysLog(operation = "登录系统")
    @PostMapping("/login")
    public Response login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        return loginService.login(loginDTO, request);
    }

    @GetMapping("/logout")
    public Response logout(HttpServletRequest request) {
        return loginService.logout(request);
    }
}
