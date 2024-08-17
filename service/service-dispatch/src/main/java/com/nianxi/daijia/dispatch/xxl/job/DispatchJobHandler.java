package com.nianxi.daijia.dispatch.xxl.job;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @author Jie.
 * @description: TODO
 * @date 2024/8/17
 * @version: 1.0
 */
@Component
public class DispatchJobHandler {

    @XxlJob("firstJobHandler")
    public void testJobHandler() {
        System.out.println("xxl-job项目启动成功");
    }
}
