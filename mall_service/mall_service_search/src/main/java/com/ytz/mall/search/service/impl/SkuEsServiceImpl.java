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
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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

        Iterable<SkuInfo> saveAll = skuEsMapper.saveAll(skuInfos);
        return skuInfos;
    }

    @Override
    public Map<String, Object> findByCondotion(Map<String, String> searchMap) {

        if (CollUtil.isEmpty(searchMap)) {
            searchMap = new ConcurrentHashMap<>();
        }

        //1.获取到关键字
        String keywords = searchMap.get("keywords");

        //2.判断是否为空 如果 为空 给一个默认 值:华为
        if (StringUtils.isEmpty(keywords)) {
            keywords = "华为";
        }
        //3.创建 查询构建对象
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //4.设置 查询的条件

        // 4.1 商品分类的列表展示: 按照商品分类的名称来分组
        //terms  指定分组的一个别名
        //field 指定要分组的字段名
        //size 指定查询结果的数量 默认是10个
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuCategorygroup").field("categoryName").size(50));

        //4.2 商品的品牌的列表展示  按照商品品牌来进行分组
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuBrandgroup").field("brandName").size(100));

        //4.3 商品的规格的列表展示   按照商品的规格的字段spec 进行分组
        //规则 要求 字段 是一个keyword类型的  spec.keyword
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuSpecgroup").field("spec.keyword").size(500));


        //4.4 设置高亮的字段 设置前缀 和 后缀

        //设置高亮的字段 针对 商品的名称进行高亮
        nativeSearchQueryBuilder.withHighlightFields(new HighlightBuilder.Field("name"));
        //设置前缀 和 后缀
        nativeSearchQueryBuilder.withHighlightBuilder(new HighlightBuilder().preTags("<em style=\"color:red\">").postTags("</em>"));




        //匹配查询  先分词 再查询  主条件查询
        //参数1 指定要搜索的字段
        //参数2 要搜索的值(先分词 再搜索)
        //nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("name", keywords));
        //从多个字段中搜索数据
        nativeSearchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery(keywords,"name","categoryName","brandName"));



        //========================过滤查询 开始=====================================

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //4.4 过滤查询的条件设置   商品分类的条件
        String category = searchMap.get("category");

        if(!StringUtils.isEmpty(category)) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("categoryName", category));
        }
        //4.5 过滤查询的条件设置   商品品牌的条件
        String brand = searchMap.get("brand");

        if(!StringUtils.isEmpty(brand)) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("brandName", brand));
        }

        //4.6 过滤查询的条件设置   规格条件

        for (String key : searchMap.keySet()) {
            if(key.startsWith("spec_"))  {
                //截取规格的名称
                boolQueryBuilder.filter(QueryBuilders.termQuery("specMap."+key.substring(5)+".keyword", searchMap.get(key)));
            }
        }
        //4.7 过滤查询的条件设置   价格区间的过滤查询
        String price = searchMap.get("price");
        if(!StringUtils.isEmpty(price)){
            //获取值 按照- 切割
            String[] split = price.split("-");
            //过滤范围查询
            //0<=price<=500
            if(!"*".equals(split[1])) {
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").from(split[0], true).to(split[1], true));
            }else{
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(split[0]));
            }

        }


        //过滤查询
        nativeSearchQueryBuilder.withFilter(boolQueryBuilder);

        //========================过滤查询 结束=====================================


        //分页查询

        //第一个参数:指定当前的页码  注意: 如果是第一页 数值为0
        //第二个参数:指定当前的页的显示的行
        String pageNum1 = searchMap.get("pageNum");
        if (StrUtil.isBlank(pageNum1)) {
            pageNum1 = "1";
        }

        String pageSize1 = searchMap.get("pageSize");
        if (StrUtil.isBlank(pageSize1)) {
            pageSize1 = "20";
        }
        int pageNum = Integer.parseInt(pageNum1);
        int pageSize = Integer.parseInt(pageSize1);

        nativeSearchQueryBuilder.withPageable(PageRequest.of(pageNum-1, pageSize));


        //排序操作
        //获取排序的字段 和要排序的规则
        String sortField = searchMap.get("sortField");
        String sortRule = searchMap.get("sortRule");
        if(!StringUtils.isEmpty(sortField) && !StringUtils.isEmpty(sortRule)) {
            //执行排序
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(sortField).order(sortRule.equalsIgnoreCase("ASC")? SortOrder.ASC:SortOrder.DESC));
            //nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(sortField).order(SortOrder.valueOf(sortRule)));
        }

        //5.构建查询对象(封装了查询的语法)
        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();

        //6.执行查询
        AggregatedPage<SkuInfo> skuInfos = elasticsearchTemplate.queryForPage(nativeSearchQuery, SkuInfo.class,new SearchResultMapperImpl());


        // 6.2 获取聚合分组结果  获取商品分类的列表数据
        StringTerms stringTermsCategory = (StringTerms) skuInfos.getAggregation("skuCategorygroup");
        List<String> categoryList = getStringsCategoryList(stringTermsCategory);


        //6.3 获取 品牌分组结果 列表数据

        StringTerms stringTermsBrand = (StringTerms) skuInfos.getAggregation("skuBrandgroup");
        List<String> brandList = getStringsBrandList(stringTermsBrand);

        //6.4 获取 规格的分组结果 列表数据map
        StringTerms stringTermsSpec = (StringTerms) skuInfos.getAggregation("skuSpecgroup");
        Map<String, Set<String>> specMap = getStringSetMap(stringTermsSpec);

        //7.获取结果  返回map

        //当前的页的集合
        List<SkuInfo> content = skuInfos.getContent();
        //总页数
        int totalPages = skuInfos.getTotalPages();
        //总记录数
        long totalElements = skuInfos.getTotalElements();

        // 分页参数获取
        Pageable pageable = nativeSearchQuery.getPageable();
        int pageSize2 = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        Map<String, Object> resultMap = new HashMap<>();
        //商品分类的列表数据
        resultMap.put("categoryList", categoryList);
        //商品品牌的列表数据
        resultMap.put("brandList", brandList);
        //商品规格的列表数据展示
        resultMap.put("specMap", specMap);
        resultMap.put("rows", content);
        resultMap.put("total", totalElements);
        resultMap.put("totalPages", totalPages);
        resultMap.put("pageNum",pageNumber);
        resultMap.put("pageSize",pageSize2);
        return resultMap;
    }

    private Map<String, Set<String>> getStringSetMap(StringTerms stringTermsSpec) {
        //key :规格的名称
        //value :规格名称对应的选项的多个值集合set
        Map<String, Set<String>> specMap = new HashMap<String, Set<String>>();
        Set<String> specValues = new HashSet<String>();
        if (stringTermsSpec != null) {
            //1. 获取分组的结果集
            for (StringTerms.Bucket bucket : stringTermsSpec.getBuckets()) {
                //2.去除结果集的每一行数据()   {"手机屏幕尺寸":"5.5寸","网络":"电信4G","颜色":"白","测试":"s11","机身内存":"128G","存储":"16G","像素":"300万像素"}
                String keyAsString = bucket.getKeyAsString();

                //3.转成JSON 对象  map  key :规格的名称  value:规格名对应的选项的单个值
                Map<String, String> map = JSON.parseObject(keyAsString, Map.class);
                for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
                    String key = stringStringEntry.getKey();//规格名称   手机屏幕尺寸
                    String value = stringStringEntry.getValue();//规格的名称对应的单个选项值 5.5寸

                    //先从原来的specMap中 获取 某一个规格名称 对应的规格的选项值集合
                    specValues = specMap.get(key);
                    if (specValues == null) {
                        specValues = new HashSet<>();
                    }
                    specValues.add(value);
                    //4.提取map中的值放入到返回的map中
                    specMap.put(key, specValues);
                }
            }
        }
        return specMap;
    }

    private List<String> getStringsBrandList(StringTerms stringTermsBrand) {
        List<String> brandList = new ArrayList<>();
        if (stringTermsBrand != null) {
            for (StringTerms.Bucket bucket : stringTermsBrand.getBuckets()) {
                String keyAsString = bucket.getKeyAsString();//品牌的名称 huawei
                brandList.add(keyAsString);
            }
        }
        return brandList;
    }

    /**
     * 获取分组结果   商品分类的分组结果
     *
     * @param stringTermsCategory
     * @return
     */
    private List<String> getStringsCategoryList(StringTerms stringTermsCategory) {
        List<String> categoryList = new ArrayList<>();
        if (stringTermsCategory != null) {
            for (StringTerms.Bucket bucket : stringTermsCategory.getBuckets()) {
                String keyAsString = bucket.getKeyAsString();
                categoryList.add(keyAsString);
            }
        }
        return categoryList;
    }


}
