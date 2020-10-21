package com.ytz.mall.search.dao;

import com.ytz.mall.search.pojo.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: SkuEsMapper
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/21
 * @Version: V1.0
 */
@Repository
public interface SkuEsMapper extends ElasticsearchRepository<SkuInfo, Long> {
}
