package top.trumandu.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.trumandu.common.domain.Response;
import top.trumandu.common.domain.ResultCodeEnum;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/04
 * @description 全局异常处理
 */
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response globalExceptionHandler(Exception e) {
        e.printStackTrace();
        return Response.setResult(ResultCodeEnum.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<ObjectError> objectErrors = e.getBindingResult().getAllErrors();
        StringBuilder sb = new StringBuilder();
        objectErrors.forEach(objectError -> sb.append(((FieldError) objectError).getField()).append(": ").append(objectError.getDefaultMessage()).append(" "));
        return Response.setResult(ResultCodeEnum.BAD_REQUEST).message(sb.toString());
    }
}
