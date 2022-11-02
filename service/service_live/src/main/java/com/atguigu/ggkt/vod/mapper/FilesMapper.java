package com.atguigu.ggkt.vod.mapper;

import com.atguigu.ggkt.vod.domain.Files;

public interface FilesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Files record);

    int insertSelective(Files record);

    Files selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Files record);

    int updateByPrimaryKey(Files record);
}