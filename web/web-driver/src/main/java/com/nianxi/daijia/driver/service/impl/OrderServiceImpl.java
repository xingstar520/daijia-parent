package com.nianxi.daijia.driver.service.impl;

import com.nianxi.daijia.common.result.Result;
import com.nianxi.daijia.driver.service.OrderService;
import com.nianxi.daijia.map.client.MapFeignClient;
import com.nianxi.daijia.model.form.customer.ExpectOrderForm;
import com.nianxi.daijia.model.form.map.CalculateDrivingLineForm;
import com.nianxi.daijia.model.form.rules.FeeRuleRequestForm;
import com.nianxi.daijia.model.vo.customer.ExpectOrderVo;
import com.nianxi.daijia.model.vo.map.DrivingLineVo;
import com.nianxi.daijia.model.vo.rules.FeeRuleResponseVo;
import com.nianxi.daijia.order.client.OrderInfoFeignClient;
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

}
