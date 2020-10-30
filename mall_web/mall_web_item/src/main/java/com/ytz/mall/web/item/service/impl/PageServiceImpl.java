package com.ytz.mall.web.item.service.impl;

import com.alibaba.fastjson.JSON;
import com.ytz.mall.common.Result;
import com.ytz.mall.goods.feign.CategoryFeign;
import com.ytz.mall.goods.feign.SkuFeign;
import com.ytz.mall.goods.feign.SpuFeign;
import com.ytz.mall.goods.pojo.Sku;
import com.ytz.mall.goods.pojo.Spu;
import com.ytz.mall.web.item.service.PageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PageServiceImpl
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/29
 * @Version: V1.0
 */
@Service
public class PageServiceImpl implements PageService {
    @Resource
    private SpuFeign spuFeign;

    @Resource
    private CategoryFeign categoryFeign;

    @Resource
    private SkuFeign skuFeign;

    @Resource
    private TemplateEngine templateEngine;

    /**
     * 生成静态文件路径
     */
    @Value("${pagePath}")
    private String pagePath;

    /**
     * 构建数据模型
     * @param spuId
     * @return
     */
    private Map<String,Object> buildDataModel(Long spuId){
        //构建数据模型
        Map<String,Object> dataMap = new HashMap<>();
        //获取spu 和SKU列表
        Result<Spu> result = spuFeign.findById(spuId);
        Spu spu = result.getData();

        //获取分类信息
        dataMap.put("category1",categoryFeign.findById(spu.getCategoryId()).getData());
        dataMap.put("category2",categoryFeign.findById(spu.getCategorySecondId()).getData());
        dataMap.put("category3",categoryFeign.findById(spu.getCategoryThirdId()).getData());
        if(spu.getImages()!=null) {
            dataMap.put("imageList", spu.getImages().split(","));
        }

        dataMap.put("specificationList", JSON.parseObject(spu.getSpecItems(),Map.class));
        dataMap.put("spu",spu);

        //根据spuId查询Sku集合
        Sku skuCondition = new Sku();
        skuCondition.setSpuId(spu.getId());
        Result<List<Sku>> resultSku = skuFeign.findList(skuCondition);
        dataMap.put("skuList",resultSku.getData());
        return dataMap;
    }

    /***
     * 生成静态页
     * @param spuId
     */
    @Override
    public void createPageHtml(Long spuId) {
        // 1.上下文  模板  +  数据集 =html
        Context context = new Context();
        Map<String, Object> dataModel = buildDataModel(spuId);
        context.setVariables(dataModel);
        // 2.准备文件
        File dir = new File(pagePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File dest = new File(dir, spuId + ".html");
        // 3.生成页面
        try (PrintWriter writer = new PrintWriter(dest, "UTF-8")) {
            //模板的文件
            templateEngine.process("item", context, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
