package com.nianxi.daijia.driver.service;

import com.nianxi.daijia.model.vo.driver.CosUploadVo;
import org.springframework.web.multipart.MultipartFile;

public interface CosService {

    //文件上传接口
    CosUploadVo uploadFile(MultipartFile file, String path);
}
