package top.trumandu.module.system.log.domain;

import top.trumandu.common.domain.PageParamDTO;

import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2022/04/04
 * @description
 */
public class SysLogQueryDTO extends PageParamDTO {
    private String operation;
    private String username;
    private String method;
    private String ip;
    private List<String> createTime;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<String> getCreateTime() {
        return createTime;
    }

    public void setCreateTime(List<String> createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SysLogQueryDTO{" +
                "operation='" + operation + '\'' +
                ", username='" + username + '\'' +
                ", method='" + method + '\'' +
                ", ip='" + ip + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
