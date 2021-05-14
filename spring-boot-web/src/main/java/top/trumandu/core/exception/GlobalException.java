package top.trumandu.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.trumandu.core.response.RestResult;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Truman.P.Du
 * @date 2021/05/13
 * @description 全局异常处理
 */
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResult globalExceptionHandler(HttpServletResponse response, Exception e) {
        e.printStackTrace();
        return RestResult.error(e.getMessage());
    }
}
