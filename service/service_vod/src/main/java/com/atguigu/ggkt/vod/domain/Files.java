package com.atguigu.ggkt.vod.domain;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * (Files)实体类
 *
 * @author yan
 * @since 2022-11-14 20:49:53
 */
@Data
public class Files {

    /**
     * 唯一标识
     */
    private Integer id;

    /**
     * 路径
     */
    private String path;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 访问路径
     */
    private String url;

    /**
     * 所属用户
     */
    private String userName;

    /**
     * 不为null时不在首页展示
     */
    private String secret;

    /**
     * 标题
     */
    private String title;

    /**
     * 星级
     */
    private String star;

    /**
     * 作者
     */
    private String author;

    /**
     * 详细信息
     */
    private String info;

    /**
     * 封面
     */
    private String avatar;

    /**
     * 上传时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @ApiModelProperty(value = "文件大小",name =  "volume",required =  false,example = "")
    private String volume;

}

