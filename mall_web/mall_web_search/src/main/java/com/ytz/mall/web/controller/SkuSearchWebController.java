package com.ytz.mall.web.controller;

import com.ytz.mall.web.service.SkuSearchSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName: SkuSearchWebController
 * @Description: TODO 页面搜索功能
 * @author: yangtz
 * @date: 2020/10/23
 * @Version: V1.0
 */
@Api(tags = "SkuSearchWebController", description = "页面搜索Controller")
@Controller
@RequestMapping("search")
@CrossOrigin
public class SkuSearchWebController {

    @Resource
    private SkuSearchSerivce skuSearchSerivce;

    /**
     * 搜索
     * @param searchMap
     * @return
     */
    @ApiOperation("页面搜索")
    @GetMapping("list")
    public String search(@RequestParam(required = false) Map<String, String> searchMap, Model model) {
        //1.调用搜索微服务的 feign  根据搜索的条件参数 查询 数据
        Map<String, Object> resultmap = skuSearchSerivce.search(searchMap);
        //2.将数据设置到model中     (模板文件中 根据th:标签数据展示)
        //搜索的结果设置
        model.addAttribute("result", resultmap);

        //3.设置搜索的条件 回显
        model.addAttribute("searchMap",searchMap);

        // 设置url
        model.addAttribute("url", resultmap.get("url"));

        // 设置分页信息
        model.addAttribute("page", resultmap.get("page"));

        return "search";
    }

}
