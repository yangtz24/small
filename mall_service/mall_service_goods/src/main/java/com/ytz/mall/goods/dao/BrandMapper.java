package com.ytz.mall.goods.dao;

import com.ytz.mall.goods.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName: BrandMapper
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/15
 * @Version: V1.0
 */
@Repository
public interface BrandMapper extends Mapper<Brand> {

    @Select(value="select tb.* from tb_brand tb ,tb_category_brand tbc where tb.id = tbc.brand_id and tbc.category_id=#{categoryId}")
    List<Brand> findByCategory(Long categoryId);
}
