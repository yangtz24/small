package com.ytz.mall.user.dao;

import com.ytz.mall.user.pojo.Address;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName: AddressMapper
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/26
 * @Version: V1.0
 */
@Repository
public interface AddressMapper extends Mapper<Address> {
}
