package com.mlinyun.springboot3demo.dao;

import com.mlinyun.springboot3demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    /**
     * 获取所有用户
     *
     * @return 用户列表
     */
    List<User> findAllUsers();

    /**
     * 根据 id 查询用户
     *
     * @param id 用户 id
     * @return 用户对象
     */
    User findUserById(@Param("id") Integer id);

    /**
     * 根据用户名查询用户
     *
     * @param name 用户名
     * @return 用户对象，如果不存在则返回 null
     */
    User findUserByName(@Param("name") String name);

    /**
     * 添加用户
     *
     * @param user 用户对象
     * @return 添加成功返回 1，否则返回 0
     */
    int addUser(User user);

    /**
     * 更新用户
     *
     * @param user 用户对象
     * @return 更新成功返回 1，否则返回 0
     */
    int updateUser(User user);

    /**
     * 根据 id 删除用户
     *
     * @param id 用户 id
     * @return 删除成功返回 1，否则返回 0
     */
    int deleteUser(@Param("id") Integer id);

}
