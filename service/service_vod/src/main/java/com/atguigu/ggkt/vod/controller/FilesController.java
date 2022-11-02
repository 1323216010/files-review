package com.atguigu.ggkt.vod.controller;

import com.atguigu.ggkt.vod.domain.Files;
import com.atguigu.ggkt.vod.mapper.FilesMapper;
import com.atguigu.ggkt.vod.service.FilesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Files)表控制层
 *
 * @author makejava
 * @since 2022-11-02 13:23:35
 */
@Api(tags = {"FilesController"}, description = "相关接口")
@RestController
@RequestMapping("files")
@RequiredArgsConstructor
public class FilesController {
    /**
     * 服务对象
     */
    @Resource
    private FilesService filesService;

    @Resource
    private FilesMapper filesMapper;
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "根据唯一标志获取详情", httpMethod = "GET")
    @GetMapping("{id}")
    public ResponseEntity<Files> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.filesService.queryById(id));
    }

    /**
     * 分页查询
     *
     * @param
     * @return 数据
     */
    @ApiOperation(value = "根据唯一标志获取详情", httpMethod = "GET")
    @GetMapping("{offset}/{limit}")
    public ResponseEntity<List<Files>> queryByLimit(@PathVariable("offset") Integer offset, @PathVariable("limit") Integer limit) {
        return ResponseEntity.ok(this.filesService.queryAllByLimit(offset, limit));
    }

    /**
     * 更新
     *
     * @param
     * @return 数据
     */
    @ApiOperation(value = "根据唯一标志获取详情", httpMethod = "GET")
    @GetMapping("updateByName/{name}/{url}")
    public ResponseEntity<Integer> updateByName(@PathVariable("name") String name, @PathVariable("url") String url) {
        return ResponseEntity.ok(this.filesMapper.updateByName(name, url));
    }


    /**
     * 新增数据
     *
     * @param files 实体
     * @return 新增结果
     */
    @ApiOperation(value = "新增", httpMethod = "POST")
    @PostMapping("add")
    public ResponseEntity<Files> add(@RequestBody Files files) {
        return ResponseEntity.ok(this.filesService.insert(files));
    }

    /**
     * 编辑数据
     *
     * @param files 实体
     * @return 编辑结果
     */
    @ApiOperation(value = "修改", httpMethod = "POST")
    @PutMapping("edit")
    public ResponseEntity<Files> edit(@RequestBody Files files) {
        return ResponseEntity.ok(this.filesService.update(files));
    }

    /**
     * 删除数据
     *
     * @param name
     * @return 删除是否成功
     */
    @ApiOperation(value = "删除", httpMethod = "GET")
    @GetMapping("delete/{name}")
    public ResponseEntity<Integer> deleteById(@PathVariable("name") String name) {
        return ResponseEntity.ok(this.filesMapper.deleteByName(name));
    }

}

