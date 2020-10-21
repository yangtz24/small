package com.ytz.mall.goods.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.goods.pojo.Brand;
import com.ytz.mall.goods.dao.BrandMapper;
import com.ytz.mall.goods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName: BrandServiceImpl
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/15
 * @Version: V1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public int addBrand(Brand brand) {
        return brandMapper.insertSelective(brand);
    }

    @Override
    public int updateBrand(Long id, Brand brand) {
        return brandMapper.updateByExampleSelective(brand, id);
    }

    @Override
    public int removeBrand(Long id) {
        return brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    @Override
    public Brand findOne(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Brand> findListByCondition(Brand brand) {
        Example example = createExample(brand);
        return brandMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Brand> pageList(Integer currentPage, Integer pageSize) {
        // 使用 PageHelper.startPage(currentPage, pageSize);
        PageHelper.startPage(currentPage, pageSize);

        // 查询所有
        List<Brand> brandList = brandMapper.selectAll();

        // 封装PageInfo
        PageInfo<Brand> pageInfo = new PageInfo<>(brandList);
        return pageInfo;
    }

    @Override
    public PageInfo<Brand> pageListByCondition(Integer currentPage, Integer pageSize, Brand brand) {
        // 分页
        PageHelper.startPage(currentPage, pageSize);

        // 搜索条件
        Example example = createExample(brand);
        List<Brand> brands = brandMapper.selectByExample(example);

        // 封装PageInfo
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        return pageInfo;
    }

    @Override
    public List<Brand> findByCategory(Long id) {
        return brandMapper.findByCategory(id);
    }

    protected Example createExample(Brand brand) {
        // 自定义条件搜索对象
        Example example = new Example(Brand.class);
        // 条件构造器
        Example.Criteria criteria = example.createCriteria();

        if (ObjectUtil.isNotEmpty(brand)) {
            if (StrUtil.isNotEmpty(brand.getName())) {
                // 搜索条件
                criteria.andLike("name", "%" + brand.getName() + "%");
            }

            if (StrUtil.isNotBlank(brand.getLetter())) {
                criteria.andEqualTo("letter", brand.getLetter());
            }
        }
        return example;
    }

}
