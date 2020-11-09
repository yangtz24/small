package com.ytz.mall.order.dao;

import com.ytz.mall.order.pojo.Order;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


/**
 * @author yangt
 */
@Repository
public interface OrderMapper extends Mapper<Order> {
}
