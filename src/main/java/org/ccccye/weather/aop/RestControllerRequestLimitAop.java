package org.ccccye.weather.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * 限制请求频率AOP
 */
@Aspect
@Component
public class RestControllerRequestLimitAop {
    private final static Log logger = LogFactory.getLog(RestControllerRequestLimitAop.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 前置通知
     * @param joinPoint
     * @param limit
     */
    @Before("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(limit)")
    public void requestLimit(final JoinPoint joinPoint, RestControllerRequestLimit limit) throws RestControllerRequestLimitException {
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = null;
        for (int i =0; i < args.length; i++){
            if (args[i] instanceof HttpServletRequest){
                request = (HttpServletRequest) args[i];
                break;
            }
        }

        if (request == null){
            throw new RestControllerRequestLimitException("方法中缺失HttpServletRequest参数");
        }

        String ip = request.getRemoteAddr();
        String url = request.getRequestURL().toString();
        String key = "req_limit_".concat(url).concat(ip);

        try {
            long count = redisTemplate.opsForValue().increment(key, 1);
            if (count == 1) {
                redisTemplate.expire(key, limit.timeout(), TimeUnit.MILLISECONDS);
            }
            if (count > limit.maxCount()) {
                logger.info("用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + limit.maxCount() + "]");
                throw new RestControllerRequestLimitException();
            }
        }catch (RestControllerRequestLimitException requestLimitException){
            throw requestLimitException;
        }
        catch (Exception ex){
            logger.error("发生异常:"+ex.getMessage());
        }

    }

}
