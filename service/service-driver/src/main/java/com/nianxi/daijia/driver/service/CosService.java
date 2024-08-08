package com.nianxi.daijia.driver.service;

import com.nianxi.daijia.model.vo.driver.CosUploadVo;
import org.springframework.web.multipart.MultipartFile;

public interface CosService {


    // 腾讯云cos上传服务
    CosUploadVo upload(MultipartFile file, String path);
}
