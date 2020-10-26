package com.ytz.mall.user.service;

import com.github.pagehelper.PageInfo;
import com.ytz.mall.user.pojo.Cities;

import java.util.List;


public interface CitiesService {

    /***
     * Cities多条件分页查询
     * @param cities
     * @param page
     * @param size
     * @return
     */
    PageInfo<Cities> findPage(Cities cities, int page, int size);

    /***
     * Cities分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Cities> findPage(int page, int size);

    /***
     * Cities多条件搜索方法
     * @param cities
     * @return
     */
    List<Cities> findList(Cities cities);

    /***
     * 删除Cities
     * @param id
     */
    int delete(Long id);

    /***
     * 修改Cities数据
     * @param cities
     */
    int update(Cities cities);

    /***
     * 新增Cities
     * @param cities
     */
    int add(Cities cities);

    /**
     * 根据ID查询Cities
     * @param id
     * @return
     */
     Cities findById(Long id);

    /***
     * 查询所有Cities
     * @return
     */
    List<Cities> findAll();
}
