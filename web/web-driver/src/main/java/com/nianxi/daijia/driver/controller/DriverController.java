package com.nianxi.daijia.driver.controller;

import com.nianxi.daijia.common.login.NianxiLogin;
import com.nianxi.daijia.common.result.Result;
import com.nianxi.daijia.common.util.AuthContextHolder;
import com.nianxi.daijia.driver.service.DriverService;
import com.nianxi.daijia.model.vo.driver.DriverLoginVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "司机API接口管理")
@RestController
@RequestMapping(value="/driver")
@SuppressWarnings({"unchecked", "rawtypes"})
public class DriverController {
	
    @Autowired
    private DriverService driverService;

    //小程序授权登陆
    @Operation(summary = "小程序授权登陆")
    @GetMapping(value = "/login/{code}")
    public Result<String> wxLogin(@PathVariable String code) {
        return Result.ok(driverService.login(code));
    }

    //获取司机信息
    @Operation(summary = "获取司机信息")
    @NianxiLogin
    @GetMapping("/getDriverLoginInfo")
    public Result<DriverLoginVo> getDriverLoginInfo(){
        //1 获取用户id
        Long driverId = AuthContextHolder.getUserId();
        //2.调用service方法
        DriverLoginVo driverLoginVo = driverService.getDriverLoginInfo(driverId);
        return Result.ok(driverLoginVo);
    }
}

