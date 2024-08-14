package com.nianxi.daijia.map.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jie.
 * @description: TODO
 * @date 2024/8/12
 * @version: 1.0
 */
@Configuration
public class MapConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}