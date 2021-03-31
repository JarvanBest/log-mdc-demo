package com.jarvan.demo.service.impl;

import com.jarvan.demo.service.ITestService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 描述：
 *
 * @author 含光
 * @email jarvan_best@163.com
 * @date 2021/3/31 4:51 下午
 * @company 数海掌讯
 */
@Service
public class TestServiceImpl implements ITestService {
    private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Autowired
    private TaskExecutor taskExecutor;

    @Override
    @Async
    public void test() {
        MDC.put("traceId", UUID.randomUUID().toString());
        MDC.put("UID", UUID.randomUUID().toString());
        logger.info("测试Spring自带的Async异步注解");
        taskExecutor.execute(() -> {
            logger.info("测试线程池");
            logger.info("子线程测试，traceId是否和主线程保持一致？");
        });
        logger.info("测试spring 异步调用");
    }
}
