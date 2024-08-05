package com.nianxi.daijia.customer.client;

import com.nianxi.daijia.common.result.Result;
import com.nianxi.daijia.model.vo.customer.CustomerLoginVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-customer")
public interface CustomerInfoFeignClient {

    //微信小程序登录接口
    @GetMapping("/customer/info/login/{code}")
    public Result<Long> login(@PathVariable String code);

    //获取客户基本信息
    @GetMapping("/customer/info/getCustomerLoginInfo/{customerId}")
    public Result<CustomerLoginVo> getCustomerLoginInfo(@PathVariable Long customerId);
}