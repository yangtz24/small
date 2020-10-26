package com.ytz.mall.user.service;

import com.github.pagehelper.PageInfo;
import com.ytz.mall.user.pojo.User;

import java.util.List;


public interface UserService {

    /***
     * User多条件分页查询
     * @param user
     * @param page
     * @param size
     * @return
     */
    PageInfo<User> findPage(User user, int page, int size);

    /***
     * User分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<User> findPage(int page, int size);

    /***
     * User多条件搜索方法
     * @param user
     * @return
     */
    List<User> findList(User user);

    /***
     * 删除User
     * @param id
     */
    int delete(Long id);

    /***
     * 修改User数据
     * @param user
     */
    int update(User user);

    /***
     * 新增User
     * @param user
     */
    int add(User user);

    /**
     * 根据ID查询User
     * @param id
     * @return
     */
     User findById(Long id);

    /***
     * 查询所有User
     * @return
     */
    List<User> findAll();
}
