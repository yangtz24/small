package com.ytz.mall.search.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.search.pojo.SkuInfo;
import com.ytz.mall.search.service.SkuEsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SkuEsController
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/21
 * @Version: V1.0
 */
@Api(tags = "SkuEsController", description = "检索Controller")
@RestController
@RequestMapping("search")
@CrossOrigin
public class SkuEsController {

    @Autowired
    private SkuEsService skuEsService;

    /**
     * 导入sku数据
     * @return
     */
    @ApiOperation("导入sku数据")
    @GetMapping("import/sku")
    public Result<List<SkuInfo>> importSku(){
        List<SkuInfo> skuInfos = skuEsService.importSkuData();
        if (CollUtil.isNotEmpty(skuInfos)) {
            return new Result<>(true, StatusCode.SUCCESS,"导入数据到索引库中成功！", skuInfos);
        }
        return new Result<>(false, StatusCode.ERROR,"导入数据到索引库中失败！", skuInfos);
    }

    /**
     * 搜索sku数据
     * @return
     */
    @ApiOperation("搜索sku数据")
    @GetMapping
    public Result<Map<String, Object>> search(@RequestParam(required = false) Map<String, String> searchField){
        Map<String, Object> map = skuEsService.findByCondotion(searchField);
        if (ObjectUtil.isNotEmpty(map.get("rows"))) {
            return new Result<>(true, StatusCode.SUCCESS,"查询成功！", map);
        }
        return new Result<>(false, StatusCode.ERROR,"查询失败！", map);
    }
}
