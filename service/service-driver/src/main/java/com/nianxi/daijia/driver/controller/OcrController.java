package com.nianxi.daijia.driver.controller;

import com.nianxi.daijia.common.result.Result;
import com.nianxi.daijia.driver.service.OcrService;
import com.nianxi.daijia.model.vo.driver.DriverLicenseOcrVo;
import com.nianxi.daijia.model.vo.driver.IdCardOcrVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Tag(name = "腾讯云识别接口管理")
@RestController
@RequestMapping(value="/ocr")
@SuppressWarnings({"unchecked", "rawtypes"})
public class OcrController {
	
    @Autowired
    private OcrService ocrService;

    //身份证识别
    @Operation(summary = "身份证识别")
    @PostMapping("/idCardOrc")
    public Result<IdCardOcrVo> idCardOcr(@RequestPart("file") MultipartFile file) {
        IdCardOcrVo idCardOcrVo = ocrService.idCardOcr(file);
        return Result.ok(idCardOcrVo);
    }

}

