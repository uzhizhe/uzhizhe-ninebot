package com.uzhizhe.ninebot.logger.annotations;

import java.lang.annotation.*;

/**
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-08-07
 * @Desc SysBeforeNotice 前置通知
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysExceptionNotice {
    String value() default "";
}
