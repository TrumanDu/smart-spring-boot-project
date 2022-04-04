package top.trumandu.module.system.log.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import top.trumandu.common.domain.BaseEntity;

/**
 * @author Truman.P.Du
 * @date 2022/04/04
 * @description
 */
@TableName("sys_log")
public class SysLogEntity extends BaseEntity {

    private String operation;
    private String username;
    private String method;
    private String params;
    private Long cost;
    private String ip;

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

}
