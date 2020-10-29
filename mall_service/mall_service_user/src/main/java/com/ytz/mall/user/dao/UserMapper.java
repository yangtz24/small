package com.ytz.mall.user.dao;

import com.ytz.mall.user.pojo.User;
import org.apache.ibatis.annotations.Select;
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

    /**
     * 根据用户登录名查询
     * @param userName
     * @return
     */
    @Select("SELECT * FROM TB_USER WHERE USERNAME = #{userName}")
    User findByUserName(String userName);
}
