package com.ytz.mall.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName: GoodsApplication
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/15
 * @Version: V1.0
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.ytz.mall.goods.dao"})
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }
}
