package com.uzhizhe.ninebot.web.logger.annotations;

import java.lang.annotation.*;

/**
 * @author qingjiang.li
 * @since 2019-07-03 2:18 PM
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogger {
    String value() default "";
}
