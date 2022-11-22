package top.trumandu.common.exception;

import top.trumandu.common.domain.ResultCodeEnum;

import java.io.Serial;

/**
 * @author Truman.P.Du
 * @date 2022/11/22
 * @description
 */
@SuppressWarnings("unused")
public class CustomException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4109743717152168905L;
    private Integer code;

    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public CustomException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
