package com.ytz.mall.goods.service;

import com.github.pagehelper.PageInfo;
import com.ytz.mall.goods.pojo.Brand;

import java.util.List;

/**
 * @ClassName: BrandService
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/15
 * @Version: V1.0
 */
public interface BrandService {

    /**
     * 添加品牌
     * @param brand
     */
    int addBrand(Brand brand);

    /**
     * 修改品牌
     * @param brand
     * @return
     */
    int updateBrand(Long id, Brand brand);

    /**
     * 删除
     * @param id
     * @return
     */
    int removeBrand(Long id);

    /**
     * 查询所有
     * @return
     */
    List<Brand> findAll();

    /**
     * 查询一个
     * @param id
     * @return
     */
    Brand findOne(Long id);

    /**
     * 多条件查询
     * @param brand
     * @return
     */
    List<Brand> findListByCondition(Brand brand);

    /**
     * 分页查询
     * @param currentPage 当前页
     * @param pageSize 每页显示条数
     * @return
     */
    PageInfo<Brand> pageList(Integer currentPage, Integer pageSize);

    /**
     * 分页条件查询
     * @param currentPage 当前页
     * @param pageSize 每页显示条数
     * @param brand 条件
     * @return
     */
    PageInfo<Brand> pageListByCondition(Integer currentPage, Integer pageSize, Brand brand);

    /**
     * 根据分类ID
     * @param id
     * @return
     */
    List<Brand> findByCategory(Long id);


}
