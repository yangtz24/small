package com.ytz.mall.search.feign;

import com.ytz.mall.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @ClassName: SkuSearchFeign
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/23
 * @Version: V1.0
 */
@FeignClient(name = "SEARCH-SERVICE")
@RequestMapping("search")
public interface SkuSearchFeign {

    /**
     * 搜索
     * @param searchMap
     * @return
     */
    @GetMapping
    Result<Map<String, Object>> search(@RequestParam(required = false) Map<String, String> searchMap);
}
