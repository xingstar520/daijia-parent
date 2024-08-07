package com.nianxi.daijia.driver.service;

import com.nianxi.daijia.model.vo.driver.DriverLoginVo;

public interface DriverService {

    //小程序授权登陆
    String login(String code);

    //获取司机信息
    DriverLoginVo getDriverLoginInfo(Long driverId);

}
