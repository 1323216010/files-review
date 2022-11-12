package com.atguigu.ggkt.vod.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author sxwh
 **/
@Component
public class StaticGetPrivate {

    private static StaticGetPrivate staticGetPrivate;

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @PostConstruct
    public void init() {
        staticGetPrivate = this;
        staticGetPrivate.javaMailSender = this.javaMailSender;
        staticGetPrivate.emailFrom = this.emailFrom;
    }

    public static JavaMailSender getMailSender() {
        return staticGetPrivate.javaMailSender;
    }

    public static String getEmailFrom() {
        return staticGetPrivate.emailFrom;
    }
}
