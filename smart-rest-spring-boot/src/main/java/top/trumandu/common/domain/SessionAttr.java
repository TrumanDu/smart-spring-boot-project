package top.trumandu.common.domain;

/**
 * @author Truman.P.Du
 * @date 2020/04/25
 * @description
 */
public enum SessionAttr {
    /**
     * session_user
     */
    USER("session_user");

    private final String value;

    private SessionAttr(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
