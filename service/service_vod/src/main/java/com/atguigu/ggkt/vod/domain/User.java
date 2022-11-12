package com.atguigu.ggkt.vod.domain;

import lombok.Data;

@Data
public class User {
    /**
    * 唯一标识
    */
    private Integer id;

    /**
    * 用户名
    */
    private String userName;

    /**
    * 密码
    */
    private String password;

    /**
    * 手机号
    */
    private String phoneNumber;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 出生时间
    */
    private String birthDate;

    /**
    * 生日
    */
    private String bithday;

    /**
    * 身份证号
    */
    private Integer idNumber;

    /**
    * 位置
    */
    private String location;

    /**
    * 家庭住址
    */
    private String homeAddress;

    /**
    * 经度
    */
    private String longitude;

    /**
    * 纬度
    */
    private String latitude;

    /**
    * 头像地址
    */
    private String avatar;

    /**
    * 性别
    */
    private String sex;

    /**
    * 用户昵称
    */
    private String nickname;

    /**
    * 所在城市
    */
    private String city;

    /**
    * 登录ip
    */
    private String ip;
}