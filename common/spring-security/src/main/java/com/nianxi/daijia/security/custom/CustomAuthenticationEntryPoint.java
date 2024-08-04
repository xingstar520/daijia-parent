package com.nianxi.daijia.security.custom;

import com.nianxi.daijia.common.result.Result;
import com.nianxi.daijia.common.result.ResultCodeEnum;
import com.nianxi.daijia.common.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {

        ResponseUtil.out(response, Result.build(null, ResultCodeEnum.ACCOUNT_ERROR));
    }
}
