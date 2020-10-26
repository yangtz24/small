package com.ytz.mall.user.dao;

import com.ytz.mall.user.pojo.OauthClientDetails;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName: OauthClientDetailsMapper
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/26
 * @Version: V1.0
 */
@Repository
public interface OauthClientDetailsMapper extends Mapper<OauthClientDetails> {
}
