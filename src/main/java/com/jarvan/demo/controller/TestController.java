package com.jarvan.demo.controller;

import com.jarvan.demo.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 描述：
 *
 * @author 含光
 * @email jarvan_best@163.com
 * @date 2021/3/31 5:01 下午
 * @company 数海掌讯
 */
@Controller
public class TestController {

    @Autowired
    private ITestService testService;

    @GetMapping("testAsync")
    public String test() {
        testService.test();
        return "SUCCESS";
    }
}
