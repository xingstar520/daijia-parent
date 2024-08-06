package com.nianxi.daijia.customer.service.impl;

import com.nianxi.daijia.common.constant.RedisConstant;
import com.nianxi.daijia.common.execption.GuiguException;
import com.nianxi.daijia.common.result.Result;
import com.nianxi.daijia.common.result.ResultCodeEnum;
import com.nianxi.daijia.customer.client.CustomerInfoFeignClient;
import com.nianxi.daijia.customer.service.CustomerService;
import com.nianxi.daijia.model.form.customer.UpdateWxPhoneForm;
import com.nianxi.daijia.model.vo.customer.CustomerLoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class CustomerServiceImpl implements CustomerService {
    //注入客户端
    @Autowired
    private CustomerInfoFeignClient client;

    @Autowired
    private RedisTemplate redisTemplate;

    //微信小程序登录
    @Override
    public String login(String code) {
        //1.拿着code去微信服务器换取openid
        Result<Long> loginResult = client.login(code);
        //2.根据openid判断用户是否存在(如果返回失败,返回错误信息)
        Integer codeResult = loginResult.getCode();
        if (codeResult != 200) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        //3.获取远程调用返回用户ID
        Long customerId = loginResult.getData();
        //4.判断用户是否存在(如果返回失败,返回错误信息)
        if (customerId == null) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        //5.生成token
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        //6.把用户放到redis中,设置过期时间
        //key:token value:customerId
        //redisTemplate.opsForCluster().set(token, customerId, 30, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(RedisConstant.USER_LOGIN_KEY_PREFIX+token,
                customerId.toString(),
                RedisConstant.USER_LOGIN_KEY_TIMEOUT,
                TimeUnit.SECONDS);
        //7.返回token
        return token;
    }

//    //获取客户登录信息
//    @Override
//    public CustomerLoginVo getCustomerLoginInfo(String token) {
//        //2 根据token查询redis
//        //3 查询token在redis里面对应用户id
//        String customerId = (String)redisTemplate.opsForValue().get(RedisConstant.USER_LOGIN_KEY_PREFIX + token);
//        if (!StringUtils.hasText(customerId)) {
//            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
//        }
////        if (StringUtils.isEmpty(customerId)) {
////            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
////        }
//
//        //4 根据用户id进行远程调用 得到用户信息
//        Result<CustomerLoginVo> customerLoginVoResult = client.getCustomerLoginInfo(Long.parseLong(customerId));
//        Integer code = customerLoginVoResult.getCode();
//        if (code != 200) {
//            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
//        }
//
//        CustomerLoginVo customerLoginVo = customerLoginVoResult.getData();
//        if (customerLoginVo == null) {
//            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
//        }
//        //5 返回用户信息
//        return customerLoginVo;
//    }

    //获取客户基本信息(改进)
    @Override
    public CustomerLoginVo getCustomerInfo(Long customerId) {
        //4 根据用户id进行远程调用 得到用户信息
        Result<CustomerLoginVo> customerLoginVoResult = client.getCustomerLoginInfo(customerId);
        Integer code = customerLoginVoResult.getCode();
        if (code != 200) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }

        CustomerLoginVo customerLoginVo = customerLoginVoResult.getData();
        if (customerLoginVo == null) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        //5 返回用户信息
        return customerLoginVo;
    }

    //更新客户微信手机号码
    @Override
    public Boolean updateWxPhoneNumber(UpdateWxPhoneForm updateWxPhoneForm) {
        client.updateWxPhoneNumber(updateWxPhoneForm);
        return true;
    }
}
