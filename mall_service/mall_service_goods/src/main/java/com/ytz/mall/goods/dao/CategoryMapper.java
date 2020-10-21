package com.ytz.mall.goods.dao;

import com.ytz.mall.goods.pojo.Category;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName: CategoryMapper
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/16
 * @Version: V1.0
 */
@Repository
public interface CategoryMapper extends Mapper<Category> {
}
