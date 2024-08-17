package com.nianxi.daijia.dispatch.xxl.job;

import com.nianxi.daijia.common.execption.GuiguException;
import com.nianxi.daijia.common.result.ResultCodeEnum;
import com.nianxi.daijia.dispatch.mapper.XxlJobLogMapper;
import com.nianxi.daijia.dispatch.service.NewOrderService;
import com.nianxi.daijia.model.entity.dispatch.XxlJobLog;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jie.
 * @description: TODO
 * @date 2024/8/17
 * @version: 1.0
 */
@Component
public class JobHandler {

    @Autowired
    private XxlJobLogMapper xxlJobLogMapper;

    @Autowired
    private NewOrderService newOrderService;

    @XxlJob("newOrderTaskHandler")
    public void newOrderTaskHandler(){

        //记录任务调度日志
        XxlJobLog xxlJobLog = new XxlJobLog();
        xxlJobLog.setJobId(XxlJobHelper.getJobId());
        long startTime = System.currentTimeMillis();

        try {
            //搜索附近满足条件司机
            newOrderService.executeTask(XxlJobHelper.getJobId());
            // 成功状态
            xxlJobLog.setStatus(1);
        } catch (Exception e){
            // 失败状态
            xxlJobLog.setStatus(0);
            xxlJobLog.setError(e.getMessage());
            throw new GuiguException(ResultCodeEnum.FAIL);
        } finally {
            long times = System.currentTimeMillis()- startTime;
            xxlJobLog.setTimes(times);
            xxlJobLogMapper.insert(xxlJobLog);
        }
    }
}
