package com.mlinyun.springboot3demo.controller;

import com.mlinyun.springboot3demo.entity.TbUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor // 生成一个包含常量，构造方法，@Autowired 注解的构造方法
public class RedisController {

    private final StringRedisTemplate stringRedisTemplate;

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 向 Redis 中添加字符串
     *
     * @param key   键
     * @param value 值
     * @return 是否添加成功
     */
    @GetMapping("/addStringToRedis")
    @ResponseBody
    public Boolean addStringToRedis(String key, String value) {
        try {
            stringRedisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从 Redis 中获取字符串
     *
     * @param key 键
     * @return 值
     */
    @GetMapping("/getStringFromRedis")
    @ResponseBody
    public String getStringFromRedis(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 从 Redis 中删除字符串
     *
     * @param key 键
     * @return 是否删除成功
     */
    @GetMapping("/deleteStringFromRedis")
    @ResponseBody
    public Boolean deleteStringFromRedis(String key) {
        return stringRedisTemplate.delete(key);
    }

    /**
     * 向 Redis 中添加用户对象
     *
     * @param username 用户名
     * @param password 密码
     * @return 是否添加成功
     */
    @GetMapping("/addUserToRedis")
    @ResponseBody
    public Boolean addUserToRedis(String username, String password) {
        try {
            TbUser tbUser = new TbUser();
            tbUser.setUsername(username);
            tbUser.setPassword(password);
            redisTemplate.opsForValue().set(username, tbUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从 Redis 中获取用户对象
     *
     * @param username 用户名
     * @return 用户对象
     */
    @GetMapping("/getUserFromRedis")
    @ResponseBody
    public TbUser getUserFromRedis(String username) {
        return (TbUser) redisTemplate.opsForValue().get(username);
    }

    /**
     * 从 Redis 中删除用户对象
     *
     * @param username 用户名
     * @return 是否删除成功
     */
    @GetMapping("/deleteUserFromRedis")
    @ResponseBody
    public Boolean deleteUserFromRedis(String username) {
        return redisTemplate.delete(username);
    }

}
