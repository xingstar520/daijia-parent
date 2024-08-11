package com.nianxi.daijia.driver.service.impl;

import com.nianxi.daijia.common.constant.RedisConstant;
import com.nianxi.daijia.common.execption.GuiguException;
import com.nianxi.daijia.common.result.Result;
import com.nianxi.daijia.common.result.ResultCodeEnum;
import com.nianxi.daijia.driver.client.DriverInfoFeignClient;
import com.nianxi.daijia.driver.service.DriverService;
import com.nianxi.daijia.model.form.driver.DriverFaceModelForm;
import com.nianxi.daijia.model.form.driver.UpdateDriverAuthInfoForm;
import com.nianxi.daijia.model.vo.driver.DriverAuthInfoVo;
import com.nianxi.daijia.model.vo.driver.DriverLoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverInfoFeignClient client;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String login(String code) {
        //1.拿着code去微信服务器换取openid
        Result<Long> longResult = client.login(code);
        //2.根据openid判断用户是否存在(如果返回失败,返回错误信息)
        Integer codeResult = longResult.getCode();
        //判断状态码是否为200
        if (codeResult != 200) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        //3.获取远程调用返回用户ID
        Long driverId = longResult.getData();
        //4.判断用户是否存在(如果返回失败,返回错误信息)
        if (driverId == null) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        //5.生成token
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        //6.把用户放到redis中,设置过期时间
        //key:token value:customerId
        redisTemplate.opsForValue().set(RedisConstant.USER_LOGIN_KEY_PREFIX + token,
                driverId.toString(),
                RedisConstant.USER_LOGIN_KEY_TIMEOUT,
                TimeUnit.SECONDS);
        return token;
    }

    //获取司机信息
    @Override
    public DriverLoginVo getDriverLoginInfo(Long driverId) {
        //2 远程调用获取司机信息
        Result<DriverLoginVo> loginVoResult = client.getDriverLoginInfo(driverId);
        //3 判断是否获取成功
        Integer code = loginVoResult.getCode();
        if (code != 200) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        //4 获取司机信息
        DriverLoginVo driverLoginVo = loginVoResult.getData();
        if (driverLoginVo == null) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        //5 返回司机信息
        return driverLoginVo;
    }

    //获取司机认证信息
    @Override
    public DriverAuthInfoVo getDriverAuthInfo(Long driverId) {
        //2 远程调用获取司机认证信息
        Result<DriverAuthInfoVo> driverAuthInfoVoResult = client.getDriverAuthInfo(driverId);
        //3 判断是否获取成功
        Integer code = driverAuthInfoVoResult.getCode();
        if (code != 200) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        DriverAuthInfoVo driverAuthInfoVo = driverAuthInfoVoResult.getData();
        if (driverAuthInfoVo == null) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        return driverAuthInfoVo;
    }

    //更新司机认证信息
    @Override
    public Boolean updateDriverAuthInfo(UpdateDriverAuthInfoForm updateDriverAuthInfoForm) {
        Result<Boolean> booleanResult = client.updateDriverAuthInfo(updateDriverAuthInfoForm);
        Integer code = booleanResult.getCode();
        if (code != 200) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        Boolean data = booleanResult.getData();
        return data;
    }

    //创建司机人脸模型
    @Override
    public Boolean createDriverFaceModel(DriverFaceModelForm driverFaceModelForm) {
        Result<Boolean> booleanResult = client.creatDriverFaceModel(driverFaceModelForm);
        Integer code = booleanResult.getCode();
        if (code != 200) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        return booleanResult.getData();
    }
}
