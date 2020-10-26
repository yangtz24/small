package com.ytz.mall.user.dao;

import com.ytz.mall.user.pojo.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName: UserMapper
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/26
 * @Version: V1.0
 */
@Repository
public interface UserMapper extends Mapper<User> {
}
