package com.uzhizhe.ninebot.permission;

import java.lang.annotation.*;

/**
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-08-07
 * @Desc SysLogger 环绕通知使用注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Operate {
    EOperateType value() default EOperateType.Unknown;
}
