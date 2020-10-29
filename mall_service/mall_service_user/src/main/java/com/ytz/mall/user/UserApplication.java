package com.ytz.mall.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName: UserApplication
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/27
 * @Version: V1.0
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.ytz.mall.user.dao"})
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
