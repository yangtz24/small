package com.ytz.mall.goods.service;

import com.github.pagehelper.PageInfo;
import com.ytz.mall.goods.pojo.Sku;

import java.util.List;

/**
 * @ClassName: SkuService
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/18
 * @Version: V1.0
 */
public interface SkuService {

    /***
     * Sku多条件分页查询
     * @param sku
     * @param page
     * @param size
     * @return
     */
    PageInfo<Sku> findPage(Sku sku, int page, int size);

    /***
     * Sku分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Sku> findPage(int page, int size);

    /***
     * Sku多条件搜索方法
     * @param sku
     * @return
     */
    List<Sku> findList(Sku sku);

    /***
     * 删除Sku
     * @param id
     * @return
     */
    int delete(Long id);

    /***
     * 修改Sku数据
     * @param sku
     * @return
     */
    int update(Sku sku);

    /***
     * 新增Sku
     * @param sku
     * @return
     */
    int add(Sku sku);

    /**
     * 根据ID查询Sku
     * @param id
     * @return
     */
    Sku findById(Long id);

    /***
     * 查询所有Sku
     * @return
     */
    List<Sku> findAll();

    /**
     * 查询部分
     * @return
     */
    List<Sku> findlimitAll();
}
