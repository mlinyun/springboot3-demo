package com.mlinyun.springboot3demo.controller;

import com.mlinyun.springboot3demo.entity.TbUser;
import com.mlinyun.springboot3demo.entity.request.UserDeleteRequest;
import com.mlinyun.springboot3demo.entity.request.UserRegisterOrLoginRequest;
import com.mlinyun.springboot3demo.service.TbUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final TbUserService tbUserService;

    @Autowired
    public UserController(TbUserService tbUserService) {
        this.tbUserService = tbUserService;
    }

    /**
     * 用户注册
     *
     * @param userRegisterOrLoginRequest 用户注册请求体
     * @return 新用户 id
     */
    @PostMapping("/register")
    public long register(@RequestBody UserRegisterOrLoginRequest userRegisterOrLoginRequest) {
        if (userRegisterOrLoginRequest == null) {
            return -1;
        }
        String username = userRegisterOrLoginRequest.getUsername();
        String password = userRegisterOrLoginRequest.getPassword();
        if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
            return -1;
        }
        return tbUserService.userRegister(username, password);
    }

    /**
     * 用户登录
     *
     * @param userRegisterOrLoginRequest 用户登录请求体
     * @return 用户登录状态
     */
    @PostMapping("/login")
    public TbUser login(@RequestBody UserRegisterOrLoginRequest userRegisterOrLoginRequest, HttpServletRequest request) {
        if (userRegisterOrLoginRequest == null) {
            return null;
        }
        String username = userRegisterOrLoginRequest.getUsername();
        String password = userRegisterOrLoginRequest.getPassword();
        if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
            return null;
        }
        return tbUserService.userLogin(username, password, request);
    }

    /**
     * 删除用户
     *
     * @param userDeleteRequest 用户删除请求体
     * @param request           请求
     * @return 删除结果（true：删除成功，false：删除失败）
     */
    @PostMapping("/delete")
    public boolean delete(@RequestBody UserDeleteRequest userDeleteRequest, HttpServletRequest request) {
        // 非空判断
        if (userDeleteRequest == null) {
            return false;
        }
        Integer id = userDeleteRequest.getId();
        return tbUserService.deleteUser(id, request);
    }

    /**
     * 更新用户
     *
     * @param user 用户对象
     * @return 更新成功返回 1，否则返回 0
     */
    @PostMapping("/update")
    public boolean updateUser(@RequestBody TbUser user) {
        return tbUserService.updateUser(user);
    }

    /**
     * 查询用户
     *
     * @param username 用户名
     * @param request  请求
     * @return 查询到的用户列表
     */
    @GetMapping("/search")
    public List<TbUser> searchUsers(@RequestParam(value = "username", required = true) String username, HttpServletRequest request) {
        return tbUserService.searchUsers(username, request);
    }

    /**
     * 获取当前用户信息
     *
     * @param request 请求
     * @return 当前登录的用户信息
     */
    @GetMapping("/currentUser")
    public TbUser getCurrentUser(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        return tbUserService.getCurrentUser(request);
    }

    /**
     * 用户注销
     *
     * @param request 请求
     * @return true - 注销成功 false - 注销失败
     */
    @PostMapping("/logout")
    public boolean logout(HttpServletRequest request) {
        if (request == null) {
            return false;
        }
        return tbUserService.userLogout(request);
    }

}
