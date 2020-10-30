package com.ytz.mall.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName: OauthApplication
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/29
 * @Version: V1.0
 */
@SpringBootApplication
@EnableEurekaClient
//@MapperScan(basePackages = {"com.ytz.mall.oauth.dao"})
public class OauthApplication {
    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class, args);
    }
}
