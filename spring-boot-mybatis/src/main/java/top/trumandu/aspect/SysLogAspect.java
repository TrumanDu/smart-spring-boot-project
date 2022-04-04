package top.trumandu.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.trumandu.common.anno.SysLog;
import top.trumandu.module.system.log.SysLogService;
import top.trumandu.module.system.log.domain.SysLogEntity;
import top.trumandu.util.HttpContextUtils;
import top.trumandu.util.IpUtils;
import top.trumandu.util.SmartCurrentUserUtil;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author Truman.P.Du
 * @date 2022/04/04
 * @description 日志切面
 */
@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private SysLogService sysLogService;

    @Pointcut("@annotation(top.trumandu.common.anno.SysLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLogEntity sysLogEntity = new SysLogEntity();
        SysLog syslog = method.getAnnotation(SysLog.class);
        if (syslog != null) {
            //注解上的描述
            sysLogEntity.setOperation(syslog.operation());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLogEntity.setMethod(className + "." + methodName + "()");

        if (syslog.params()) {
            //请求的参数
            Object[] args = joinPoint.getArgs();
            try {
                String params = JSONObject.toJSONString(args[0]);
                sysLogEntity.setParams(params);
            } catch (Exception e) {

            }
        }
        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        sysLogEntity.setIp(IpUtils.getIpAddress(request));
        //用户名
        String username = SmartCurrentUserUtil.getCurrentUser().getUsername();
        sysLogEntity.setUsername(username);

        sysLogEntity.setCost(time);
        //保存系统日志
        sysLogService.add(sysLogEntity);
    }
}