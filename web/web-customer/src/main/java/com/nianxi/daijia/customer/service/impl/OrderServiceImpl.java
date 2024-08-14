package com.nianxi.daijia.customer.service.impl;

import com.nianxi.daijia.common.result.Result;
import com.nianxi.daijia.customer.service.OrderService;
import com.nianxi.daijia.map.client.MapFeignClient;
import com.nianxi.daijia.model.form.customer.ExpectOrderForm;
import com.nianxi.daijia.model.form.map.CalculateDrivingLineForm;
import com.nianxi.daijia.model.form.rules.FeeRuleRequestForm;
import com.nianxi.daijia.model.vo.customer.ExpectOrderVo;
import com.nianxi.daijia.model.vo.map.DrivingLineVo;
import com.nianxi.daijia.model.vo.rules.FeeRuleResponseVo;
import com.nianxi.daijia.rules.client.FeeRuleFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class OrderServiceImpl implements OrderService {

    @Autowired
    private FeeRuleFeignClient feeRuleFeignClient;

    @Autowired
    private MapFeignClient mapFeignClient;

    //预估订单数据
    @Override
    public ExpectOrderVo expectOrder(ExpectOrderForm expectOrderForm) {
        //获取驾驶线路
        CalculateDrivingLineForm calculateDrivingLineForm = new CalculateDrivingLineForm();
        BeanUtils.copyProperties(expectOrderForm, calculateDrivingLineForm);

        Result<DrivingLineVo> drivingLineVoResult = mapFeignClient.calculateDrivingLine(calculateDrivingLineForm);
        // 判断 Result 是否为空或调用失败
        if (drivingLineVoResult == null) {
            log.error("获取驾驶线路失败: {}", "返回结果为空");
            throw new RuntimeException("获取驾驶线路失败");
        }
        log.info("drivingLineVoResult: {}", drivingLineVoResult);

        DrivingLineVo drivingLineVo = drivingLineVoResult.getData();
        // 判断 DrivingLineVo 是否为空
        if (drivingLineVo == null) {
            log.error("驾驶线路数据为空");
            throw new RuntimeException("驾驶线路数据为空");
        }


        //获取订单费用
        FeeRuleRequestForm feeRuleRequestForm = new FeeRuleRequestForm();
        feeRuleRequestForm.setDistance(drivingLineVo.getDistance());
        feeRuleRequestForm.setStartTime(new Date());
        feeRuleRequestForm.setWaitMinute((0));
        Result<FeeRuleResponseVo> feeRuleResponseVoResult = feeRuleFeignClient.calculateOrderFee(feeRuleRequestForm);
        FeeRuleResponseVo feeRuleResponseVo = feeRuleResponseVoResult.getData();

        //封装ExpectOrderVo对象
        ExpectOrderVo expectOrderVo = new ExpectOrderVo();
        expectOrderVo.setDrivingLineVo(drivingLineVo);
        expectOrderVo.setFeeRuleResponseVo(feeRuleResponseVo);
        return expectOrderVo;
    }

}
