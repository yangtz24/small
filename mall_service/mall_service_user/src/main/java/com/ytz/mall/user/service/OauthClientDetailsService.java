package com.ytz.mall.user.service;

import com.github.pagehelper.PageInfo;
import com.ytz.mall.user.pojo.OauthClientDetails;

import java.util.List;


/**
 * @author yangt
 */
public interface OauthClientDetailsService {

    /***
     * OauthClientDetails多条件分页查询
     * @param oauthClientDetails
     * @param page
     * @param size
     * @return
     */
    PageInfo<OauthClientDetails> findPage(OauthClientDetails oauthClientDetails, int page, int size);

    /***
     * OauthClientDetails分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<OauthClientDetails> findPage(int page, int size);

    /***
     * OauthClientDetails多条件搜索方法
     * @param oauthClientDetails
     * @return
     */
    List<OauthClientDetails> findList(OauthClientDetails oauthClientDetails);

    /***
     * 删除OauthClientDetails
     * @param id
     */
    int delete(String id);

    /***
     * 修改OauthClientDetails数据
     * @param oauthClientDetails
     */
    int update(OauthClientDetails oauthClientDetails);

    /***
     * 新增OauthClientDetails
     * @param oauthClientDetails
     */
    int add(OauthClientDetails oauthClientDetails);

    /**
     * 根据ID查询OauthClientDetails
     * @param id
     * @return
     */
     OauthClientDetails findById(String id);

    /***
     * 查询所有OauthClientDetails
     * @return
     */
    List<OauthClientDetails> findAll();
}
