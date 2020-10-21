package com.ytz.mall.content.service;

import com.github.pagehelper.PageInfo;
import com.ytz.mall.content.pojo.ContentCategory;

import java.util.List;

/**
 * @ClassName: ContentCategoryService
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/20
 * @Version: V1.0
 */
public interface ContentCategoryService {

    /***
     * ContentCategory多条件分页查询
     * @param contentCategory
     * @param page
     * @param size
     * @return
     */
    PageInfo<ContentCategory> findPage(ContentCategory contentCategory, int page, int size);

    /***
     * ContentCategory分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<ContentCategory> findPage(int page, int size);

    /***
     * ContentCategory多条件搜索方法
     * @param contentCategory
     * @return
     */
    List<ContentCategory> findList(ContentCategory contentCategory);

    /***
     * 删除ContentCategory
     * @param id
     * @return
     */
    int delete(Long id);

    /***
     * 修改ContentCategory数据
     * @param contentCategory
     * @return
     */
    int update(ContentCategory contentCategory);

    /***
     * 新增ContentCategory
     * @param contentCategory
     * @return
     */
    int add(ContentCategory contentCategory);

    /**
     * 根据ID查询ContentCategory
     * @param id
     * @return
     */
    ContentCategory findById(Long id);

    /***
     * 查询所有ContentCategory
     * @return
     */
    List<ContentCategory> findAll();
}
