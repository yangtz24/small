package com.ytz.mall.search.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.ytz.mall.common.Result;
import com.ytz.mall.goods.feign.GoodsFeign;
import com.ytz.mall.goods.pojo.Sku;
import com.ytz.mall.search.dao.SkuEsMapper;
import com.ytz.mall.search.pojo.SkuInfo;
import com.ytz.mall.search.service.SkuEsService;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SkuServiceImpl
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/21
 * @Version: V1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SkuEsServiceImpl implements SkuEsService {


    @Autowired
    private SkuEsMapper skuEsMapper;

    @Resource
    private GoodsFeign goodsFeign;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public List<SkuInfo> importSkuData() {

        // 调用Feign
        Result<List<Sku>> all = goodsFeign.findLimitAll();

        // 转化为JSON文件
        List<SkuInfo> skuInfos = null;
        if (ObjectUtil.isNotEmpty(all) && CollUtil.isNotEmpty(all.getData())) {
            skuInfos = JSON.parseArray(JSON.toJSONString(all.getData()), SkuInfo.class);
            // 生成动态域
            skuInfos.forEach(skuInfo -> {
                String spec = skuInfo.getSpec();
                Map<String, Object> map = JSON.parseObject(spec, Map.class);
                skuInfo.setSpecMap(map);
            });
        } else {
            skuInfos = new ArrayList<>();
        }

        skuInfos.forEach(skuInfo -> {System.out.println(skuInfo.getBrandName());});


        Iterable<SkuInfo> saveAll = skuEsMapper.saveAll(skuInfos);
        return skuInfos;
    }

    @Override
    public Map<String, Object> findByCondotion(Map<String, String> searchMap) {
        // 获取关键字
        String keywords = null;
        if (CollUtil.isNotEmpty(searchMap)) {
            keywords = searchMap.get("keywords");
        }

        if (StrUtil.isBlank(keywords)) {
            keywords = "apple";
        }

        //2.创建查询对象 的构建对象
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();

        //3.设置查询的条件
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("name", keywords));

        //4.构建查询对象
        NativeSearchQuery query = nativeSearchQueryBuilder.build();

        //5.执行查询
        AggregatedPage<SkuInfo> skuPage = elasticsearchTemplate.queryForPage(query, SkuInfo.class);

        //6.封装返回结果
        Map<String, Object> resultMap = new HashMap<>(16);
        resultMap.put("rows", skuPage.getContent());
        resultMap.put("total", skuPage.getTotalElements());
        return resultMap;
    }


}
