package org.ccccye.weather.aop;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 根据IP地址限制访问频率
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface RestControllerRequestLimit {

    /**
     * 接口最大访问次数
     */
    int maxCount() default Integer.MAX_VALUE;

    /**
     * 超时
     */
    long timeout() default 60 * 1000;
}
