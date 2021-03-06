package com.ytz.mall.goods.service;

import com.github.pagehelper.PageInfo;
import com.ytz.mall.goods.pojo.Category;

import java.util.List;

/**
 * @ClassName: CategoryService
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/16
 * @Version: V1.0
 */
public interface CategoryService {

    /***
     * Category多条件分页查询
     * @param category
     * @param page
     * @param size
     * @return
     */
    PageInfo<Category> findPage(Category category, int page, int size);

    /***
     * Category分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Category> findPage(int page, int size);

    /***
     * Category多条件搜索方法
     * @param category
     * @return
     */
    List<Category> findList(Category category);

    /***
     * 删除Category
     * @param id
     * @return
     */
    int delete(Long id);

    /***
     * 修改Category数据
     * @param category
     * @return
     */
    int update(Category category);

    /***
     * 新增Category
     * @param category
     * @return
     */
    int add(Category category);

    /**
     * 根据ID查询Category
     * @param id
     * @return
     */
    Category findById(Long id);

    /***
     * 查询所有Category
     * @return
     */
    List<Category> findAll();

    /***
     * 根据父节点ID查询
     * @param pid:父节点ID
     */
    List<Category> findByParentId(Long pid);
}
