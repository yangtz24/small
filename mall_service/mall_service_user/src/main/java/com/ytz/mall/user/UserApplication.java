package com.ytz.mall.user;

import com.ytz.mall.common.TokenDecode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public TokenDecode tokenDecode() {
        return new TokenDecode();
    }
}
