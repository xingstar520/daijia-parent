package com.nianxi.daijia.customer.controller;

import com.nianxi.daijia.common.result.Result;
import com.nianxi.daijia.customer.service.CustomerInfoService;
import com.nianxi.daijia.model.entity.customer.CustomerInfo;
import com.nianxi.daijia.model.form.customer.UpdateWxPhoneForm;
import com.nianxi.daijia.model.vo.customer.CustomerLoginVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/customer/info")
@SuppressWarnings({"unchecked", "rawtypes"})
public class CustomerInfoController {

	@Autowired
	private CustomerInfoService customerInfoService;

	//微信小程序登录接口
	@Operation(summary = "小程序授权登录")
	@GetMapping("/login/{code}")
	public Result<Long> login(@PathVariable String code) {
		return Result.ok(customerInfoService.login(code));
	}

	//获取客户基本信息
	@Operation(summary = "获取客户基本信息")
	@GetMapping("/getCustomerInfo/{customerId}")
	public Result<CustomerInfo> getCustomerInfo(@PathVariable Long customerId) {
		return Result.ok(customerInfoService.getById(customerId));
	}

	//获取客户登录信息
	@Operation(summary = "获取客户登录信息")
	@GetMapping("/getCustomerLoginInfo/{customerId}")
	public Result<CustomerLoginVo> getCustomerLoginInfo(@PathVariable Long customerId) {
		CustomerLoginVo customerLoginVo = customerInfoService.getCustomerLoginInfo(customerId);
		return Result.ok(customerLoginVo);
	}

	//更新客户微信手机号码
	@Operation(summary = "更新客户微信手机号码")
	@GetMapping("/updateWxPhoneNumber")
	public Result<Boolean> updateWxPhoneNumber(@RequestBody UpdateWxPhoneForm updateWxPhoneForm) {
		return Result.ok(customerInfoService.updateWxPhoneNumber(updateWxPhoneForm));
	}
}

