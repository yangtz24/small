package com.ytz.mall.content.feign;

import com.ytz.mall.common.Result;
import com.ytz.mall.content.pojo.Content;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName: ContentFeign
 * @Description: TODO feign调用
 * @author: yangtz
 * @date: 2020/10/20
 * @Version: V1.0
 */
@FeignClient(name="CONTENT-SERVICE")
@RequestMapping("/content")
public interface ContentFeign {

    /**
     * 根据分类的ID 获取到广告列表
     * @param id
     * @return
     */
    @GetMapping(value = "/list/category/{id}")
    Result<List<Content>> findListByCategory(@PathVariable(name="id") Long id);

}
