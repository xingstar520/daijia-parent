package com.nianxi.daijia.customer.service;

import com.nianxi.daijia.model.entity.customer.CustomerInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nianxi.daijia.model.vo.customer.CustomerLoginVo;

public interface CustomerInfoService extends IService<CustomerInfo> {

    //微信小程序登录
    Long login(String code);

    //获取客户登录信息
    CustomerLoginVo getCustomerLoginInfo(Long customerId);
}
