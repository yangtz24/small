package com.ytz.mall.goods.service;

import com.github.pagehelper.PageInfo;
import com.ytz.mall.goods.pojo.Para;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: ParaService
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/16
 * @Version: V1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public interface ParaService {

    /***
     * Para多条件分页查询
     * @param para
     * @param page
     * @param size
     * @return
     */
    PageInfo<Para> findPage(Para para, int page, int size);

    /***
     * Para分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Para> findPage(int page, int size);

    /***
     * Para多条件搜索方法
     * @param para
     * @return
     */
    List<Para> findList(Para para);

    /***
     * 删除Para
     * @param id
     */
    int delete(Long id);

    /***
     * 修改Para数据
     * @param para
     */
    int update(Para para);

    /***
     * 新增Para
     * @param para
     */
    int add(Para para);

    /**
     * 根据ID查询Para
     * @param id
     * @return
     */
    Para findById(Long id);

    /***
     * 查询所有Para
     * @return
     */
    List<Para> findAll();

    /***
     * 根据分类ID查询参数列表
     * @param id
     * @return
     */
    List<Para> findByCategoryId(Long id);
}
