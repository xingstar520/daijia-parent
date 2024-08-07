package com.nianxi.daijia.driver.service;

import com.nianxi.daijia.common.result.Result;
import com.nianxi.daijia.model.entity.driver.DriverInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PathVariable;

public interface DriverInfoService extends IService<DriverInfo> {

    //小程序授权登陆
    Long login(String code);

}
