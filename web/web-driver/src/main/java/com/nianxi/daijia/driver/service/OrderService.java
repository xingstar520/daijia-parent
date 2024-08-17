package com.nianxi.daijia.driver.service;

import com.nianxi.daijia.model.form.customer.ExpectOrderForm;
import com.nianxi.daijia.model.vo.customer.ExpectOrderVo;
import com.nianxi.daijia.model.vo.order.NewOrderDataVo;

import java.util.List;

public interface OrderService {

    //查询订单状态
    Integer getOrderStatus(Long orderId);

    //查询预期订单
    List<NewOrderDataVo> findNewOrderQueueData(Long driverId);
}
