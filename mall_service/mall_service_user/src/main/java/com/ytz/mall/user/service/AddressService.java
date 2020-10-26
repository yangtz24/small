package com.ytz.mall.user.service;

import com.github.pagehelper.PageInfo;
import com.ytz.mall.user.pojo.Address;

import java.util.List;


/**
 * @author yangtz
 */
public interface AddressService {

    /***
     * Address多条件分页查询
     * @param address
     * @param page
     * @param size
     * @return
     */
    PageInfo<Address> findPage(Address address, int page, int size);

    /***
     * Address分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Address> findPage(int page, int size);

    /***
     * Address多条件搜索方法
     * @param address
     * @return
     */
    List<Address> findList(Address address);

    /***
     * 删除Address
     * @param id
     * @return
     */
    int delete(Long id);

    /***
     * 修改Address数据
     * @param address
     * @return
     */
    int update(Address address);

    /***
     * 新增Address
     * @param address
     * @return
     */
    int add(Address address);

    /**
     * 根据ID查询Address
     * @param id
     * @return
     */
     Address findById(Long id);

    /***
     * 查询所有Address
     * @return
     */
    List<Address> findAll();
}
