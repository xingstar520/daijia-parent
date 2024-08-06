package com.nianxi.daijia.customer.service;

import com.nianxi.daijia.model.form.customer.UpdateWxPhoneForm;
import com.nianxi.daijia.model.vo.customer.CustomerLoginVo;
import org.springframework.web.bind.annotation.RequestBody;

public interface CustomerService {

    //微信小程序登录
    String login(String code);

    //获取客户登录信息
//    CustomerLoginVo getCustomerLoginInfo(String token);

    //获取客户基本信息
    CustomerLoginVo getCustomerInfo(Long customerId);

    //更新客户微信手机号码
    Boolean updateWxPhoneNumber(@RequestBody UpdateWxPhoneForm updateWxPhoneForm);
}
