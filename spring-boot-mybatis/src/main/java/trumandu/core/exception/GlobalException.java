package trumandu.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.trumandu.core.response.RestResult;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<ObjectError> objectErrors = e.getBindingResult().getAllErrors();
        StringBuilder sb = new StringBuilder();
        objectErrors.forEach(objectError -> sb.append(((FieldError) objectError).getField() + " " + objectError.getDefaultMessage()));
        return RestResult.failure(sb.toString());
    }
}
