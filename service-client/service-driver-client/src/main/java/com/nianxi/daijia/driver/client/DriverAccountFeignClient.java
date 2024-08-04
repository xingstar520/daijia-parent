package com.nianxi.daijia.driver.client;

import com.nianxi.daijia.common.result.Result;
import com.nianxi.daijia.model.form.driver.TransferForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "service-driver")
public interface DriverAccountFeignClient {


}