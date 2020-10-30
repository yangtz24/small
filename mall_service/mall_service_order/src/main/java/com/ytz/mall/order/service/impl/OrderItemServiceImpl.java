package com.ytz.mall.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.order.dao.OrderItemMapper;
import com.ytz.mall.order.pojo.OrderItem;
import com.ytz.mall.order.service.OrderItemService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/****
 * @Author:admin
 * @Description:OrderItem业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Resource
    private OrderItemMapper orderItemMapper;


    /**
     * OrderItem条件+分页查询
     * @param orderItem 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<OrderItem> findPage(OrderItem orderItem, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(orderItem);
        //执行搜索
        return new PageInfo<>(orderItemMapper.selectByExample(example));
    }

    /**
     * OrderItem分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<OrderItem> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<>(orderItemMapper.selectAll());
    }

    /**
     * OrderItem条件查询
     * @param orderItem
     * @return
     */
    @Override
    public List<OrderItem> findList(OrderItem orderItem){
        //构建查询条件
        Example example = createExample(orderItem);
        //根据构建的条件查询数据
        return orderItemMapper.selectByExample(example);
    }


    /**
     * OrderItem构建查询对象
     * @param orderItem
     * @return
     */
    public Example createExample(OrderItem orderItem){
        Example example=new Example(OrderItem.class);
        Example.Criteria criteria = example.createCriteria();
        if(orderItem!=null){
            // ID
            if(!StringUtils.isEmpty(orderItem.getOrderItemId())){
                    criteria.andEqualTo("orderItemId",orderItem.getOrderItemId());
            }
            // 1级分类
            if(!StringUtils.isEmpty(orderItem.getCategoryId())){
                    criteria.andEqualTo("categoryId",orderItem.getCategoryId());
            }
            // 2级分类
            if(!StringUtils.isEmpty(orderItem.getCategorySecondId())){
                    criteria.andEqualTo("categorySecondId",orderItem.getCategorySecondId());
            }
            // 3级分类
            if(!StringUtils.isEmpty(orderItem.getCategoryThirdId())){
                    criteria.andEqualTo("categoryThirdId",orderItem.getCategoryThirdId());
            }
            // SPU_ID
            if(!StringUtils.isEmpty(orderItem.getSpuId())){
                    criteria.andEqualTo("spuId",orderItem.getSpuId());
            }
            // SKU_ID
            if(!StringUtils.isEmpty(orderItem.getSkuId())){
                    criteria.andEqualTo("skuId",orderItem.getSkuId());
            }
            // 订单ID
            if(!StringUtils.isEmpty(orderItem.getOrderId())){
                    criteria.andEqualTo("orderId",orderItem.getOrderId());
            }
            // 商品名称
            if(!StringUtils.isEmpty(orderItem.getName())){
                    criteria.andLike("name","%"+orderItem.getName()+"%");
            }
            // 单价
            if(!StringUtils.isEmpty(orderItem.getPrice())){
                    criteria.andEqualTo("price",orderItem.getPrice());
            }
            // 数量
            if(!StringUtils.isEmpty(orderItem.getNum())){
                    criteria.andEqualTo("num",orderItem.getNum());
            }
            // 总金额
            if(!StringUtils.isEmpty(orderItem.getMoney())){
                    criteria.andEqualTo("money",orderItem.getMoney());
            }
            // 实付金额
            if(!StringUtils.isEmpty(orderItem.getPayMoney())){
                    criteria.andEqualTo("payMoney",orderItem.getPayMoney());
            }
            // 图片地址
            if(!StringUtils.isEmpty(orderItem.getImage())){
                    criteria.andEqualTo("image",orderItem.getImage());
            }
            // 重量
            if(!StringUtils.isEmpty(orderItem.getWeight())){
                    criteria.andEqualTo("weight",orderItem.getWeight());
            }
            // 运费
            if(!StringUtils.isEmpty(orderItem.getPostFee())){
                    criteria.andEqualTo("postFee",orderItem.getPostFee());
            }
            // 是否退货,0:未退货，1：已退货
            if(!StringUtils.isEmpty(orderItem.getIsReturn())){
                    criteria.andEqualTo("isReturn",orderItem.getIsReturn());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(String id){
        orderItemMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改OrderItem
     * @param orderItem
     */
    @Override
    public void update(OrderItem orderItem){
        orderItemMapper.updateByPrimaryKey(orderItem);
    }

    /**
     * 增加OrderItem
     * @param orderItem
     */
    @Override
    public void add(OrderItem orderItem){
        orderItemMapper.insert(orderItem);
    }

    /**
     * 根据ID查询OrderItem
     * @param id
     * @return
     */
    @Override
    public OrderItem findById(String id){
        return  orderItemMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询OrderItem全部数据
     * @return
     */
    @Override
    public List<OrderItem> findAll() {
        return orderItemMapper.selectAll();
    }
}
