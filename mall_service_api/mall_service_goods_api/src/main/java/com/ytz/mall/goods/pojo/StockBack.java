package com.ytz.mall.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@ApiModel(description = "StockBack",value = "StockBack")
@Table(name="tb_stock_back")
@Data
public class StockBack implements Serializable{

	private static final long serialVersionUID = -2876561391286811767L;
	@ApiModelProperty(value = "订单id",required = false)
    @Column(name = "order_id")
	private String orderId;

	@ApiModelProperty(value = "SKU的id",required = false)
	@Id
    @Column(name = "sku_id")
	private String skuId;

	@ApiModelProperty(value = "回滚数量",required = false)
    @Column(name = "num")
	private Integer num;

	@ApiModelProperty(value = "回滚状态",required = false)
    @Column(name = "status")
	private String status;

	@ApiModelProperty(value = "创建时间",required = false)
    @Column(name = "create_time")
	private Date createTime;

	@ApiModelProperty(value = "回滚时间",required = false)
    @Column(name = "back_time")
	private Date backTime;

}
