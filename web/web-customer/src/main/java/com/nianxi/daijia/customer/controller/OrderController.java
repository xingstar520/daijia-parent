package com.nianxi.daijia.customer.controller;

import com.nianxi.daijia.common.login.NianxiLogin;
import com.nianxi.daijia.common.result.Result;
import com.nianxi.daijia.common.util.AuthContextHolder;
import com.nianxi.daijia.customer.service.OrderService;
import com.nianxi.daijia.model.form.customer.ExpectOrderForm;
import com.nianxi.daijia.model.form.customer.SubmitOrderForm;
import com.nianxi.daijia.model.vo.customer.ExpectOrderVo;
import com.nianxi.daijia.model.vo.order.CurrentOrderInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "订单API接口管理")
@RestController
@RequestMapping("/order")
@SuppressWarnings({"unchecked", "rawtypes"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    //XJJ TODD 2024/8/12:后续完善，目前假设乘客端没有订单
    //查找乘客端当前订单
    @Operation(summary = "查找乘客端当前订单")
    @NianxiLogin
    @GetMapping("/searchCustomerCurrentOrder")
    public Result<CurrentOrderInfoVo> searchCustomerCurrentOrder() {
        CurrentOrderInfoVo currentOrderInfoVo = new CurrentOrderInfoVo();
        currentOrderInfoVo.setIsHasCurrentOrder(false);
        return Result.ok(currentOrderInfoVo);
    }

    //预估订单数据
    @Operation(summary = "预估订单数据")
    @NianxiLogin
    @PostMapping("/expectOrder")
    public Result<ExpectOrderVo> expectOrder(@RequestBody ExpectOrderForm expectOrderForm) {
        return Result.ok(orderService.expectOrder(expectOrderForm));
    }

    //乘客下单
    @Operation(summary = "乘客下单")
    @NianxiLogin
    @PostMapping("/submitOrder")
    public Result<Long> submitOrder(@RequestBody SubmitOrderForm submitOrderForm) {
        submitOrderForm.setCustomerId(AuthContextHolder.getUserId());//设置乘客id
        return Result.ok(orderService.submitOrder(submitOrderForm));
    }

    //查询订单状态
    @Operation(summary = "查询订单状态")
    @NianxiLogin
    @GetMapping("/getOrderStatus/{orderId}")
    public Result<Integer> getOrderStatus(@PathVariable Long orderId) {
        return Result.ok(orderService.getOrderStatus(orderId));
    }
}

