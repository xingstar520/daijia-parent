package com.nianxi.daijia.customer.service;

import com.nianxi.daijia.model.entity.customer.CustomerInfo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface CustomerInfoService extends IService<CustomerInfo> {

    //微信小程序登录
    Long login(String code);
}
