package com.nianxi.daijia.customer.service;

import com.nianxi.daijia.model.form.customer.ExpectOrderForm;
import com.nianxi.daijia.model.form.customer.SubmitOrderForm;
import com.nianxi.daijia.model.vo.customer.ExpectOrderVo;

public interface OrderService {

    //预估订单数据
    ExpectOrderVo expectOrder(ExpectOrderForm expectOrderForm);

    //乘客下单
    Long submitOrder(SubmitOrderForm submitOrderForm);

    //查询订单状态
    Integer getOrderStatus(Long orderId);
}
