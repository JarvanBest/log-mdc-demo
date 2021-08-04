package com.jarvan.demo;

import com.jarvan.demo.controller.TestController;
import com.jarvan.demo.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 描述：
 *
 * @author 含光
 * @email jarvan_best@163.com
 * @date 2021/3/28 2:54 下午
 * @company 数海掌讯
 */
@SpringBootApplication
@EnableAsync
public class LogMDCApp {
    private static final Logger logger = LoggerFactory.getLogger(LogMDCApp.class);

    public static void main(String[] args) {
        SpringApplication.run(LogMDCApp.class, args);
        MDC.put("traceId", UUID.randomUUID().toString());
        MDC.put("UID", "用户id");

        new Thread(() -> logger.info("get thread traceId")).start();

        Collections.singletonList(1).forEach(item -> logger.info("get stream traceId "));

        asyncExecute(() -> logger.info("get thread pool traceId"));

        testAsync(() -> logger.info("get spring async traceId"));

    }

    public static void asyncExecute(Runnable task) {
        TaskExecutor taskExecutor = SpringUtil.getBean("taskExecutor", TaskExecutor.class);
        if (taskExecutor != null && task != null) {
            taskExecutor.execute(task);
        }
    }

    public static void testAsync(Runnable task) {
        TestController testController = SpringUtil.getBean("testController", TestController.class);
        if (testController != null) {
            testController.test();
            task.run();
        }
    }


}
