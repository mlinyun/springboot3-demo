package com.mlinyun.springboot3demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mlinyun.springboot3demo.entity.TbUser;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * @author LinCanhui
 * @description 针对表【tb_user】的数据库操作Service
 * @createDate 2025-03-26 01:30:37
 */
public interface TbUserService extends IService<TbUser> {

    /**
     * 用户注册
     *
     * @param username 用户名
     * @param password 密码
     * @return 新用户 id
     */
    long userRegister(String username, String password);

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 脱敏后的用户信息
     */
    TbUser userLogin(String username, String password, HttpServletRequest request);

    /**
     * 用户信息脱敏方法
     *
     * @param originUser 要脱敏的 user 对象
     * @return 脱敏后的用户信息
     */
    TbUser getSafetyUser(TbUser originUser);

    /**
     * 删除用户：根据 id 删除用户
     *
     * @param id      要删除用户 id
     * @param request 请求
     */
    boolean deleteUser(Integer id, HttpServletRequest request);

    /**
     * 更新用户
     *
     * @param user 用户对象
     * @return 更新成功返回 1，否则返回 0
     */
    boolean updateUser(TbUser user);

    /**
     * 查询用户：根据用户名搜索用户
     *
     * @param username 用户名
     * @param request  请求
     * @return 查询到的用户
     */
    List<TbUser> searchUsers(String username, HttpServletRequest request);

    /**
     * 获取当前用户信息
     *
     * @param request 请求
     * @return 当前登录的用户信息
     */
    TbUser getCurrentUser(HttpServletRequest request);

    /**
     * 用户注销功能
     *
     * @param request 请求
     * @return true - 注销成功 false - 注销失败
     */
    boolean userLogout(HttpServletRequest request);

}
