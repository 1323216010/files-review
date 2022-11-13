package com.atguigu.ggkt.vod.controller;

import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vod.service.FileService;
import com.atguigu.ggkt.vod.utils.StaticGetPrivate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "文件上传接口")
@RestController
@RequestMapping("/admin/vod/file")
//@CrossOrigin
public class FileUploadController {

    @Autowired
    private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("upload")
    public Result uploadFile(MultipartFile file) {
        String url = fileService.upload(file);
        return Result.ok(url).message("上传文件成功");
    }

    @ApiOperation("文件上传")
    @PostMapping("fileUpload")
    public Result fileUpload(@RequestParam("file") MultipartFile file) {
        String url = StaticGetPrivate.getKkAddres() + "/fileUpload2";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("file", file.getResource());
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(params, headers);

        StaticGetPrivate.getTemplates().exchange(url, HttpMethod.POST, requestEntity, String.class);
        return Result.ok(url).message("上传文件成功");
    }
}
