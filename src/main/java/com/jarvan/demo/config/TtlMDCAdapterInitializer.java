package com.jarvan.demo.config;

import org.slf4j.TtlMDCAdapter;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 描述：
 *
 * @author 朱佳文
 * @email jarvan_best@163.com
 * @date 2021/3/28 3:59 下午
 * @company 数海掌讯
 */
public class TtlMDCAdapterInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        TtlMDCAdapter.getInstance();
    }
}
