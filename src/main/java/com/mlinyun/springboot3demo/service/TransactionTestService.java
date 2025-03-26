package com.mlinyun.springboot3demo.service;

import com.mlinyun.springboot3demo.utils.NameUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class TransactionTestService {


    private final TbUserService tbUserService;

    @Autowired
    public TransactionTestService(TbUserService tbUserService) {
        this.tbUserService = tbUserService;
    }

    /**
     * 没有事务的测试
     *
     * @return 是否注册成功
     */
    public boolean testWithoutTransaction() {
        // 生成一个随机用户名
        String username = NameUtils.generateRandomUsername();
        log.info("随机生成的用户名（没用事务）: {}", username);
        boolean res = tbUserService.userRegister(username, "12345678") > 0;
        // 发生异常
        int i = 1 / 0;
        return res;
    }

    /**
     * 有事务的测试
     *
     * @return 是否注册成功
     */
    @Transactional
    public boolean testWithTransaction() {
        String username = NameUtils.generateRandomUsername();
        log.info("随机生成的用户名（使用事务）: {}", username);
        boolean res = tbUserService.userRegister(username, "12345678") > 0;
        // 发生异常
        int i = 1 / 0;
        return res;
    }

}
