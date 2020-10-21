package com.ytz.mall.canal.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import com.ytz.mall.common.Result;
import com.ytz.mall.content.feign.ContentFeign;
import com.ytz.mall.content.pojo.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

/**
 * @ClassName: CanalDataEventListener
 * @Description: TODO CanalEventListener监听
 * @author: yangtz
 * @date: 2020/10/20
 * @Version: V1.0
 */
@CanalEventListener
public class CanalDataEventListener {

    @Autowired
    private ContentFeign contentFeign;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /***
     * 增加数据监听
     * @param eventType
     * @param rowData
     */
//    @InsertListenPoint
//    public void onEventInsert(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
//        rowData.getAfterColumnsList().forEach((c) -> System.out.println("By--Annotation: " + c.getName() + " ::   " + c.getValue()));
//    }

    /***
     * 修改数据监听
     * @param rowData
     */
//    @UpdateListenPoint
//    public void onEventUpdate(CanalEntry.RowData rowData) {
//        System.out.println("UpdateListenPoint");
//        rowData.getAfterColumnsList().forEach((c) -> System.out.println("By--Annotation: " + c.getName() + " ::   " + c.getValue()));
//    }

    /***
     * 删除数据监听
     * @param eventType
     */
//    @DeleteListenPoint
//    public void onEventDelete(CanalEntry.EventType eventType) {
//        System.out.println("DeleteListenPoint");
//    }

    /***
     * 自定义数据修改监听
     * @param eventType
     * @param rowData
     */
    @ListenPoint(destination = "example", // 实例地址
            schema = "shop_mall", // 监听的数据库
            table = {"tb_content_category", "tb_content"}, // 监听的数据表
            eventType = {
                    CanalEntry.EventType.UPDATE,
                    CanalEntry.EventType.DELETE,
                    CanalEntry.EventType.INSERT,
            }) // 监听类型
    public void onEventCustomUpdate(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        //1.获取到被修改的category_id
        String categoryId = getColumnValue(eventType, rowData);

        //2.调用feign 获取数据
        Result<List<Content>> byCategory = contentFeign.findListByCategory(Long.valueOf(categoryId));
        //3.存储到redis中
        List<Content> data = byCategory.getData();
        //4.
        stringRedisTemplate.boundValueOps("content_"+categoryId).set(JSON.toJSONString(data));
    }

    private String getColumnValue(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        //1.判断更改类型 如果是删除 则需要获取到before的数据
        String categoryId = "";
        if (CanalEntry.EventType.DELETE == eventType) {
            List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
            for (CanalEntry.Column column : beforeColumnsList) {
                //column.getName(列的名称   column.getValue() 列对应的值
                if (column.getName().equals("category_id")) {
                    categoryId = column.getValue();
                    return categoryId;
                }
            }
        } else {
            //2判断是 更新 新增 获取after的数据
            List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
            for (CanalEntry.Column column : afterColumnsList) {
                //column.getName(列的名称   column.getValue() 列对应的值
                if (column.getName().equals("category_id")) {
                    categoryId = column.getValue();
                    return categoryId;
                }
            }
        }
        //3.返回
        return categoryId;
    }
}
