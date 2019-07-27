package com.uzhizhe.ninebot.web.logger;

import com.alibaba.fastjson.JSON;
import com.uzhizhe.ninebot.web.logger.annotations.SysLogger;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author qingjiang.li
 * @since 2019-07-03 2:20 PM
 */
@Component
@Aspect
@Slf4j
public class SysLoggerAspect {

    @Autowired
    private SysLoggerService sysLoggerService;

    @Pointcut("@annotation(com.uzhizhe.ninebot.web.logger.annotations.SysLogger)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;
        try {
            saveLoger(point, time);
        } catch (Exception e) {
        }
        return result;
    }

    @Before("logPointCut()")
    public void before(JoinPoint joinPoint) throws Throwable {
        try {
            Object[] args = joinPoint.getArgs();
            log.info("{} -> {} parameters:{}",
                    joinPoint.getTarget().getClass().getSimpleName(),
                    joinPoint.getSignature().getName(),
                    JSON.toJSONString(args));
        } catch (Exception e) {
            String s = joinPoint.toString();
            log.info("joinPoint toString:{}", s);
        }


    }

    private void saveLoger(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLoggerBo sysLogBO = new SysLoggerBo();
        sysLogBO.setExeuTime(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        sysLogBO.setCreateDate(dateFormat.format(new Date()));
        SysLogger sysLogger = method.getAnnotation(SysLogger.class);
        if (sysLogger != null) {
            //注解上的描述
            sysLogBO.setRemark(sysLogger.value());
        }
        //请求的 类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLogBO.setClassName(className);
        sysLogBO.setMethodName(methodName);
        //请求的参数
        Object[] args = joinPoint.getArgs();

        try {
            List<String> list = new ArrayList<String>();
            for (Object o : args) {
                list.add(JSON.toJSONString(o));
            }
            sysLogBO.setParams(list.toString());
        } catch (Exception e) {
        }
        sysLoggerService.save(sysLogBO);
    }
}
