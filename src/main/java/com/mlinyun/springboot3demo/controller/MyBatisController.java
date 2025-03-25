package com.mlinyun.springboot3demo.controller;

import com.mlinyun.springboot3demo.dao.UserDao;
import com.mlinyun.springboot3demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyBatisController {

    private final UserDao userDao;

    @Autowired
    public MyBatisController(UserDao userDao) {
        this.userDao = userDao;
    }

    // 获取所有用户
    @GetMapping("/users/queryAllUser")
    public List<User> queryAll() {
        return userDao.findAllUsers();
    }

    // 根据 id 查询用户
    @GetMapping("/users/queryUserById")
    public User queryUserById(@RequestParam(value = "id", required = true) Integer id) {
        return userDao.findUserById(id);
    }

    // 新增用户
    @GetMapping("/users/addUser")
    public Boolean addUser(@RequestParam(value = "name", required = true) String name,
                           @RequestParam(value = "password", required = true) String password) {
        // 判断参数是否为空
        if (!StringUtils.hasText(name) || !StringUtils.hasText(password)) {
            return false;
        }
        // 判断数据库中是否已经存在该用户
        User existingUser = userDao.findUserByName(name);
        if (existingUser != null) {
            return false;
        }
        // 新增用户
        User newUser = new User();
        newUser.setName(name);
        newUser.setPassword(password);
        return userDao.addUser(newUser) > 0;
    }

    // 更新用户信息
    @GetMapping("/users/updateUser")
    public Boolean updateUser(@RequestParam(value = "id", required = true) Integer id,
                              @RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "password", required = true) String password) {
        // 判断参数是否为空
        if (!StringUtils.hasText(name) || !StringUtils.hasText(password)) {
            return false;
        }
        // 校验 id 是否合法
        if (id <= 0) {
            return false;
        }
        // 判断数据库中是否已经存在该用户
        User existingUser = userDao.findUserById(id);
        if (existingUser == null) {
            return false;
        }
        // 判断数据库中是否已经存在该用户名
        User existingUserByName = userDao.findUserByName(name);
        // 如果存在该用户名，且不是当前用户，则返回 false
        if (existingUserByName != null && !existingUserByName.getId().equals(id)) {
            return false;
        }
        // 更新用户信息
        User updatedUser = new User();
        updatedUser.setId(id);
        updatedUser.setName(name);
        updatedUser.setPassword(password);
        return userDao.updateUser(updatedUser) > 0;
    }

    // 根据 id 删除用户
    @GetMapping("/users/deleteUserById")
    public Boolean deleteUserById(@RequestParam(value = "id", required = true) Integer id) {
        return userDao.deleteUser(id) > 0;
    }

}
