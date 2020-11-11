package com.ytz.mall.user.feign;

import com.ytz.mall.common.Result;
import com.ytz.mall.user.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: UserFeign
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/11/2
 * @Version: V1.0
 */
@FeignClient(name = "USER-SERVICE")
@RequestMapping("user")
public interface UserFeign {

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    @GetMapping("load/{username}")
    Result<User> findByUsername(@PathVariable(value = "username") String username);
}
