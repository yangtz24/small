package com.ytz.mall.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.user.dao.AddressMapper;
import com.ytz.mall.user.pojo.Address;
import com.ytz.mall.user.service.AddressService;
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
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;


    /**
     * Address条件+分页查询
     * @param address 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Address> findPage(Address address, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(address);
        //执行搜索
        return new PageInfo<>(addressMapper.selectByExample(example));
    }

    /**
     * Address分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Address> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<>(addressMapper.selectAll());
    }

    /**
     * Address条件查询
     * @param address
     * @return
     */
    @Override
    public List<Address> findList(Address address){
        //构建查询条件
        Example example = createExample(address);
        //根据构建的条件查询数据
        return addressMapper.selectByExample(example);
    }


    /**
     * Address构建查询对象
     * @param address
     * @return
     */
    public Example createExample(Address address){
        Example example=new Example(Address.class);
        Example.Criteria criteria = example.createCriteria();
        if(address!=null){
            // 
            if(!StringUtils.isEmpty(address.getId())){
                    criteria.andEqualTo("id",address.getId());
            }
            // 用户名
            if(!StringUtils.isEmpty(address.getUsername())){
                    criteria.andLike("username","%"+address.getUsername()+"%");
            }
            // 省
            if(!StringUtils.isEmpty(address.getProvinceId())){
                    criteria.andEqualTo("provinceId",address.getProvinceId());
            }
            // 市
            if(!StringUtils.isEmpty(address.getCityId())){
                    criteria.andEqualTo("cityId",address.getCityId());
            }
            // 县/区
            if(!StringUtils.isEmpty(address.getAreaId())){
                    criteria.andEqualTo("areaId",address.getAreaId());
            }
            // 电话
            if(!StringUtils.isEmpty(address.getPhone())){
                    criteria.andEqualTo("phone",address.getPhone());
            }
            // 详细地址
            if(!StringUtils.isEmpty(address.getAddress())){
                    criteria.andEqualTo("address",address.getAddress());
            }
            // 联系人
            if(!StringUtils.isEmpty(address.getContact())){
                    criteria.andEqualTo("contact",address.getContact());
            }
            // 是否是默认 1默认 0否
            if(!StringUtils.isEmpty(address.getIsDefault())){
                    criteria.andEqualTo("isDefault",address.getIsDefault());
            }
            // 别名
            if(!StringUtils.isEmpty(address.getAlias())){
                    criteria.andEqualTo("alias",address.getAlias());
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
        return addressMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Address
     * @param address
     */
    @Override
    public int update(Address address){
        return addressMapper.updateByPrimaryKey(address);
    }

    /**
     * 增加Address
     * @param address
     */
    @Override
    public int add(Address address){
        return addressMapper.insert(address);
    }

    /**
     * 根据ID查询Address
     * @param id
     * @return
     */
    @Override
    public Address findById(Long id){
        return  addressMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Address全部数据
     * @return
     */
    @Override
    public List<Address> findAll() {
        return addressMapper.selectAll();
    }
}
