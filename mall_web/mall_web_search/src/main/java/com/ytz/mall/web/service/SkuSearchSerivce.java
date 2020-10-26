package com.ytz.mall.web.service;

import java.util.Map;

/**
 * @ClassName: SkuSearchSerivce
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/23
 * @Version: V1.0
 */
public interface SkuSearchSerivce {

    /**
     * 返回查询结果
     * @param searchMap
     * @return
     */
    Map<String, Object> search(Map<String, String> searchMap);
}
