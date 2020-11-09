package com.ytz.mall.order.service.impl;

import com.ytz.mall.common.Constants;
import com.ytz.mall.common.RedisService;
import com.ytz.mall.common.Result;
import com.ytz.mall.goods.feign.SkuFeign;
import com.ytz.mall.goods.feign.SpuFeign;
import com.ytz.mall.goods.pojo.Sku;
import com.ytz.mall.goods.pojo.Spu;
import com.ytz.mall.order.pojo.OrderItem;
import com.ytz.mall.order.service.CartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 描述
 *
 * @author www.itheima.com
 * @version 1.0
 * @package com.ytz.mall.order.service.impl *
 * @since 1.0
 */
@Service
public class CartServiceImpl implements CartService {

    @Resource
    private SkuFeign skuFeign;

    @Resource
    private SpuFeign spuFeign;

    @Resource
    private RedisService redisService;

    @Override
    public void add(Long id, Integer num, String username) {

        if(num<=0){
            //删除掉原来的商品
            redisService.hdel(Constants.CART + username, id);
            return;
        }

        //1.根据商品的SKU的ID 获取sku的数据
        Result<Sku> skuResult = skuFeign.findById(id);

        Sku data = skuResult.getData();

        if (data != null) {

            //2.根据sku的数据对象 获取 该SKU对应的SPU的数据
            Long spuId = data.getSpuId();

            Result<Spu> spuResult = spuFeign.findById(spuId);
            Spu spu = spuResult.getData();

            //3.将数据存储到 购物车对象(order_item)中

            OrderItem orderItem = new OrderItem();

            orderItem.setCategoryId(spu.getCategoryId());
            orderItem.setCategorySecondId(spu.getCategorySecondId());
            orderItem.setCategoryThirdId(spu.getCategoryThirdId());
            orderItem.setSpuId(spu.getId());
            orderItem.setSkuId(id);
            //商品的名称  sku的名称
            orderItem.setName(data.getName());
            //sku的单价
            orderItem.setPrice(data.getPrice());
            //购买的数量
            orderItem.setNum(num);
            //单价* 数量 orderItem.getNum() * orderItem.getPrice()
            orderItem.setMoney(new BigDecimal(orderItem.getNum()).multiply(orderItem.getPrice()));
            //单价* 数量 rderItem.getNum() * orderItem.getPrice()
            orderItem.setPayMoney(new BigDecimal(orderItem.getNum()).multiply(orderItem.getPrice()));
            //商品的图片dizhi
            orderItem.setImage(data.getImage());
            //4.数据添加到redis中  key:用户名 field:sku的ID  value:购物车数据(order_item)
            redisService.hset(Constants.CART + username, id, orderItem);
        }

    }

    @Override
    public Map<Object, Object> list(String username) {
        return redisService.hmget(Constants.CART + username);
    }
}
