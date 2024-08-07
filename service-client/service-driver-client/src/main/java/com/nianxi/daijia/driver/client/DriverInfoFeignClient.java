package com.nianxi.daijia.driver.client;

import com.nianxi.daijia.common.result.Result;
import com.nianxi.daijia.model.entity.driver.DriverSet;
import com.nianxi.daijia.model.form.driver.DriverFaceModelForm;
import com.nianxi.daijia.model.form.driver.UpdateDriverAuthInfoForm;
import com.nianxi.daijia.model.vo.driver.DriverAuthInfoVo;
import com.nianxi.daijia.model.vo.driver.DriverInfoVo;
import com.nianxi.daijia.model.vo.driver.DriverLoginVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "service-driver")
public interface DriverInfoFeignClient {

    //微信小程序登录接口
    @GetMapping(value = "/driver/info/login/{code}")
    Result<Long> login(@PathVariable("code") String code);

    //获取司机信息
    @GetMapping(value = "/driver/info/getDriverLoginInfo/{driverId}")
    Result<DriverLoginVo> getDriverLoginInfo(@PathVariable("driverId") Long driverId);
}