package com.nianxi.daijia.driver.service;

import com.nianxi.daijia.model.vo.driver.IdCardOcrVo;
import org.springframework.web.multipart.MultipartFile;

public interface OcrService {

    //身份证识别
    IdCardOcrVo idCardOcr(MultipartFile file);
}
