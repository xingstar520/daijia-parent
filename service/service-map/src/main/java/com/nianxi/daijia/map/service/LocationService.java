package com.nianxi.daijia.map.service;

import com.nianxi.daijia.model.form.map.SearchNearByDriverForm;
import com.nianxi.daijia.model.form.map.UpdateDriverLocationForm;
import com.nianxi.daijia.model.vo.map.NearByDriverVo;

import java.util.List;

public interface LocationService {

    //司机开启接单，更新司机位置信息
    Boolean updateDriverLocation(UpdateDriverLocationForm updateDriverLocationForm);

    //司机关闭接单，删除司机位置信息
    Boolean removeDriverLocation(Long driverId);

    //搜索附近满足条件的司机
    List<NearByDriverVo> searchNearByDriver(SearchNearByDriverForm searchNearByDriverForm);
}
