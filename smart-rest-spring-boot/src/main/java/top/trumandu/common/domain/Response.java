package top.trumandu.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Truman.P.Du
 * @date 2020/04/25
 * @description rest 统一返回对象
 */
@SuppressWarnings("unused")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private Integer code;
    private String message;
    private Object data;

    private Response() {
    }

    /**
     * 通用返回成功
     *
     */
    public static Response ok() {
        Response response = new Response();
        response.setCode(ResultCodeEnum.SUCCESS.getCode());
        response.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return response;
    }

    /**
     * 通用返回错误，未知错误
     *
     */
    public static Response error() {
        Response response = new Response();
        response.setCode(ResultCodeEnum.UNKNOWN_ERROR.getCode());
        response.setMessage(ResultCodeEnum.UNKNOWN_ERROR.getMessage());
        return response;
    }

    public static Response setResult(ResultCodeEnum result) {
        Response r = new Response();
        r.setCode(result.getCode());
        r.setMessage(result.getMessage());
        return r;
    }

    /**
     * 自定义返回数据
     */
    public Response data(Object data) {
        this.setData(data);
        return this;
    }

    /**
     * 自定义信息
     */
    public Response message(String message) {
        this.setMessage(message);
        return this;
    }

    /**
     * 自定义状态码
     */
    public Response code(Integer code) {
        this.setCode(code);
        return this;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
