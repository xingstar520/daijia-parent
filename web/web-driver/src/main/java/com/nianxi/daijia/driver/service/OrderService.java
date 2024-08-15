package com.nianxi.daijia.driver.service;

import com.nianxi.daijia.model.form.customer.ExpectOrderForm;
import com.nianxi.daijia.model.vo.customer.ExpectOrderVo;

public interface OrderService {

    //查询订单状态
    Integer getOrderStatus(Long orderId);
}
