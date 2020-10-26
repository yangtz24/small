package com.ytz.mall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName: GatewayWebApplication
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/26
 * @Version: V1.0
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayWebApplication.class, args);
    }

    /***
     * IP限流
     * @return
     */
//    @Bean(name="ipKeyResolver")
//    public KeyResolver userKeyResolver() {
//        return exchange -> {
//            //获取远程客户端IP
//            String hostName = Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getAddress().getHostAddress();
//            System.out.println("hostName:"+hostName);
//            return Mono.just(hostName);
//        };
//    }
}
