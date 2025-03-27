package com.mlinyun.springboot3demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mlinyun.springboot3demo.dao.TbUserMapper;
import com.mlinyun.springboot3demo.entity.TbUser;
import com.mlinyun.springboot3demo.service.TbUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.mlinyun.springboot3demo.constant.UserConstant.SALT;
import static com.mlinyun.springboot3demo.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author LinCanhui
 * @description 针对表【tb_user】的数据库操作Service实现
 * @createDate 2025-03-26 01:30:37
 */
@Slf4j
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {

    private final TbUserMapper tbUserMapper;

    @Autowired
    public TbUserServiceImpl(TbUserMapper tbUserMapper) {
        this.tbUserMapper = tbUserMapper;
    }

    /**
     * 用户注册
     *
     * @param username 用户名
     * @param password 密码
     * @return 新用户 id
     */
    @Override
    public long userRegister(String username, String password) {
        // 1. 校验
        // 1.1 校验是否为空
        if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
            return -1;
        }
        // 1.2 校验账号位数（账户长度范围 4-16位）
        if (username.length() < 4 || username.length() > 16) {
            return -1;
        }
        // 1.3 校验密码位数（密码长度范围 8-16位）
        if (password.length() < 8 || password.length() > 16) {
            return -1;
        }
        // 1.4 校验账号不包含特殊字符
        String validPattern = "[\\u00A0\\s\"`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(username);
        if (matcher.find()) {
            return -1;
        }
        // 1.5 账号不能重复
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        long count = tbUserMapper.selectCount(queryWrapper);
        if (count > 0) {
            return -1;
        }

        // 2. 密码加密
        // 使用 String 的加密方法，采用 MD5 加密
        String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes(StandardCharsets.UTF_8));

        // 3. 插入数据库
        TbUser user = new TbUser();
        user.setUsername(username);
        user.setPassword(encryptedPassword);
        return tbUserMapper.insert(user) > 0 ? user.getId() : -1;
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @param request  请求
     * @return 脱敏后的用户信息
     */
    @Override
    public TbUser userLogin(String username, String password, HttpServletRequest request) {
        // 1. 校验
        // 1.1 校验是否为空
        if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
            return null;
        }
        // 1.2 校验账号位数（账户长度范围 4-16位）
        if (username.length() < 4 || username.length() > 16) {
            return null;
        }
        // 1.3 校验密码位数（密码长度范围 8-16位）
        if (password.length() < 8 || password.length() > 16) {
            return null;
        }
        // 1.4 校验账号不包含特殊字符
        String validPattern = "[\\u00A0\\s\"`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(username);
        if (matcher.find()) {
            return null;
        }

        // 2. 密码加密
        // 使用 String 的加密方法，采用 MD5 加密
        String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes(StandardCharsets.UTF_8));

        // 3. 查询数据库
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", encryptedPassword);
        TbUser user = tbUserMapper.selectOne(queryWrapper);
        // 用户不存在
        if (user == null) {
            log.info("用户登录失败，用户名或密码错误"); // 日志记录
            return null;
        }

        // 4. 用户信息脱敏
        TbUser safetyUser = getSafetyUser(user);

        // 5. 记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);

        // 返回脱敏后的用户信息
        return safetyUser;
    }

    /**
     * 用户信息脱敏方法
     *
     * @param originUser 要脱敏的 user 对象
     * @return 脱敏后的用户信息
     */
    @Override
    public TbUser getSafetyUser(TbUser originUser) {
        // 非空判断
        if (originUser == null) {
            return null;
        }
        TbUser safetyUser = new TbUser();
        safetyUser.setId(originUser.getId());
        safetyUser.setUsername(originUser.getUsername());
        return safetyUser;
    }

    /**
     * 删除用户：根据 id 删除用户
     *
     * @param id      要删除用户 id
     * @param request 请求
     */
    @Override
    public boolean deleteUser(Integer id, HttpServletRequest request) {
        // 判断 id 是否合法
        if (id <= 0) {
            return false;
        }
        return tbUserMapper.deleteById(id) > 0;
    }

    /**
     * 更新用户
     *
     * @param user 用户对象
     * @return 更新成功返回 1，否则返回 0
     */
    @Override
    public boolean updateUser(TbUser user) {
        // 非空判断
        if (user == null) {
            return false;
        }
        Integer id = user.getId();
        String username = user.getUsername();
        String password = user.getPassword();

        // 校验 id 是否合法
        if (id == null || id <= 0) {
            return false;
        }
        // 校验账号位数（账户长度范围 4-16位）
        if (username.length() < 4 || username.length() > 16) {
            return false;
        }
        // 校验密码位数（密码长度范围 8-16位）
        if (password.length() < 8 || password.length() > 16) {
            return false;
        }
        // 校验账号不包含特殊字符
        String validPattern = "[\\u00A0\\s\"`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(username);
        if (matcher.find()) {
            return false;
        }
        // 判断数据库中是否存在该用户
        TbUser existingUser = tbUserMapper.selectById(id);
        if (existingUser == null) {
            return false;
        }
        // 判断数据库中是否已经存在该用户名
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        TbUser existingUserByName = tbUserMapper.selectOne(queryWrapper);
        // 如果存在该用户名，且不是当前用户，则返回 false
        if (existingUserByName != null && !existingUserByName.getId().equals(id)) {
            return false;
        }
        // 对密码进行加密
        String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes(StandardCharsets.UTF_8));
        user.setPassword(encryptedPassword);
        return tbUserMapper.updateById(user) > 0;
    }

    /**
     * 查询用户：根据用户名搜索用户
     *
     * @param username 用户名
     * @param request  请求
     * @return 查询到的用户
     */
    @Override
    public List<TbUser> searchUsers(String username, HttpServletRequest request) {
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(username)) {
            queryWrapper.like("username", username);
        }
        List<TbUser> userList = tbUserMapper.selectList(queryWrapper);
        return userList.stream().map(user -> {
            user.setPassword(null);
            return getSafetyUser(user);
        }).collect(Collectors.toList());
    }

    /**
     * 获取当前用户信息
     *
     * @param request 请求
     * @return 当前登录的用户信息
     */
    @Override
    public TbUser getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        TbUser currentUser = (TbUser) userObj;
        if (currentUser == null) {
            return null;
        }
        Integer userId = currentUser.getId();
        // 校验用户是否合法
        if (userId == null || userId <= 0) {
            return null;
        }
        TbUser user = tbUserMapper.selectById(userId);
        return getSafetyUser(user);
    }

    /**
     * 用户注销功能
     *
     * @param request 请求
     * @return true - 注销成功 false - 注销失败
     */
    @Override
    public boolean userLogout(HttpServletRequest request) {
        // 移除登录状态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
    }

}
