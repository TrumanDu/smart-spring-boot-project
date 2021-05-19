package top.trumandu.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Truman.P.Du
 * @date 2020/04/25
 * @description rest 统一返回对象
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResult {
    private int code;
    private String message;
    private Object data;


    public RestResult(Object data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getMessage();
        this.data = data;
    }

    public RestResult(ResultCode resultCode, Object data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public static RestResult success() {
        return success(null);
    }

    public static RestResult success(Object data) {
        return new RestResult(ResultCode.SUCCESS, data);
    }


    /**
     * 客户端参数错误
     *
     * @param data
     * @return
     */
    public static RestResult failure(Object data) {
        return new RestResult(ResultCode.BAD_REQUEST, data);
    }

    /**
     * 服务器内部错误
     *
     * @param data
     * @return
     */
    public static RestResult error(Object data) {
        return new RestResult(ResultCode.INTERNAL_SERVER_ERROR, data);
    }

    /**
     * 未认证（签名错误）
     *
     * @return
     */
    public static RestResult unauthorized() {
        return new RestResult(ResultCode.UNAUTHORIZED, null);
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

}