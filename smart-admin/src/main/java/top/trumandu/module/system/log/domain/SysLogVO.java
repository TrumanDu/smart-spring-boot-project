package top.trumandu.module.system.log.domain;

import java.util.Date;

/**
 * @author Truman.P.Du
 * @date 2022/04/04
 * @description
 */
public class SysLogVO {
    private Long id;
    private String operation;
    private String username;
    private String method;
    private String params;
    private Long cost;
    private String ip;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }


    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SysLogVO{" +
                "id=" + id +
                ", operation='" + operation + '\'' +
                ", username='" + username + '\'' +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                ", cost=" + cost +
                ", ip='" + ip + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
