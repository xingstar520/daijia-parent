package com.nianxi.daijia.customer.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "service-customer")
public interface CustomerInfoFeignClient {


}