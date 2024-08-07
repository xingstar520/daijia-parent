package com.nianxi.daijia.driver.service;

import com.nianxi.daijia.common.result.Result;
import com.nianxi.daijia.model.entity.driver.DriverInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nianxi.daijia.model.vo.driver.DriverLoginVo;
import org.springframework.web.bind.annotation.PathVariable;

public interface DriverInfoService extends IService<DriverInfo> {

    //小程序授权登陆
    Long login(String code);

    //获取司机信息
    DriverLoginVo getDriverInfo(Long driverId);
}
