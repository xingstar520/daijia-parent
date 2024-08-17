package com.nianxi.daijia.dispatch.service;

import com.nianxi.daijia.model.vo.dispatch.NewOrderTaskVo;
import com.nianxi.daijia.model.vo.order.NewOrderDataVo;

import java.util.List;

public interface NewOrderService {

    //添加并开始新订单任务调度
    Long addAndStartTask(NewOrderTaskVo newOrderTaskVo);

    //搜索附近满足条件司机
    void executeTask(long jobId);

    //查询司机新订单数据
    List<NewOrderDataVo> findNewOrderQueueData(Long driverId);

    //清空新订单队列数据
    Boolean clearNewOrderQueueData(Long driverId);
}
