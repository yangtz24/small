package com.ytz.mall.web.item.controller;

import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.web.item.service.PageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: PageController
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/29
 * @Version: V1.0
 */
@Api(tags = "PageController", description = "页面Controller")
@RestController
@RequestMapping("page")
public class PageController {

    @Autowired
    private PageService pageService;

    /**
     * 生成静态页面
     * @param id SPU的ID
     * @return
     */
    @RequestMapping("createHtml/{id}")
    public Result<?> createHtml(@PathVariable(name="id") Long id){
        pageService.createPageHtml(id);
        return new Result<>(true, StatusCode.SUCCESS,"success");
    }
}
