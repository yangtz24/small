package com.ytz.mall.goods.dao;

import com.ytz.mall.goods.pojo.Sku;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName: SkuMapper
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/18
 * @Version: V1.0
 */
@Repository
public interface SkuMapper extends Mapper<Sku> {

    /**
     * 查询部分
     * @return
     */
    @Select("select * from tb_sku limit 100")
    List<Sku> findLimitAll();
}
