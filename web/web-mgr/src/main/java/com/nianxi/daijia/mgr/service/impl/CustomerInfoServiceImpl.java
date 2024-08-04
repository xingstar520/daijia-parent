package com.nianxi.daijia.mgr.service.impl;

import com.nianxi.daijia.customer.client.CustomerInfoFeignClient;
import com.nianxi.daijia.mgr.service.CustomerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class CustomerInfoServiceImpl implements CustomerInfoService {

	@Autowired
	private CustomerInfoFeignClient customerInfoFeignClient;



}
