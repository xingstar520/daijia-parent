package com.nianxi.daijia.customer.service;

import com.nianxi.daijia.model.vo.customer.CustomerLoginVo;

public interface CustomerService {

    //微信小程序登录
    String login(String code);

    //获取客户登录信息
//    CustomerLoginVo getCustomerLoginInfo(String token);

    //获取客户基本信息
    CustomerLoginVo getCustomerInfo(Long customerId);
}
