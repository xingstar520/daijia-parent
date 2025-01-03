package com.nianxi.daijia.customer.controller;

import com.nianxi.daijia.common.constant.RedisConstant;
import com.nianxi.daijia.common.execption.GuiguException;
import com.nianxi.daijia.common.login.NianxiLogin;
import com.nianxi.daijia.common.result.Result;
import com.nianxi.daijia.common.result.ResultCodeEnum;
import com.nianxi.daijia.common.util.AuthContextHolder;
import com.nianxi.daijia.customer.client.CustomerInfoFeignClient;
import com.nianxi.daijia.customer.service.CustomerService;
import com.nianxi.daijia.model.form.customer.UpdateWxPhoneForm;
import com.nianxi.daijia.model.vo.customer.CustomerLoginVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "客户API接口管理")
@RestController
@RequestMapping("/customer")
@SuppressWarnings({"unchecked", "rawtypes"})
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //微信小程序登录接口
    @Operation(summary = "小程序授权登录")
    @GetMapping("/login/{code}")
    public Result<String> wxLogin(@PathVariable String code) {
        return Result.ok(customerService.login(code));
    }

      //获取客户基本信息
//    @Operation(summary = "获取客户登录信息")
//    @GetMapping("/getCustomerLoginInfo")
//    public Result<CustomerLoginVo> getCustomerLoginInfo(@RequestHeader("token") String token) {
//        //调用service方法
//        CustomerLoginVo customerLoginVo = customerService.getCustomerLoginInfo(token);
//        return Result.ok(customerLoginVo);
//    }

    //获取客户基本信息(改进)
    @Operation(summary = "获取客户登录信息")
    @NianxiLogin
    @GetMapping("/getCustomerLoginInfo")
    public Result<CustomerLoginVo> getCustomerLoginInfo() {
        //1.从ThreadLocal中获取用户ID
        Long customerId = AuthContextHolder.getUserId();
        //2.调用service方法
        CustomerLoginVo customerLoginVo =  customerService.getCustomerInfo(customerId);
        return Result.ok(customerLoginVo);
    }

    //更新客户微信手机号码
    @Operation(summary = "更新用户微信手机号")
    @NianxiLogin
    @PostMapping("/updateWxPhone")
    public Result updateWxPhone(@RequestBody UpdateWxPhoneForm updateWxPhoneForm) {
        updateWxPhoneForm.setCustomerId(AuthContextHolder.getUserId());
//        return Result.ok(customerService.updateWxPhoneNumber(updateWxPhoneForm));
        //微信公众号为个人版直接跳过，返回true
        return Result.ok(true);
    }
}

