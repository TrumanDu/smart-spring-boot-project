package top.trumandu.core.response;

/**
 * @author Truman.P.Du
 * @date 2021/05/13
 * @description 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    /**
     * 200
     */
    SUCCESS(200, "OK"),
    /**
     * 400
     */
    BAD_REQUEST(400, "Bad Request"),
    /**
     * 401
     */
    UNAUTHORIZED(401,"Unauthorized"),
    /**
     * 500
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),;

    /**
     * 业务异常码
     */
    private Integer code;
    /**
     * 信息描述
     */
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
