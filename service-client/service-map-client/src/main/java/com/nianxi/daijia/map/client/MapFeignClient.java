package com.nianxi.daijia.map.client;

import com.nianxi.daijia.common.result.Result;
import com.nianxi.daijia.model.form.map.CalculateDrivingLineForm;
import com.nianxi.daijia.model.form.map.SearchNearByDriverForm;
import com.nianxi.daijia.model.vo.map.DrivingLineVo;
import com.nianxi.daijia.model.vo.map.NearByDriverVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "service-map")
public interface MapFeignClient {

    //计算驾驶路线
    @PostMapping("/map/calculateDrivingLine")
    Result<DrivingLineVo> calculateDrivingLine(@RequestBody CalculateDrivingLineForm calculateDrivingLineForm);
   }