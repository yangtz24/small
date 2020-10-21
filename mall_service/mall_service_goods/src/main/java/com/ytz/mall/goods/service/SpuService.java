package com.ytz.mall.goods.service;

import com.github.pagehelper.PageInfo;
import com.ytz.mall.goods.pojo.Goods;
import com.ytz.mall.goods.pojo.Spu;

import java.util.List;

/**
 * @ClassName: SpuService
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/18
 * @Version: V1.0
 */
public interface SpuService {

    /***
     * Spu多条件分页查询
     * @param spu
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(Spu spu, int page, int size);

    /***
     * Spu分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(int page, int size);

    /***
     * Spu多条件搜索方法
     * @param spu
     * @return
     */
    List<Spu> findList(Spu spu);

    /***
     * 删除Spu
     * @param id
     * @return
     */
    int delete(Long id);

    /***
     * 修改Spu数据
     * @param spu
     * @return
     */
    int update(Spu spu);

    /***
     * 新增Spu
     * @param spu
     * @return
     */
    int add(Spu spu);

    /**
     * 根据ID查询Spu
     * @param id
     * @return
     */
    Spu findById(Long id);

    /***
     * 查询所有Spu
     * @return
     */
    List<Spu> findAll();

    /**
     * 添加商品(SPU+ SKUlIST)
     * @param goods   update  add
     * @return
     */
    int save(Goods goods);

    /**
     *
     * @param id
     * @return
     */
    Goods findGoodsById(Long id);

    /**
     * 上架
     * @param spuId
     * @return
     */
    int put(Long spuId);

    /**
     * 批量上架
     * @param ids
     * @return
     */
    int batchPut(Long[] ids);

    /**
     *
     * @param id
     * @return
     */
    int auditSpu(Long id);

    /**
     * 下架
     * @param id
     * @return
     */
    int pullSpu(Long id);

    /**
     *
     * @param id
     * @return
     */
    int logicDeleteSpu(Long id);

    /**
     *
     * @param id
     * @return
     */
    int restoreSpu(Long id);
}
