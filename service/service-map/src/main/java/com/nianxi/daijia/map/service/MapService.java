package com.nianxi.daijia.map.service;

import com.nianxi.daijia.model.form.map.CalculateDrivingLineForm;
import com.nianxi.daijia.model.vo.map.DrivingLineVo;
import org.springframework.web.bind.annotation.PostMapping;

public interface MapService {

    //计算驾驶路线
    DrivingLineVo calculateDrivingLine(CalculateDrivingLineForm calculateDrivingLineForm);
}
