package com.atguigu.ggkt.vod.mapper;

import com.atguigu.ggkt.vod.domain.Files;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface FilesMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Files queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Files> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param files 实例对象
     * @return 对象列表
     */
    List<Files> queryAll(Files files);

    /**
     * 新增数据
     *
     * @param files 实例对象
     * @return 影响行数
     */
    int insert(Files files);

    /**
     * 修改数据
     *
     * @param files 实例对象
     * @return 影响行数
     */
    int update(Files files);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    int deleteByName(String name);


    int deleteByPrimaryKey(Integer id);

    int insertSelective(Files record);

    Files selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Files record);

    int updateByPrimaryKey(Files record);

    int updateByName(String name, String url);
}