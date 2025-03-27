package com.mlinyun.springboot3demo.controller;

import com.mlinyun.springboot3demo.entity.TbUser;
import com.mlinyun.springboot3demo.entity.request.UserDeleteRequest;
import com.mlinyun.springboot3demo.entity.request.UserRegisterOrLoginRequest;
import com.mlinyun.springboot3demo.service.TbUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "用户管理", description = "用户注册、登录、查询、更新、删除等操作")
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
    @Operation(summary = "用户注册", description = "根据用户名和密码创建新用户")
    @Parameter(name = "userRegisterOrLoginRequest", description = "用户注册请求对象", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "注册成功，返回用户 ID"),
            @ApiResponse(responseCode = "400", description = "请求参数有误", content = @Content)
    })
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
    @Operation(summary = "用户登录", description = "根据用户名和密码进行登录验证")
    @Parameters({
            @Parameter(name = "userRegisterOrLoginRequest", description = "用户登录请求对象", required = true),
            @Parameter(name = "request", description = "请求对象", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "登录成功，返回用户信息"),
            @ApiResponse(responseCode = "400", description = "请求参数有误", content = @Content),
            @ApiResponse(responseCode = "401", description = "登录失败", content = @Content)
    })
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
    @Operation(summary = "删除用户", description = "根据用户 ID 删除指定用户")
    @Parameters({
            @Parameter(name = "userDeleteRequest", description = "用户删除请求对象", required = true),
            @Parameter(name = "request", description = "请求对象", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功"),
            @ApiResponse(responseCode = "400", description = "请求参数有误", content = @Content),
            @ApiResponse(responseCode = "403", description = "无权限删除", content = @Content)
    })
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
    @Operation(summary = "更新用户信息", description = "根据用户对象更新用户信息")
    @Parameter(name = "user", description = "用户对象", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "更新成功"),
            @ApiResponse(responseCode = "400", description = "请求参数有误", content = @Content)
    })
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
    @Operation(summary = "查询用户", description = "根据用户名模糊查询用户信息")
    @Parameters({
            @Parameter(name = "username", description = "用户名", required = true),
            @Parameter(name = "request", description = "请求对象", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功"),
            @ApiResponse(responseCode = "403", description = "无权限查询", content = @Content)
    })
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
    @Operation(summary = "获取当前用户信息", description = "获取当前登录用户的详细信息")
    @Parameter(name = "request", description = "请求对象", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功"),
            @ApiResponse(responseCode = "401", description = "未登录", content = @Content)
    })
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
    @Operation(summary = "用户注销", description = "退出当前用户登录状态")
    @Parameter(name = "request", description = "请求对象", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "注销成功"),
            @ApiResponse(responseCode = "400", description = "注销失败", content = @Content)
    })
    public boolean logout(HttpServletRequest request) {
        if (request == null) {
            return false;
        }
        return tbUserService.userLogout(request);
    }

}
