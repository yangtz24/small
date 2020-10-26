package com.ytz.mall.web.service.impl;

import com.ytz.mall.common.Page;
import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.search.feign.SkuSearchFeign;
import com.ytz.mall.search.pojo.SkuInfo;
import com.ytz.mall.web.service.SkuSearchSerivce;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: SkuSearchServiceImpl
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/23
 * @Version: V1.0
 */
@Service
public class SkuSearchServiceImpl implements SkuSearchSerivce {

    @Resource
    private SkuSearchFeign skuSearchFeign;

    @Override
    public Map<String, Object> search(Map<String, String> searchMap) {

        Result<Map<String, Object>> result = skuSearchFeign.search(searchMap);

        if (ObjectUtils.isEmpty(result)) {
            result = new Result<>();
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        if (StatusCode.SUCCESS == result.getCode()) {
            Map<String, Object> resultData = result.getData();
            resultMap.put("data", resultData.get("rows"));
            resultMap.put("total", resultData.get("total"));
            resultMap.put("categoryList", resultData.get("categoryList"));
            resultMap.put("totalPages", resultData.get("totalPages"));
            resultMap.put("specMap", resultData.get("specMap"));
            resultMap.put("brandList", resultData.get("brandList"));

            //创建一个分页的对象  可以获取当前页 和总个记录数和显示的页码(以当前页为中心的5个页码)
            Page<SkuInfo> page = new Page<>(
                    Long.parseLong(resultData.get("total").toString()),
                    Integer.parseInt(resultData.get("pageNum").toString()),
                    Integer.parseInt(resultData.get("pageSize").toString())
            );
            resultMap.put("page", page);
        }

        //拼接url
        String url = getUrl(searchMap);
        resultMap.put("url", url);

        return resultMap;
    }

    private String getUrl(Map<String, String> searchMap) {
        StringBuilder url = new StringBuilder("/search/list");
        if(searchMap!=null && searchMap.size()>0){
            url.append("?");
            for (Map.Entry<String, String> stringStringEntry : searchMap.entrySet()) {
                String key = stringStringEntry.getKey();
                String value = stringStringEntry.getValue();
                if("pageNum".equals(key)){
                    continue;
                }
                url.append(key).append("=").append(value).append("&");
            }

            //去掉多余的&
            if(url.lastIndexOf("&")!=-1){
                url = new StringBuilder(url.substring(0, url.lastIndexOf("&")));
            }

        }
        return url.toString();
    }
}
