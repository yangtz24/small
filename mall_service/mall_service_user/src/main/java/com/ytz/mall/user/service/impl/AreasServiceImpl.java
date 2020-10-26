package com.ytz.mall.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.user.dao.AreasMapper;
import com.ytz.mall.user.pojo.Areas;
import com.ytz.mall.user.service.AreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * @author yangt
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AreasServiceImpl implements AreasService {

    @Autowired
    private AreasMapper areasMapper;


    /**
     * Areas条件+分页查询
     * @param areas 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Areas> findPage(Areas areas, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(areas);
        //执行搜索
        return new PageInfo<>(areasMapper.selectByExample(example));
    }

    /**
     * Areas分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Areas> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<>(areasMapper.selectAll());
    }

    /**
     * Areas条件查询
     * @param areas
     * @return
     */
    @Override
    public List<Areas> findList(Areas areas){
        //构建查询条件
        Example example = createExample(areas);
        //根据构建的条件查询数据
        return areasMapper.selectByExample(example);
    }


    /**
     * Areas构建查询对象
     * @param areas
     * @return
     */
    public Example createExample(Areas areas){
        Example example=new Example(Areas.class);
        Example.Criteria criteria = example.createCriteria();
        if(areas!=null){
            // 区域ID
            if(!StringUtils.isEmpty(areas.getAreaId())){
                    criteria.andEqualTo("areaId",areas.getAreaId());
            }
            // 区域名称
            if(!StringUtils.isEmpty(areas.getArea())){
                    criteria.andEqualTo("area",areas.getArea());
            }
            // 城市ID
            if(!StringUtils.isEmpty(areas.getCityId())){
                    criteria.andEqualTo("cityId",areas.getCityId());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public int delete(Long id){
        return areasMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Areas
     * @param areas
     */
    @Override
    public int update(Areas areas){
        return areasMapper.updateByPrimaryKey(areas);
    }

    /**
     * 增加Areas
     * @param areas
     */
    @Override
    public int add(Areas areas){
        return areasMapper.insert(areas);
    }

    /**
     * 根据ID查询Areas
     * @param id
     * @return
     */
    @Override
    public Areas findById(Long id){
        return  areasMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Areas全部数据
     * @return
     */
    @Override
    public List<Areas> findAll() {
        return areasMapper.selectAll();
    }
}
