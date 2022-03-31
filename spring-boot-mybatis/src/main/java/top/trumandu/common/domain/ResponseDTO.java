package top.trumandu.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Truman.P.Du
 * @date 2020/04/25
 * @description rest 统一返回对象
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> {
    private int code;
    private String message;
    private T data;


    public ResponseDTO(T data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getMessage();
        this.data = data;
    }

    public ResponseDTO(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public ResponseDTO(ResultCode resultCode, String message) {
        this.code = resultCode.getCode();
        this.message = message;
    }

    public static ResponseDTO success() {
        return success(null);
    }

    public static ResponseDTO success(Object data) {
        return new ResponseDTO(ResultCode.SUCCESS, data);
    }


    /**
     * 客户端参数错误
     *
     * @param message
     * @return
     */
    public static ResponseDTO failure(String message) {
        return new ResponseDTO(ResultCode.BAD_REQUEST, message);
    }


    public static ResponseDTO error(String message) {
        return new ResponseDTO(ResultCode.INTERNAL_SERVER_ERROR, message);
    }

    /**
     * 未认证（签名错误）
     *
     * @return
     */
    public static ResponseDTO unauthorized() {
        return new ResponseDTO(ResultCode.UNAUTHORIZED, null);
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

    public void setData(T data) {
        this.data = data;
    }

}
