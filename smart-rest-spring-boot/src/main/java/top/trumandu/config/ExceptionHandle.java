package top.trumandu.config;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import top.trumandu.common.domain.Response;
import top.trumandu.common.domain.ResultCodeEnum;
import top.trumandu.common.exception.BusinessException;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/04
 * @description 全局异常处理
 */
@SuppressWarnings("unused")
@RestControllerAdvice
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class ExceptionHandle {
    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response globalExceptionHandler(Exception e) {
        LOGGER.error("globalException", e);
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

    @ExceptionHandler(BusinessException.class)
    public Response customerExceptionHandler(BusinessException e) {
        LOGGER.warn("BusinessException", e);
        return Response.error().code(e.getCode()).message(e.getMessage());
    }

    /**
     * 接口不存在
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response error(NoHandlerFoundException e) {
        return Response.error().code(HttpStatus.NOT_FOUND.value()).message(e.getRequestURL());
    }

    /**
     * 请求方法不被允许
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Response httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return Response.error().code(HttpStatus.METHOD_NOT_ALLOWED.value()).message(e.getMessage());
    }

    /**
     * 请求与响应媒体类型不一致 异常
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return Response.error().code(HttpStatus.BAD_REQUEST.value()).message(e.getMessage());
    }

    /**
     * body json参数解析异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response httpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        request.setAttribute("argument_error", e.getMessage());
        return Response.error().code(HttpStatus.BAD_REQUEST.value()).message(e.getMessage());
    }
}
