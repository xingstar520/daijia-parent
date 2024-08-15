package com.nianxi.daijia.order.service;

import com.nianxi.daijia.model.entity.order.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nianxi.daijia.model.form.order.OrderInfoForm;

public interface OrderInfoService extends IService<OrderInfo> {

    //保存订单信息
    Long saveOrderInfo(OrderInfoForm orderInfoForm);

    //获取订单状态
    Integer getOrderStatus(Long orderId);
}
