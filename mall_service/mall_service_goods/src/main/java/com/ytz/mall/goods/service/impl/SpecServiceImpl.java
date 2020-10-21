package com.ytz.mall.goods.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.goods.pojo.Category;
import com.ytz.mall.goods.pojo.Spec;
import com.ytz.mall.goods.pojo.Template;
import com.ytz.mall.goods.dao.CategoryMapper;
import com.ytz.mall.goods.dao.SpecMapper;
import com.ytz.mall.goods.dao.TemplateMapper;
import com.ytz.mall.goods.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName: SpecServiceImpl
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/16
 * @Version: V1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private TemplateMapper templateMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * Spec条件+分页查询
     * @param spec 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Spec> findPage(Spec spec, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(spec);
        //执行搜索
        return new PageInfo<Spec>(specMapper.selectByExample(example));
    }

    /**
     * Spec分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Spec> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Spec>(specMapper.selectAll());
    }

    /**
     * Spec条件查询
     * @param spec
     * @return
     */
    @Override
    public List<Spec> findList(Spec spec){
        //构建查询条件
        Example example = createExample(spec);
        //根据构建的条件查询数据
        return specMapper.selectByExample(example);
    }


    /**
     * Spec构建查询对象
     * @param spec
     * @return
     */
    public Example createExample(Spec spec){
        Example example=new Example(Spec.class);
        Example.Criteria criteria = example.createCriteria();
        if(spec!=null){
            // ID
            if(!StringUtils.isEmpty(spec.getId())){
                criteria.andEqualTo("id",spec.getId());
            }
            // 名称
            if(!StringUtils.isEmpty(spec.getName())){
                criteria.andLike("name","%"+spec.getName()+"%");
            }
            // 规格选项
            if(!StringUtils.isEmpty(spec.getOptions())){
                criteria.andEqualTo("options",spec.getOptions());
            }
            // 排序
            if(!StringUtils.isEmpty(spec.getSeq())){
                criteria.andEqualTo("seq",spec.getSeq());
            }
            // 模板ID
            if(!StringUtils.isEmpty(spec.getTemplateId())){
                criteria.andEqualTo("templateId",spec.getTemplateId());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public int delete(Long id){
        //查询模板
        Spec spec = specMapper.selectByPrimaryKey(id);
        int result = 0;
        if (ObjectUtil.isNotNull(spec)) {
            //变更模板数量
            result = updateSpecNum(spec, -1);
            if (result > 0) {
                //删除指定规格
                return specMapper.deleteByPrimaryKey(id);
            }
        }
        return result;

    }

    /**
     * 修改Spec
     * @param spec
     * @return
     */
    @Override
    public int update(Spec spec){
        return specMapper.updateByPrimaryKey(spec);
    }

    /**
     * 增加Spec
     * @param spec
     * @return
     */
    @Override
    public int add(Spec spec){
        int insert = specMapper.insert(spec);
        if (insert > 0) {
            //变更模板数量
            return updateSpecNum(spec,1);
        }
        return insert;
    }

    /**
     * 根据ID查询Spec
     * @param id
     * @return
     */
    @Override
    public Spec findById(Long id){
        return  specMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Spec全部数据
     * @return
     */
    @Override
    public List<Spec> findAll() {
        return specMapper.selectAll();
    }

    @Override
    public List<Spec> findByCategoryId(Long categoryId) {
        //查询分类
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        Spec spec = new Spec();
        if (ObjectUtil.isNotNull(category)) {
            //根据分类的模板ID查询规格
            spec.setTemplateId(category.getTemplateId());
        }
        return specMapper.select(spec);
    }


    /**
     * 修改模板统计数据
     * @param spec:操作的模板
     * @param count:变更的数量
     */
    public int updateSpecNum(Spec spec,int count){
        //修改模板数量统计
        Template template = templateMapper.selectByPrimaryKey(spec.getTemplateId());
        template.setSpecNum(template.getSpecNum()+count);
        return templateMapper.updateByPrimaryKeySelective(template);
    }
}
