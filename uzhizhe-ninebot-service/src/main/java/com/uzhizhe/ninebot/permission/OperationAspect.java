package com.uzhizhe.ninebot.permission;

import com.alibaba.fastjson.JSON;
import com.uzhizhe.ninebot.entities.UserModel;
import com.uzhizhe.ninebot.entities.UserOperateModel;
import com.uzhizhe.ninebot.entities.UserRoleModel;
import com.uzhizhe.ninebot.exceptions.UserException;
import com.uzhizhe.ninebot.service.user.UserModelService;
import com.uzhizhe.ninebot.service.user.UserOperateModelService;
import com.uzhizhe.ninebot.service.user.UserRoleModelService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author qingjiang.li
 * @since 2019-07-03 2:20 PM
 */
@Component
@Aspect
@Slf4j
public class OperationAspect {

    @Autowired
    private UserModelService userModelService;
    @Autowired
    private UserRoleModelService userRoleModelService;
    @Autowired
    private UserOperateModelService userOperateModelService;

    @Pointcut("@annotation(com.uzhizhe.ninebot.permission.Operate)")
    public void operationPointCut() {
    }

    @Before("operationPointCut()")
    public void before(JoinPoint joinPoint) throws Throwable {
        boolean permission = false;
        try {
            Object[] args = joinPoint.getArgs();
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Operate operate = method.getAnnotation(Operate.class);
            EOperateType operateType = operate.value();
            log.info("{} -> {} parameters:{}",
                    joinPoint.getTarget().getClass().getSimpleName(),
                    joinPoint.getSignature().getName(),
                    JSON.toJSONString(args));
            log.info("operate:{}, operateType:{}", operate, operateType);

            String username = String.valueOf(args[0]);
            System.out.println(username);
            System.out.println(operateType);
            permission = hasPermission(username, operateType);

        } catch (Exception e) {
            String s = joinPoint.toString();
            log.info("joinPoint toString:{}", s);
            log.error("operationPointCut Exception", e);
        }
        if (!permission) {
            throw new UserException(4002, "您的账号没有此功能的操作权限，如需操作请联系管理员申请权限");
        }

    }

    private boolean hasPermission(String username, EOperateType operateType) {
        UserModel userModel = userModelService.query(username);
        if (userModel == null) {
            throw new UserException(4001, "用户不存在");
        }
        UserRoleModel userRoleModel = userRoleModelService.query(userModel.getRoleId());
        UserOperateModel userOperateModel = userOperateModelService.query(operateType.name());
        log.info("userMode:{}", userModel);
        log.info("userRoleMode:{}", userRoleModel);
        log.info("userOperateModel:{}", userOperateModel);

        System.out.println(userRoleModel.getRolePermissionCode() + ":" + userOperateModel.getPermissionCode());
        int permissionCode = userRoleModel.getRolePermissionCode() & userOperateModel.getPermissionCode();
        log.info("permissionCode:{}", permissionCode);

        return permissionCode > 0;
    }


}
