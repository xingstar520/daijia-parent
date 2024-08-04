package com.nianxi.daijia.customer.service.impl;

import com.nianxi.daijia.customer.mapper.CustomerInfoMapper;
import com.nianxi.daijia.customer.service.CustomerInfoService;
import com.nianxi.daijia.model.entity.customer.CustomerInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class CustomerInfoServiceImpl extends ServiceImpl<CustomerInfoMapper, CustomerInfo> implements CustomerInfoService {

}
