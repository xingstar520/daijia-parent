package com.nianxi.daijia.driver.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nianxi.daijia.common.constant.SystemConstant;
import com.nianxi.daijia.common.execption.GuiguException;
import com.nianxi.daijia.common.result.ResultCodeEnum;
import com.nianxi.daijia.driver.mapper.DriverAccountMapper;
import com.nianxi.daijia.driver.mapper.DriverInfoMapper;
import com.nianxi.daijia.driver.mapper.DriverLoginLogMapper;
import com.nianxi.daijia.driver.mapper.DriverSetMapper;
import com.nianxi.daijia.driver.service.DriverInfoService;
import com.nianxi.daijia.model.entity.driver.DriverAccount;
import com.nianxi.daijia.model.entity.driver.DriverInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nianxi.daijia.model.entity.driver.DriverLoginLog;
import com.nianxi.daijia.model.entity.driver.DriverSet;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class DriverInfoServiceImpl extends ServiceImpl<DriverInfoMapper, DriverInfo> implements DriverInfoService {

    @Autowired
    private WxMaService wxMaService;

    @Autowired
    private DriverInfoMapper driverInfoMapper;

    @Autowired
    private DriverSetMapper driverSetMapper;

    @Autowired
    private DriverAccountMapper driverAccountMapper;

    @Autowired
    private DriverLoginLogMapper driverLoginLogMapper;

    //小程序授权登陆
    @Override
    public Long login(String code) {
        //1.获取code值
        try {
            WxMaJscode2SessionResult sessionInfo = wxMaService.getUserService().getSessionInfo(code);
            //2.获取openid
            String openid = sessionInfo.getOpenid();
            //3.根据openid查询用户是否是第一次登录
            LambdaQueryWrapper<DriverInfo> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(DriverInfo::getWxOpenId, openid);
            DriverInfo driverInfo = driverInfoMapper.selectOne(wrapper);
            //4.如果是第一次登录，新增用户信息
            if (driverInfo == null) {
                //1. 新增司机基本信息
                driverInfo = new DriverInfo();
                driverInfo.setNickname(String.valueOf(System.currentTimeMillis()));
                driverInfo.setAvatarUrl("https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
                driverInfo.setWxOpenId(openid);
                driverInfoMapper.insert(driverInfo);

                //2. 初始化司机设置
                DriverSet driverSet = new DriverSet();
                driverSet.setDriverId(driverInfo.getId());
                driverSet.setOrderDistance(new BigDecimal(0));//0：无限制
                driverSet.setAcceptDistance(new BigDecimal(SystemConstant.ACCEPT_DISTANCE));//默认接单范围：5公里
                driverSet.setIsAutoAccept(0);//0：否 1：是
                driverSetMapper.insert(driverSet);

                //3. 初始化司机账户信息
                DriverAccount driverAccount = new DriverAccount();
                driverAccount.setDriverId(driverInfo.getId());
                driverAccountMapper.insert(driverAccount);

            }
            //5. 记录司机登录信息
            DriverLoginLog driverLoginLog = new DriverLoginLog();
            driverLoginLog.setDriverId(driverInfo.getId());
            driverLoginLog.setMsg("小程序授权登陆");
            driverLoginLogMapper.insert(driverLoginLog);

            //6. 返回司机id
            return driverInfo.getId();

        } catch (WxErrorException e) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
    }
}