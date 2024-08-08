package com.nianxi.daijia.driver.controller;

import com.nianxi.daijia.common.result.Result;
import com.nianxi.daijia.driver.service.CosService;
import com.nianxi.daijia.model.vo.driver.CosUploadVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Tag(name = "腾讯云cos上传接口管理")
@RestController
@RequestMapping(value="/cos")
@SuppressWarnings({"unchecked", "rawtypes"})
public class CosController {

    @Autowired
    private CosService cosService;

    // 腾讯云cos上传服务
    @Operation(summary = "腾讯云cos上传服务")
    @PostMapping(value = "/upload")
    public Result<CosUploadVo> upload(@RequestPart("file") MultipartFile file,@RequestParam("path") String path) {
           CosUploadVo cosUploadVo = cosService.upload(file,path);
           return Result.ok(cosUploadVo);
    }

}

