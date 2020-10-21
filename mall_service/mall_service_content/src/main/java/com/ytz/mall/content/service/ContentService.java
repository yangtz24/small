package com.ytz.mall.content.service;

import com.github.pagehelper.PageInfo;
import com.ytz.mall.content.pojo.Content;

import java.util.List;

/**
 * @ClassName: ContentService
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/20
 * @Version: V1.0
 */
public interface ContentService {

    /***
     * Content多条件分页查询
     * @param content
     * @param page
     * @param size
     * @return
     */
    PageInfo<Content> findPage(Content content, int page, int size);

    /***
     * Content分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Content> findPage(int page, int size);

    /***
     * Content多条件搜索方法
     * @param content
     * @return
     */
    List<Content> findList(Content content);

    /***
     * 删除Content
     * @param id
     * @return
     */
    int delete(Long id);

    /***
     * 修改Content数据
     * @param content
     * @return
     */
    int update(Content content);

    /***
     * 新增Content
     * @param content
     * @return
     */
    int add(Content content);

    /**
     * 根据ID查询Content
     * @param id
     * @return
     */
    Content findById(Long id);

    /***
     * 查询所有Content
     * @return
     */
    List<Content> findAll();

    /**
     * 根据分类ID查询
     * @param id
     * @return
     */
    List<Content> findByCategory(Long id);
}
