package com.nianxi.daijia.driver.controller;

import com.nianxi.daijia.common.login.NianxiLogin;
import com.nianxi.daijia.common.result.Result;
import com.nianxi.daijia.common.util.AuthContextHolder;
import com.nianxi.daijia.driver.service.DriverService;
import com.nianxi.daijia.model.form.driver.DriverFaceModelForm;
import com.nianxi.daijia.model.form.driver.UpdateDriverAuthInfoForm;
import com.nianxi.daijia.model.vo.driver.DriverAuthInfoVo;
import com.nianxi.daijia.model.vo.driver.DriverLoginVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //获取司机登录信息
    @Operation(summary = "获取司机登录信息")
    @NianxiLogin
    @GetMapping("/getDriverLoginInfo")
    public Result<DriverLoginVo> getDriverLoginInfo(){
        //1 获取用户id
        Long driverId = AuthContextHolder.getUserId();
        //2.调用service方法
        DriverLoginVo driverLoginVo = driverService.getDriverLoginInfo(driverId);
        return Result.ok(driverLoginVo);
    }

    //获取司机认证信息
    @Operation(summary = "获取司机认证信息")
    @NianxiLogin
    @GetMapping("/getDriverAuthInfo")
    public Result<DriverAuthInfoVo> getDriverAuthInfo(){
        //1 获取司机id
        Long driverId = AuthContextHolder.getUserId();
        //2.调用service方法
        DriverAuthInfoVo driverAuthInfoVo = driverService.getDriverAuthInfo(driverId);
        return Result.ok(driverAuthInfoVo);
    }

    //更新司机认证信息
    @Operation(summary = "更新司机认证信息")
    @NianxiLogin
    @PostMapping("/updateDriverAuthInfo")
    public Result<Boolean> updateDriverAuthInfo(@RequestBody UpdateDriverAuthInfoForm updateDriverAuthInfoForm){
        //1 获取司机id
        updateDriverAuthInfoForm.setDriverId(AuthContextHolder.getUserId());
        return Result.ok(driverService.updateDriverAuthInfo(updateDriverAuthInfoForm));
    }

    //创建司机人脸模型
    @Operation(summary = "创建司机人脸模型")
    @NianxiLogin
    @PostMapping("/creatDriverFaceModel")
    public Result<Boolean> creatDriverFaceModel(@RequestBody DriverFaceModelForm driverFaceModelForm){
        //1 获取司机id
        driverFaceModelForm.setDriverId(AuthContextHolder.getUserId());
        return Result.ok(driverService.createDriverFaceModel(driverFaceModelForm));
    }

    //判断司机当日是否进行过人脸识别
    @Operation(summary = "判断司机当日是否进行过人脸识别")
    @NianxiLogin
    @GetMapping("/isFaceRecognition")
    Result<Boolean> isFaceRecognition() {
        Long driverId = AuthContextHolder.getUserId();
        return Result.ok(driverService.isFaceRecognition(driverId));
    }

    //验证司机人脸
    @Operation(summary = "验证司机人脸")
    @NianxiLogin
    @PostMapping("/verifyDriverFace")
    public Result<Boolean> verifyDriverFace(@RequestBody DriverFaceModelForm driverFaceModelForm) {
        driverFaceModelForm.setDriverId(AuthContextHolder.getUserId());
        return Result.ok(driverService.verifyDriverFace(driverFaceModelForm));
    }

    //开始接单状态
    @Operation(summary = "开始接单服务")
    @NianxiLogin
    @GetMapping("/startService")
    public Result<Boolean> startService() {
        Long driverId = AuthContextHolder.getUserId();
        return Result.ok(driverService.startService(driverId));
    }

    //停止接单服务
    @Operation(summary = "停止接单服务")
    @NianxiLogin
    @GetMapping("/stopService")
    public Result<Boolean> stopService() {
        Long driverId = AuthContextHolder.getUserId();
        return Result.ok(driverService.stopService(driverId));
    }
}

