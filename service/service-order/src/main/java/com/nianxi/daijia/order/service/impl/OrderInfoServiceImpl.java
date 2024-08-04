package com.nianxi.daijia.order.service.impl;

import com.nianxi.daijia.model.entity.order.OrderInfo;
import com.nianxi.daijia.order.mapper.OrderInfoMapper;
import com.nianxi.daijia.order.service.OrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {


}
