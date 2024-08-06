package com.nianxi.daijia.common.login;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Jie.
 * @description: TODO
 * @date 2024/8/6
 * @version: 1.0
 */

// 登陆判断注解
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NianxiLogin {

}