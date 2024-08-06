package com.nianxi.daijia.common.login;

import com.nianxi.daijia.common.constant.RedisConstant;
import com.nianxi.daijia.common.execption.GuiguException;
import com.nianxi.daijia.common.result.ResultCodeEnum;
import com.nianxi.daijia.common.util.AuthContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Jie.
 * @description: TODO
 * @date 2024/8/6
 * @version: 1.0
 */
@Component
@Aspect //切面类
public class NianxiLoginAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    //用环绕通知,登陆判断
    //切入点表达式:指定在哪个方法上切入(增强)
    //切入点表达式：指定对哪些规则的方法进行增强
    @Around("execution(* com.nianxi.daijia.*.controller.*.*(..)) && @annotation(nianxiLogin)")
    public Object login(ProceedingJoinPoint proceedingJoinPoint,NianxiLogin nianxiLogin)  throws Throwable {

        //1. 获取request对象
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) attributes;
        HttpServletRequest request = sra.getRequest();
        //2. 从请求头中获取token
        String token = request.getHeader("token");
        //3. 判断token是否为空,如果为空,则返回登陆提示
        if (!StringUtils.hasText(token)) {
            throw new GuiguException(ResultCodeEnum.LOGIN_AUTH);
        }
        //4. token不为空,查询redis
        String customerId = (String)redisTemplate.opsForValue().get(RedisConstant.USER_LOGIN_KEY_PREFIX + token);
        //5. 查询redis对应用户id,把用户id放入ThreadLocal里面
        if (StringUtils.hasText(customerId)) {
            AuthContextHolder.setUserId(Long.parseLong(customerId));
        }
        //6. 执行业务方法
        return proceedingJoinPoint.proceed();
    }
}
