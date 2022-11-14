package com.atguigu.ggkt.vod.service.impl;

import com.atguigu.ggkt.vod.domain.Files;
import com.atguigu.ggkt.vod.mapper.FilesMapper;
import com.atguigu.ggkt.vod.service.FilesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Files)表服务实现类
 *
 * @author yan
 * @since 2022-11-14 20:50:28
 */
@Service("filesService")
public class FilesServiceImpl implements FilesService {

    @Resource
    private FilesMapper filesMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Files queryById(Integer id) {
        return this.filesMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Files> queryAllByLimit(int offset, int limit) {
        return this.filesMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param files 实例对象
     * @return 实例对象
     */
    @Override
    public Files insert(Files files) {
        this.filesMapper.insert(files);
        return files;
    }

    @Override
    public int deleteByName(String name) {
        return this.filesMapper.deleteByName(name);
    }

    /**
     * 修改数据
     *
     * @param files 实例对象
     * @return 实例对象
     */
    @Override
    public Files update(Files files) {
        this.filesMapper.update(files);
        return this.queryById(files.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.filesMapper.deleteById(id) > 0;
    }
}

