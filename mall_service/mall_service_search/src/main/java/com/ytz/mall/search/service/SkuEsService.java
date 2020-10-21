package com.ytz.mall.search.service;

import com.ytz.mall.search.pojo.SkuInfo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SkuEsService
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/21
 * @Version: V1.0
 */
public interface SkuEsService {

    /**
     * 导入数据
     * @return
     */
    List<SkuInfo> importSkuData();

    /**
     * 条件搜索
     * @param searchMap 条件
     * @return
     */
    Map<String, Object> findByCondotion(Map<String, String> searchMap);
}
