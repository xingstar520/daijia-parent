package com.nianxi.daijia.mgr.service.impl;

import com.nianxi.daijia.mgr.service.OrderInfoService;
import com.nianxi.daijia.order.client.OrderInfoFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class OrderInfoServiceImpl implements OrderInfoService {

	@Autowired
	private OrderInfoFeignClient orderInfoFeignClient;



}
