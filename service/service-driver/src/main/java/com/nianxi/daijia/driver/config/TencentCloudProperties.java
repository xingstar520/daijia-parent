package com.nianxi.daijia.driver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Jie.
 * @description: TODO
 * @date 2024/8/8
 * @version: 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "tencent.cloud")
public class TencentCloudProperties {

    private String secretId;
    private String secretKey;
    private String region;
    private String bucketPrivate;
}
