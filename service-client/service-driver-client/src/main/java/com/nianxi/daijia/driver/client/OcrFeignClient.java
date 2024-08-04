package com.nianxi.daijia.driver.client;

import com.nianxi.daijia.common.result.Result;
import com.nianxi.daijia.model.vo.driver.DriverLicenseOcrVo;
import com.nianxi.daijia.model.vo.driver.IdCardOcrVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "service-driver")
public interface OcrFeignClient {


}