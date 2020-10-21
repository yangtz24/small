package com.ytz.mall.goods.service;

import com.github.pagehelper.PageInfo;
import com.ytz.mall.goods.pojo.Template;

import java.util.List;

/**
 * @ClassName: TemplateService
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/16
 * @Version: V1.0
 */
public interface TemplateService {

    /***
     * Template多条件分页查询
     * @param template
     * @param page
     * @param size
     * @return
     */
    PageInfo<Template> findPage(Template template, int page, int size);

    /***
     * Template分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Template> findPage(int page, int size);

    /***
     * Template多条件搜索方法
     * @param template
     * @return
     */
    List<Template> findList(Template template);

    /***
     * 删除Template
     * @param id
     * @return
     */
    int delete(Long id);

    /***
     * 修改Template数据
     * @param template
     * @return
     */
    int update(Template template);

    /***
     * 新增Template
     * @param template
     * @return
     */
    int add(Template template);

    /**
     * 根据ID查询Template
     * @param id
     * @return
     */
    Template findById(Long id);

    /***
     * 查询所有Template
     * @return
     */
    List<Template> findAll();

    /**
     * 根据分类ID查询模板信息
     * @param id
     * @return
     */
    Template findByCategoryId(Long id);
}
