package com.ytz.mall.goods.service;

import com.github.pagehelper.PageInfo;
import com.ytz.mall.goods.pojo.Spec;

import java.util.List;

/**
 * @ClassName: SpecService
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/16
 * @Version: V1.0
 */
public interface SpecService {

    /***
     * Spec多条件分页查询
     * @param spec
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spec> findPage(Spec spec, int page, int size);

    /***
     * Spec分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spec> findPage(int page, int size);

    /***
     * Spec多条件搜索方法
     * @param spec
     * @return
     */
    List<Spec> findList(Spec spec);

    /***
     * 删除Spec
     * @param id
     * @return
     */
    int delete(Long id);

    /***
     * 修改Spec数据
     * @param spec
     * @return
     */
    int update(Spec spec);

    /***
     * 新增Spec
     * @param spec
     * @return
     */
    int add(Spec spec);

    /**
     * 根据ID查询Spec
     * @param id
     * @return
     */
    Spec findById(Long id);

    /***
     * 查询所有Spec
     * @return
     */
    List<Spec> findAll();

    /***
     * 根据分类ID查询规格列表
     * @param categoryId
     * @return
     */
    List<Spec> findByCategoryId(Long categoryId);
}
