package com.mlinyun.springboot3demo.controller;

import com.mlinyun.springboot3demo.service.TransactionTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TransactionTestController {

    private final TransactionTestService transactionTestService;

    @Autowired
    public TransactionTestController(TransactionTestService transactionTestService) {
        this.transactionTestService = transactionTestService;
    }

    @GetMapping("/withoutTransactionTest")
    @ResponseBody
    public String withoutTransaction() {
        // testWithoutTransaction 方法中没有使用事务
        transactionTestService.testWithoutTransaction();

        return "无事务测试请求完成";
    }

    @GetMapping("/withTransactionTest")
    @ResponseBody
    public String withTransaction() {

        // testWithTransaction 方法中使用了事务
        transactionTestService.testWithTransaction();

        return "有事务测试请求完成";
    }

}
