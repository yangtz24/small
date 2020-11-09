package com.ytz.mall.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: OauthApplication
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/29
 * @Version: V1.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.ytz.mall.user.feign"})
public class OauthApplication {
    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
