package com.jarvan.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.UUID;

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
        logger.info("===========log-mdc测试===========");
        new Thread(() -> {
            String traceId = MDC.get("traceId");
            logger.info("从MDC中获取链路id为{}", traceId);
            logger.info("test runnable traceId");
        }).start();
    }
}
