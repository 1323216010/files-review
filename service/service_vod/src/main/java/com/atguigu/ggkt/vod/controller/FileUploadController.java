package com.atguigu.ggkt.vod.controller;

import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vod.config.StaticVariables;
import com.atguigu.ggkt.vod.domain.Files;
import com.atguigu.ggkt.vod.service.FileService;
import com.atguigu.ggkt.vod.service.FilesService;
import com.atguigu.ggkt.vod.utils.MethodUtils;
import com.atguigu.ggkt.vod.utils.StaticGetPrivate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "文件上传接口")
@RestController
@RequestMapping("/admin/vod/file")
//@CrossOrigin
public class FileUploadController {

    @Autowired
    private FileService fileService;

    @Resource
    private FilesService filesService;


    private static final Charset DEFAULT_CHARSET;

    static {
        DEFAULT_CHARSET = StandardCharsets.UTF_8;
    }

    @ApiOperation("文件上传")
    @PostMapping("upload")
    public Result uploadFile(MultipartFile file) {
        String url = fileService.upload(file);
        return Result.ok(url).message("上传文件成功");
    }

    @ApiOperation("文件上传")
    @PostMapping("fileUpload")
    public Result fileUpload(@RequestParam("file") MultipartFile file) {

        String url = StaticVariables.fileUpload;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("file", file.getResource());
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
        StaticGetPrivate.getTemplates().exchange(url, HttpMethod.POST, requestEntity, String.class);

        Files files = new Files();
        files.setTitle(MethodUtils.getFileTitle(file.getOriginalFilename()));
        files.setName(file.getOriginalFilename());
        files.setVolume(MethodUtils.getFileSize(String.valueOf(file.getSize())));
        files.setType(MethodUtils.getFileType(file.getOriginalFilename()));

        String url0 = StaticVariables.path + file.getOriginalFilename();
        url0 = new String(Base64Utils.encode(url0.getBytes(DEFAULT_CHARSET)), StandardCharsets.UTF_8);
        files.setUrl(MethodUtils.stringToUrl(url0));

        filesService.insert(files);

        return Result.ok(url).message("上传文件成功");
    }

    @GetMapping("/deleteFile")
    public String deleteFile(@RequestParam("fileName") String fileName) {

        try {
            fileName = URLDecoder.decode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Map<String,Object> map = new HashMap<>();
        map.put("fileName",fileName);
        StaticGetPrivate.getTemplates().getForObject(StaticVariables.fileDelete + "?fileName={fileName}",String.class,map);

        filesService.deleteByName(fileName);

        return "文件删除成功";
    }
}
