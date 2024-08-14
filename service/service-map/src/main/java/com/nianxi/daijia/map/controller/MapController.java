package com.nianxi.daijia.map.controller;

import com.nianxi.daijia.common.result.Result;
import com.nianxi.daijia.map.service.MapService;
import com.nianxi.daijia.model.form.map.CalculateDrivingLineForm;
import com.nianxi.daijia.model.vo.driver.DriverLoginVo;
import com.nianxi.daijia.model.vo.map.DrivingLineVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "地图API接口管理")
@RestController
@RequestMapping("/map")
@SuppressWarnings({"unchecked", "rawtypes"})
public class MapController {

    @Autowired
    private MapService mapService;

    //计算驾驶路线
    @Operation(summary = "计算驾驶路线")
    @PostMapping("/calculateDrivingLine")
    public Result<DrivingLineVo> calculateDrivingLine(@RequestBody CalculateDrivingLineForm calculateDrivingLineForm){
        DrivingLineVo drivingLineVo = mapService.calculateDrivingLine(calculateDrivingLineForm);
        return Result.ok(drivingLineVo);
    }
}

