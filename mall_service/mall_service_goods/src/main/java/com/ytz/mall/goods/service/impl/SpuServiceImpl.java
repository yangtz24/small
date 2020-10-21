package com.ytz.mall.goods.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.common.IdWorker;
import com.ytz.mall.goods.dao.BrandMapper;
import com.ytz.mall.goods.dao.CategoryMapper;
import com.ytz.mall.goods.dao.SkuMapper;
import com.ytz.mall.goods.dao.SpuMapper;
import com.ytz.mall.goods.pojo.*;
import com.ytz.mall.goods.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SpuServiceImpl
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/18
 * @Version: V1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SpuServiceImpl implements SpuService {
    @Autowired
    private SpuMapper spuMapper;

    @Autowired(required = false)
    private IdWorker idWorker;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BrandMapper brandMapper;


    /**
     * Spu条件+分页查询
     *
     * @param spu  查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Spu> findPage(Spu spu, int page, int size) {
        //分页
        PageHelper.startPage(page, size);
        //搜索条件构建  排除掉 已删除的
        Example example = createExample(spu);
        //执行搜索
        return new PageInfo<Spu>(spuMapper.selectByExample(example));
    }

    /**
     * Spu分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Spu> findPage(int page, int size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<Spu>(spuMapper.selectAll());
    }

    /**
     * Spu条件查询
     *
     * @param spu
     * @return
     */
    @Override
    public List<Spu> findList(Spu spu) {
        //构建查询条件
        Example example = createExample(spu);
        //根据构建的条件查询数据
        return spuMapper.selectByExample(example);
    }


    /**
     * Spu构建查询对象
     *
     * @param spu
     * @return
     */
    public Example createExample(Spu spu) {
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDelete",0);//只找 没有被删除的
        if (spu != null) {
            // 主键
            if (!StringUtils.isEmpty(spu.getId())) {
                criteria.andEqualTo("id", spu.getId());
            }
            // 货号
            if (!StringUtils.isEmpty(spu.getSn())) {
                criteria.andEqualTo("sn", spu.getSn());
            }
            // SPU名
            if (!StringUtils.isEmpty(spu.getName())) {
                criteria.andLike("name", "%" + spu.getName() + "%");
            }
            // 副标题
            if (!StringUtils.isEmpty(spu.getCaption())) {
                criteria.andEqualTo("caption", spu.getCaption());
            }
            // 品牌ID
            if (!StringUtils.isEmpty(spu.getBrandId())) {
                criteria.andEqualTo("brandId", spu.getBrandId());
            }
            // 一级分类
            if (!StringUtils.isEmpty(spu.getCategoryId())) {
                criteria.andEqualTo("category1Id", spu.getCategoryId());
            }
            // 二级分类
            if (!StringUtils.isEmpty(spu.getCategorySecondId())) {
                criteria.andEqualTo("category2Id", spu.getCategorySecondId());
            }
            // 三级分类
            if (!StringUtils.isEmpty(spu.getCategoryThirdId())) {
                criteria.andEqualTo("category3Id", spu.getCategoryThirdId());
            }
            // 模板ID
            if (!StringUtils.isEmpty(spu.getTemplateId())) {
                criteria.andEqualTo("templateId", spu.getTemplateId());
            }
            // 运费模板id
            if (!StringUtils.isEmpty(spu.getFreightId())) {
                criteria.andEqualTo("freightId", spu.getFreightId());
            }
            // 图片
            if (!StringUtils.isEmpty(spu.getImage())) {
                criteria.andEqualTo("image", spu.getImage());
            }
            // 图片列表
            if (!StringUtils.isEmpty(spu.getImages())) {
                criteria.andEqualTo("images", spu.getImages());
            }
            // 售后服务
            if (!StringUtils.isEmpty(spu.getSaleService())) {
                criteria.andEqualTo("saleService", spu.getSaleService());
            }
            // 介绍
            if (!StringUtils.isEmpty(spu.getIntroduction())) {
                criteria.andEqualTo("introduction", spu.getIntroduction());
            }
            // 规格列表
            if (!StringUtils.isEmpty(spu.getSpecItems())) {
                criteria.andEqualTo("specItems", spu.getSpecItems());
            }
            // 参数列表
            if (!StringUtils.isEmpty(spu.getParaItems())) {
                criteria.andEqualTo("paraItems", spu.getParaItems());
            }
            // 销量
            if (!StringUtils.isEmpty(spu.getSaleNum())) {
                criteria.andEqualTo("saleNum", spu.getSaleNum());
            }
            // 评论数
            if (!StringUtils.isEmpty(spu.getCommentNum())) {
                criteria.andEqualTo("commentNum", spu.getCommentNum());
            }
            // 是否上架
            if (!StringUtils.isEmpty(spu.getIsMarketable())) {
                criteria.andEqualTo("isMarketable", spu.getIsMarketable());
            }
            // 是否启用规格
            if (!StringUtils.isEmpty(spu.getIsEnableSpec())) {
                criteria.andEqualTo("isEnableSpec", spu.getIsEnableSpec());
            }
            // 是否删除
            if (!StringUtils.isEmpty(spu.getIsDelete())) {
                criteria.andEqualTo("isDelete", spu.getIsDelete());
            }
            // 审核状态
            if (!StringUtils.isEmpty(spu.getStatus())) {
                criteria.andEqualTo("status", spu.getStatus());
            }
        }
        return example;
    }

    @Override
    public int delete(Long id) {
        Spu spu = spuMapper.selectByPrimaryKey(id);
        if(spu==null){
            throw new RuntimeException("商品不存在");
        }
        if (!spu.getIsDelete().equals("1")) {
            throw new RuntimeException("必须先逻辑删除");
        }
        return spuMapper.deleteByPrimaryKey(id);
    }


    @Override
    public int update(Spu spu) {
        return spuMapper.updateByPrimaryKey(spu);
    }

    @Override
    public int add(Spu spu) {
        return spuMapper.insert(spu);
    }

    @Override
    public Spu findById(Long id) {
        return spuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Spu> findAll() {
        return spuMapper.selectAll();
    }

    @Override
    public int save(Goods goods) {

        Spu spu = goods.getSpu();
        int result = 0;
        if (goods.getSpu().getId() == null) {
            //新增
            //1.先获取SPU的数据  添加到表中 spu表中
            spu.setId(idWorker.nextId());//生成主键 要唯一 雪花算法.
            result = spuMapper.insertSelective(spu);

            //新增SKU
        } else {
            //修改
            result = spuMapper.updateByPrimaryKeySelective(spu);

            //1.先删除spu对应的原来的SKU的类别

            //delete from tb_sku where spu_id = ?
            Sku condition = new Sku();
            condition.setSpuId(spu.getId());
            result = skuMapper.delete(condition);
            //2.再新增

        }


        //2.获取SKU 的列表数据  循环遍历添加掉sku表中
        List<Sku> skuList = goods.getSkuList();
        for (Sku sku : skuList) {
            // id 要生成
            sku.setId(idWorker.nextId());
            // name 要生成 (spu的名称+ " "+ 规格的选项的值 )  //  spu的名称: iphonex  规格的数据: spec:{ 颜色:红色,内存大小:16G}
            // 先获取规格的数据
            String spec = sku.getSpec(); //{ 颜色:红色,内存大小:16G}
            Map<String, String> map = JSON.parseObject(spec, Map.class);
            // 转成map对象  key:颜色  value:红色
            String titile = spu.getName();
            for (String key : map.keySet()) {
                // 获取SPU的名称 拼接 即可
                titile += " " + map.get(key);
            }
            sku.setName(titile);

            // create_time
            sku.setCreateTime(new Date());

            // update_time
            sku.setUpdateTime(sku.getCreateTime());

            // spu_id
            sku.setSpuId(spu.getId());

            // category_id 3级分类的ID
            Long category3Id = spu.getCategoryThirdId();
            sku.setCategoryId(category3Id);
            // category_name 3级分类的名称
            Category category = categoryMapper.selectByPrimaryKey(category3Id);
            if (ObjectUtil.isNotNull(category)) {
                sku.setCategoryName(category.getName());
            }
            // brand_name
            Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());
            if (ObjectUtil.isNotNull(brand)) {
                sku.setBrandName(brand.getName());
            }
            result = skuMapper.insertSelective(sku);
        }
        return result;
    }

    @Override
    public Goods findGoodsById(Long id) {
        //1.创建goods对象
        Goods goods = new Goods();
        //2.根据id查询SPU的数据
        Spu spu = spuMapper.selectByPrimaryKey(id);
        //3.根据ID 查询SKU的列表数据
        //select * from tb_sku where spu_id = ?
        Sku sku = new Sku();
        sku.setSpuId(id);
        List<Sku> skuList = skuMapper.select(sku);
        //4.组合对象 返回
        goods.setSpu(spu);
        goods.setSkuList(skuList);
        return goods;
    }

    @Override
    public int put(Long spuId) {
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        //检查是否删除的商品
        if(spu.getIsDelete().equals("1")){
            throw new RuntimeException("此商品已删除！");
        }
        if(!spu.getStatus().equals("1")){
            throw new RuntimeException("未通过审核的商品不能！");
        }
        //上架状态
        spu.setIsMarketable("1");
        return spuMapper.updateByPrimaryKeySelective(spu);
    }

    @Override
    public int batchPut(Long[] ids) {
        Spu spu=new Spu();
        //上架
        spu.setIsMarketable("1");
        //批量修改
        Example example=new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", Arrays.asList(ids));
        //下架
        criteria.andEqualTo("isMarketable","0");
        //审核通过的
        criteria.andEqualTo("status","1");
        //非删除的
        criteria.andEqualTo("isDelete","0");
        return spuMapper.updateByExampleSelective(spu, example);
    }

    @Override
    public int auditSpu(Long id) {
        //update tb_spu set status=1,is_marketable=1 where is_delete=0 and id = ?

        //先判断是否已经被删除
        Spu spu = spuMapper.selectByPrimaryKey(id);
        if (spu == null || spu.getIsDelete().equals("1")) {//已经被删除了 或者商品部存在
            throw new RuntimeException("商品不存在或者已经删除");
        }
        //审核商品
        spu.setStatus("1");//已经审核
        spu.setIsMarketable("1");//自动上架
        return spuMapper.updateByPrimaryKeySelective(spu);
    }

    @Override
    public int pullSpu(Long id) {
        //update tb_spu set is_marketable=0 where is_delete=0 and id = ? and is_marketable=1 and status=1
        Spu spu = spuMapper.selectByPrimaryKey(id);
        if (spu == null || spu.getIsDelete().equals("1")) {//已经被删除了 或者商品部存在
            throw new RuntimeException("商品不存在或者已经删除");
        }

        if(!spu.getStatus().equals("1") || !spu.getIsMarketable().equals("1")){
            throw new RuntimeException("商品必须要审核或者商品必须要是上架的状态");
        }

        spu.setIsMarketable("0");
        return spuMapper.updateByPrimaryKeySelective(spu);

    }

    @Override
    public int logicDeleteSpu(Long id) {
        // update set is_delete=1 where id =? and is_delete=0
        Spu spu = spuMapper.selectByPrimaryKey(id);
        if(spu==null){
            throw new RuntimeException("商品不存在");
        }

        if(spu.getIsMarketable().equals("1")){
            throw new RuntimeException("商品还没下架,不能删除");
        }
        spu.setIsDelete("1");
        spu.setStatus("0");
        return spuMapper.updateByPrimaryKeySelective(spu);
    }

    @Override
    public int restoreSpu(Long id) {
        // update set is_delete=0 where id =? and is_delete=1
        Spu spu = spuMapper.selectByPrimaryKey(id);
        if(spu==null){
            throw new RuntimeException("商品不存在");
        }
        Spu data = new Spu();
        data.setIsDelete("0");//恢复
        Example exmaple = new Example(Spu.class);
        Example.Criteria criteria = exmaple.createCriteria();
        criteria.andEqualTo("id",id);//where id =1
        criteria.andEqualTo("isDelete","1");
        return spuMapper.updateByExampleSelective(data,exmaple);
// spuMapper.updateByPrimaryKeySelective(spu);//根据主键来进行更新  update set name=? where id=?
    }
}
