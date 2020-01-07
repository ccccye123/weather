package org.ccccye.weather.aspect;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 根据IP地址限制访问频率
 */
@Retention(RetentionPolicy.RUNTIME)  // 注解如何保存，RUNTIME:编入class，并JVM可读，CLASS:编入class，默认行为,SOURCE:编译时存在
@Target(ElementType.METHOD)  // 标记该注解作用于那种Java成员，Type,FILED,METHOD
@Documented // 标记注解包含在javadoc文档中
@Order(Ordered.HIGHEST_PRECEDENCE) // 执行顺序，从小到大顺序执行
public @interface RestControllerRequestLimit {

    /**
     * 接口最大访问次数
     */
    int maxCount() default Integer.MAX_VALUE;

    /**
     * 超时
     */
    long timeOut() default 60 * 1000;
}
