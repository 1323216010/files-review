package com.atguigu.ggkt.vod.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vod.domain.LoginForm;
import com.atguigu.ggkt.vod.mapper.UserMapper;
import com.atguigu.ggkt.vod.utils.MethodUtils;
import com.atguigu.ggkt.vod.utils.StaticGetPrivate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/admin/vod/user")
@RequiredArgsConstructor
//@CrossOrigin //跨域
public class UserLoginController {

    private final UserMapper userMapper;

    private final RedisTemplate redisTemplate;

    //login
    @PostMapping("login")
    public Result login(@RequestBody LoginForm loginForm) {
        StpUtil.login(loginForm.getUsername());
        System.out.println("StpUtil.getTokenValue()---->" + StpUtil.getTokenValue());
        //{"code":20000,"data":{"token":"admin-token"}}
        Map<String,Object> map = new HashMap<>();
        if(loginForm.getPassword().equals(userMapper.selectByUserName(loginForm.getUsername()).getPassword())) {
            map.put("token",StpUtil.getTokenValue());
        } else if ( loginForm.getPassword().equals(redisTemplate.opsForValue().get(loginForm.getUsername()))) {
            map.put("token",StpUtil.getTokenValue());
        }
        System.out.println("map:---->" + map);
        return Result.ok(map);
    }

    @PostMapping("sendMsg")
    public Result emailLogin(@RequestBody LoginForm loginForm) {
        StpUtil.login(loginForm.getUsername());
        String code = MethodUtils.generateValidateCode(6).toString();
        // 验证码由保存到session 优化为 缓存到Redis中，并且设置验证码的有效时间为 5分钟
        redisTemplate.opsForValue().set(loginForm.getUsername(), code, 5, TimeUnit.MINUTES);
        String context = "欢迎使用你的小月亮项目，登录验证码为: " + code + ",五分钟内有效，请妥善保管!";
        Map<String,Object> map = new HashMap<>();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(StaticGetPrivate.getEmailFrom());
        mailMessage.setTo(loginForm.getUsername());
        mailMessage.setSubject("yan验证码");
        mailMessage.setText(context);
        StaticGetPrivate.getMailSender().send(mailMessage);
        return Result.ok();
    }


    //info
    @GetMapping("info")
    public Result info() {
        //{"code":20000,"data":
        // {"roles":["admin"],
        // "introduction":"I am a super administrator",
        // "avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
        // "name":"Super Admin"}}
        Map<String,Object> map = new HashMap<>();
        map.put("roles","admin");
        map.put("introduction","I am a super administrator");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name","Super Admin");
        return Result.ok(map);
    }
}
