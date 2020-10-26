package com.ytz.mall.user.service;

import com.github.pagehelper.PageInfo;
import com.ytz.mall.user.pojo.Areas;

import java.util.List;


/**
 * @author yangtz
 */
public interface AreasService {

    /***
     * Areas多条件分页查询
     * @param areas
     * @param page
     * @param size
     * @return
     */
    PageInfo<Areas> findPage(Areas areas, int page, int size);

    /***
     * Areas分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Areas> findPage(int page, int size);

    /***
     * Areas多条件搜索方法
     * @param areas
     * @return
     */
    List<Areas> findList(Areas areas);

    /***
     * 删除Areas
     * @param id
     */
    int delete(Long id);

    /***
     * 修改Areas数据
     * @param areas
     */
    int update(Areas areas);

    /***
     * 新增Areas
     * @param areas
     */
    int add(Areas areas);

    /**
     * 根据ID查询Areas
     * @param id
     * @return
     */
     Areas findById(Long id);

    /***
     * 查询所有Areas
     * @return
     */
    List<Areas> findAll();
}
