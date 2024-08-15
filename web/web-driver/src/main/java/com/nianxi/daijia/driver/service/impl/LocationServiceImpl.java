package com.nianxi.daijia.driver.service.impl;

import com.nianxi.daijia.common.execption.GuiguException;
import com.nianxi.daijia.common.result.Result;
import com.nianxi.daijia.common.result.ResultCodeEnum;
import com.nianxi.daijia.driver.client.DriverInfoFeignClient;
import com.nianxi.daijia.driver.service.LocationService;
import com.nianxi.daijia.map.client.LocationFeignClient;
import com.nianxi.daijia.model.entity.driver.DriverSet;
import com.nianxi.daijia.model.form.map.UpdateDriverLocationForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationFeignClient locationFeignClient;

    @Autowired
    private DriverInfoFeignClient driverInfoFeignClient;

    //司机开启接单，更新司机位置信息
    @Override
    public Boolean updateDriverLocation(UpdateDriverLocationForm updateDriverLocationForm) {
        //根据司机id获取司机个性化设置信息
        Long driverId = updateDriverLocationForm.getDriverId();
        Result<DriverSet> driverSetResult = driverInfoFeignClient.getDriverSet(driverId);
        DriverSet driverSet = driverSetResult.getData();

        //判断：如果司机开始接单，更新位置信息
        if (driverSet.getServiceStatus() == 1){
            Result<Boolean> booleanResult = locationFeignClient.updateDriverLocation(updateDriverLocationForm);
            return booleanResult.getData();
        } else {
            //没有接单
            throw new GuiguException(ResultCodeEnum.NO_START_SERVICE);
        }
    }

    //司机关闭接单，删除司机位置信息
    @Override
    public Boolean removeDriverLocation(Long driverId) {
        Result<Boolean> booleanResult = locationFeignClient.removeDriverLocation(driverId);
        return booleanResult.getData();
    }
}
