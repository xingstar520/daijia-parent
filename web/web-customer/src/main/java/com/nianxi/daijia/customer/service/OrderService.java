package com.nianxi.daijia.customer.service;

import com.nianxi.daijia.model.form.customer.ExpectOrderForm;
import com.nianxi.daijia.model.vo.customer.ExpectOrderVo;

public interface OrderService {

    //预估订单数据
    ExpectOrderVo expectOrder(ExpectOrderForm expectOrderForm);
}
