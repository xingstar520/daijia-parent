package com.nianxi.daijia.rules.service;

import com.nianxi.daijia.model.form.rules.FeeRuleRequestForm;
import com.nianxi.daijia.model.vo.rules.FeeRuleResponseVo;

public interface FeeRuleService {

    // 计算订单费用
    FeeRuleResponseVo calculateOrderFee(FeeRuleRequestForm calculateOrderFeeForm);
}
